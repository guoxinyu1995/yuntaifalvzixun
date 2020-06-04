package com.ytfu.yuntaifawu.apis;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.uber.autodispose.AutoDisposeConverter;
import com.ytfu.yuntaifawu.BuildConfig;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.utils.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @作者 gxy
 * @创建时间 2019/11/9
 * @描述 获取Http Service
 */
public class HttpUtil {
    private static HttpUtil instance;
    private final String BASE_URL2 = BuildConfig.BASIC_URL;
    private final String BASE_URL = BASE_URL2 + "api/";
    private String version;
    //    private final String BASE_URL_HTTP = "http://yuntaifawu.com/api/";

    public static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }

    public ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    public <T> T getService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    public <T> T getService2(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 请求入列
     */
    public <T> void enqueue(Observable<T> observable, AutoDisposeConverter<T> oc, BaseRxObserver<T> cb) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(oc)
                .subscribe(cb);
    }

    public ApiService getHttpApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    /**
     * 初始化OkHttpClient
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder();
        //设置Http缓存
        Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        mClientBuilder.cache(cache)
                // 超时重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new AddCookiesInterceptor()) //这部分
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .dns(new ApiDns());
        if (AppConstant.DEBUG) {
            // 只在debug的时候打印日志
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyHttpLoggingInterceptor());
            //            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mClientBuilder.addInterceptor(interceptor);
        }
        return mClientBuilder.build();
    }

    private class MyHttpLoggingInterceptor implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(@NonNull String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.delete(0, mMessage.length());
            }

            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            boolean isJson = (message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"));
            if (isJson) {
                message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
            }
            mMessage.append(message.concat("\n"));
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Logger.d(mMessage.toString());
            }
        }
    }

    //首次请求的处理
    public class ReceivedCookiesInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }

                SharedPreferences.Editor config = App.getContext().getSharedPreferences("config", App.getContext().MODE_PRIVATE)
                        .edit();
                config.putStringSet("cookie", cookies);
                config.commit();
            }

            return originalResponse;
        }
    }

    //非首次请求的处理
    public class AddCookiesInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            HashSet<String> preferences = (HashSet) App.getContext().getSharedPreferences("config",
                    App.getContext().MODE_PRIVATE).getStringSet("cookie", null);
            if (preferences != null) {
                for (String cookie : preferences) {
                    builder.addHeader("Cookie", cookie);
                    Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
                }
            }
            return chain.proceed(builder.build());
        }
    }

    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            PackageManager packageManager = App.getContext().getPackageManager();
            try {
                PackageInfo packInfo = packageManager.getPackageInfo(App.getContext().getPackageName(), 0);
                version = packInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("xt", "1")//平台
                    .header("version", version);//app版本
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

   /*  .header("sysVersion", "sysVersion")//系统版本号
                    .header("device", "device")//设备信息
                    .header("screen", "screen")//屏幕大小
                    .header("uuid", "uuid")//设备唯一码
     .header("apiVersion", "apiVersion")//api版本
                    .header("token", "token")//令牌
                    .header("channelId", "channelId")//渠道
                    .header("networkType", "networkType");//网络类型*/
}
