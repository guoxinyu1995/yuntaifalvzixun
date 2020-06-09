package com.ytfu.yuntaifawu.ui.lvshiwode.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yanzhenjie.permission.Permission;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.lvshiwode.bean.LawyerInformationBean;
import com.ytfu.yuntaifawu.ui.lvshiwode.bean.LawyerRzUpdateBean;
import com.ytfu.yuntaifawu.ui.lvshiwode.presenter.LawyerInforPresenter;
import com.ytfu.yuntaifawu.ui.lvshiwode.view.ILwayerInforView;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityLvShiAddress;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityLvShiJIanjie;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityLvShiRenZheng;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityLvShiRenZhengSetName;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityShangChangLingYu;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityZhiYeJiGou;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.model.InvokeParam;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Auther gxy
 * @Date 2020/5/28
 * @Des 律师认证信息
 */
public class LawyerRenZhengInforActivity extends BaseActivity<ILwayerInforView, LawyerInforPresenter> implements ILwayerInforView {
    private static final String TAG = "ActivityLvShiRenZheng";
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_mianguanzhao)
    ImageView ivMianguanzhao;
    @BindView(R.id.tv_mianguanzhao)
    TextView tvMianguanzhao;
    @BindView(R.id.cl_mianguanzhao)
    ConstraintLayout clMianguanzhao;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cl_name)
    ConstraintLayout clName;
    @BindView(R.id.tv_nianling)
    TextView tvNianling;
    @BindView(R.id.cl_nianling)
    ConstraintLayout clNianling;
    @BindView(R.id.tv_xueli)
    TextView tvXueli;
    @BindView(R.id.cl_xueli)
    ConstraintLayout clXueli;
    @BindView(R.id.tv_zhiyejigou)
    TextView tvZhiyejigou;
    @BindView(R.id.cl_zhiyejigou)
    ConstraintLayout clZhiyejigou;
    @BindView(R.id.tv_lvshuoaddress)
    TextView tvLvshuoaddress;
    @BindView(R.id.cl_lvshuoaddress)
    ConstraintLayout clLvshuoaddress;
    @BindView(R.id.tv_shanchanglingyu)
    TextView tvShanchanglingyu;
    @BindView(R.id.cl_shanchanglingyu)
    ConstraintLayout clShanchanglingyu;
    @BindView(R.id.tv_gerenjianjie)
    TextView tvGerenjianjie;
    @BindView(R.id.cl_gerenjianjie)
    ConstraintLayout clGerenjianjie;
    @BindView(R.id.tv_lvshizhiyezheng)
    TextView tvLvshizhiyezheng;
    @BindView(R.id.iv_lvshizhiyezheng)
    ImageView ivLvshizhiyezheng;
    @BindView(R.id.ll_zheyezheng_add)
    LinearLayout llZheyezhengAdd;
    @BindView(R.id.cl_zhiyezheng)
    ConstraintLayout clZhiyezheng;
    @BindView(R.id.tv_niadubeian)
    TextView tvNiadubeian;
    @BindView(R.id.iv_niadubeian)
    ImageView ivNiadubeian;
    @BindView(R.id.ll_niandubeian_add)
    LinearLayout llNiandubeianAdd;
    @BindView(R.id.cl_niadubeian)
    ConstraintLayout clNiadubeian;
    @BindView(R.id.tv_shefenzheng_zhengmian)
    TextView tvShefenzhengZhengmian;
    @BindView(R.id.iv_shefenzheng_zhengmian)
    ImageView ivShefenzhengZhengmian;
    @BindView(R.id.ll_shefenzheng_zhengmian_add)
    LinearLayout llShefenzhengZhengmianAdd;
    @BindView(R.id.cl_shefenzheng_zhengmian)
    ConstraintLayout clShefenzhengZhengmian;
    @BindView(R.id.tv_shefenzheng_fanmian)
    TextView tvShefenzhengFanmian;
    @BindView(R.id.iv_shefenzheng_fanmian)
    ImageView ivShefenzhengFanmian;
    @BindView(R.id.ll_shefenzheng_fanmian_add)
    LinearLayout llShefenzhengFanmianAdd;
    @BindView(R.id.cl_shefenzheng_fanmian)
    ConstraintLayout clShefenzhengFanmian;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private List<String> xueliList;
    private OptionsPickerBuilder pickerBuilder;
    private List<Integer> integerList;

    /**
     * 上传头像
     */
    private String[] items = {"拍照", "相册选择"};
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private static final int CAMERA = 1;
    private static final int PHONES = 2;
    private int imageTypeInt;
    private File file;
    private File fileMg;
    private File fileZyz;
    private File fileNdba;
    private File fileSfzZm;
    private File fileSfzFm;
    //接口返回的数据
    private String status;  //3是审核中 4是审核通过 5是审核不通过
    private String name;
    private String age;
    private String edu;
    private String jigou;
    private String address;
    private String lingyu;
    private String jianjie;
    private String id;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lvshirenzheng_infor;
    }

    @Override
    protected LawyerInforPresenter createPresenter() {
        return new LawyerInforPresenter(this);
    }

    public static void startAt(Context context) {
        Intent intent = new Intent(context, LawyerRenZhengInforActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LawyerRenZhengInforActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {
        hideLoading();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.detectFileUriExposure();
        }
        tvTopTitle.setText("律师认证信息");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        String uid = SpUtil.getString(AppConstant.UID, "");
        getPresenter().getLawyerInfor(uid);
        //学历集合
        xueliList = new ArrayList<>();
        xueliList.add("大专");
        xueliList.add("本科");
        xueliList.add("硕士");
        xueliList.add("博士");
        xueliList.add("博士后");
        //年龄
        integerList = new ArrayList<>();
        for (int j = 18; j < 70; j++) {
            integerList.add(j);
        }
    }

    //接收event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getTextEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.LVSHI_RENZHENG_NAME:
                String lvshiname = messageEvent.getMessage();
                tvName.setText(lvshiname);
                break;
            case AppConstant.LVSUO_RENZHENG_NAME:
                String lvsuoname = messageEvent.getMessage();
                tvZhiyejigou.setText(lvsuoname);
                break;
            case AppConstant.LVSHI_RENZHENG_GRJJ:
                String lvshijianjie = messageEvent.getMessage();
                tvGerenjianjie.setText(lvshijianjie);
                break;
            case AppConstant.LVSHI_RENZHENG_ADDRESS:
                String lvshiaddress = messageEvent.getMessage();
                tvLvshuoaddress.setText(lvshiaddress);
                break;
            case AppConstant.LVSHI_RENZHENG_SAHNGCHANGLINGYU:
                String shangchanglingyu = messageEvent.getMessage();
                tvShanchanglingyu.setText(shangchanglingyu);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_fanhui, R.id.cl_mianguanzhao, R.id.cl_name, R.id.cl_nianling, R.id.cl_xueli, R.id.cl_zhiyejigou, R.id.cl_lvshuoaddress, R.id.cl_shanchanglingyu, R.id.cl_gerenjianjie, R.id.cl_zhiyezheng, R.id.cl_niadubeian, R.id.cl_shefenzheng_zhengmian, R.id.cl_shefenzheng_fanmian, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.cl_mianguanzhao:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //上传免冠照
                    imageTypeInt = 1;
                    AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                        @Override
                        public void onPermissionGranted() {
                            showCropPicDialog();
                        }
                    }, Permission.Group.CAMERA, Permission.Group.STORAGE);
                }
                break;
            case R.id.cl_zhiyezheng:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //律师执业证
                    imageTypeInt = 2;
                    AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                        @Override
                        public void onPermissionGranted() {
                            showCropPicDialog();
                        }
                    }, Permission.Group.CAMERA, Permission.Group.STORAGE);
                }
                break;
            case R.id.cl_niadubeian:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //律师年度备案考核
                    imageTypeInt = 3;
                    AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                        @Override
                        public void onPermissionGranted() {
                            showCropPicDialog();
                        }
                    }, Permission.Group.CAMERA, Permission.Group.STORAGE);
                }
                break;
            case R.id.cl_shefenzheng_zhengmian:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //身份证正面
                    imageTypeInt = 4;
                    AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                        @Override
                        public void onPermissionGranted() {
                            showCropPicDialog();
                        }
                    }, Permission.Group.CAMERA, Permission.Group.STORAGE);
                }
                break;
            case R.id.cl_shefenzheng_fanmian:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //身份证反面
                    imageTypeInt = 5;
                    AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                        @Override
                        public void onPermissionGranted() {
                            showCropPicDialog();
                        }
                    }, Permission.Group.CAMERA, Permission.Group.STORAGE);
                }
                break;
            case R.id.cl_name:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //姓名
                    startActivity(new Intent(LawyerRenZhengInforActivity.this, ActivityLvShiRenZhengSetName.class));
                }
                break;
            case R.id.cl_nianling:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //年龄
                    showYearDialog();
                    lightoff();
                }
                break;
            case R.id.cl_xueli:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //学历
                    showXueLiDialog();
                    lightoff();
                }
                break;
            case R.id.cl_zhiyejigou:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //职业机构
                    startActivity(new Intent(LawyerRenZhengInforActivity.this, ActivityZhiYeJiGou.class));
                }
                break;
            case R.id.cl_lvshuoaddress:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //律所地址
                    startActivity(new Intent(LawyerRenZhengInforActivity.this, ActivityLvShiAddress.class));
                }
                break;
            case R.id.cl_shanchanglingyu:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //擅长领域
                    startActivity(new Intent(LawyerRenZhengInforActivity.this, ActivityShangChangLingYu.class));
                }
                break;
            case R.id.cl_gerenjianjie:
                if ("3".equals(status)) {
                    showCenterToast("正在审核");
                } else {
                    //个人简介
                    startActivity(new Intent(LawyerRenZhengInforActivity.this, ActivityLvShiJIanjie.class));
                }
                break;
            case R.id.tv_commit:
                //提交
                getTvCommit();
                break;
        }
    }

    private void getTvCommit() {
        showWaitingDialog("正在提交。。。", true);
        String uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id", parseRequestBody(id));
        map.put("uid", parseRequestBody(uid));
        if (!name.equals(tvName.getText().toString())) {
            map.put("name", parseRequestBody(tvName.getText().toString()));
        }
        if (!age.equals(tvNianling.getText().toString())) {
            map.put("age", parseRequestBody(tvNianling.getText().toString()));
        }
        if (!jigou.equals(tvZhiyejigou.getText().toString())) {
            map.put("jigou", parseRequestBody(tvZhiyejigou.getText().toString()));
        }
        if (!address.equals(tvLvshuoaddress.getText().toString())) {
            map.put("address", parseRequestBody(tvLvshuoaddress.getText().toString()));
        }
        if (!lingyu.equals(tvShanchanglingyu.getText().toString())) {
            map.put("lingyu", parseRequestBody(tvShanchanglingyu.getText().toString()));
        }
        if (!edu.equals(tvXueli.getText().toString())) {
            map.put("edu", parseRequestBody(tvXueli.getText().toString()));
        }
        if (!jianjie.equals(tvGerenjianjie.getText().toString())) {
            map.put("jianjie", parseRequestBody(tvGerenjianjie.getText().toString()));
        }
        if (fileMg != null) {
            map.put(parseImageMapKey("photo", fileMg.getName()), parseImageRequestBody(fileMg));
        }
        if (fileZyz != null) {
            map.put(parseImageMapKey("zhiyezheng", fileZyz.getName()), parseImageRequestBody(fileZyz));
        }
        if (fileNdba != null) {
            map.put(parseImageMapKey("beian", fileNdba.getName()), parseImageRequestBody(fileNdba));
        }
        if (fileSfzZm != null) {
            map.put(parseImageMapKey("card1", fileSfzZm.getName()), parseImageRequestBody(fileSfzZm));
        }
        if (fileSfzFm != null) {
            map.put(parseImageMapKey("card2", fileSfzFm.getName()), parseImageRequestBody(fileSfzFm));
        }
        getPresenter().getLawyerUpdate(map);
    }

    public static RequestBody parseRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    public static RequestBody parseImageRequestBody(File file) {
        return RequestBody.create(MediaType.parse("image/*"), file);
    }

    public static String parseImageMapKey(String key, String fileName) {
        return key + "\"; filename=\"" + fileName;
    }

    //选择相机相册弹框
    private void showCropPicDialog() {
        //1、使用Dialog、设置style
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_camera_phone_item, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                lighton();
            }
        });
        //调用相机
        dialog.findViewById(R.id.tv_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
                dialog.dismiss();
                lighton();
            }
        });
        //调用相册
        dialog.findViewById(R.id.tv_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photos();
                dialog.dismiss();
                lighton();
            }
        });

        //取消
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    lighton();
                }
            }
        });
    }

    //相册
    private void photos() {
        Intent getImage = new Intent(Intent.ACTION_PICK, null);
        getImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//这是图片类型
        startActivityForResult(getImage, PHONES);
    }

    //相机
    private void camera() {
        try {
            file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg");
            if (!file.exists()) {
                boolean b = file.createNewFile();
                if (b) {
                    Log.e(TAG, "camera: " + file.getAbsolutePath());
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, CAMERA);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "camera: " + e.toString());
        }


    }


    //年龄
    int j;

    private void showYearDialog() {
        //1、使用Dialog、设置style
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_type_item, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                lighton();
            }
        });
        WheelView wheelView = dialog.findViewById(R.id.wheel_view);
        wheelView.setTextSize(20);
        wheelView.setLineSpacingMultiplier(2f);
        // wheelView.setDividerWidth(6);
        wheelView.setDividerType(WheelView.DividerType.CIRCLE);
        wheelView.setAdapter(new ArrayWheelAdapter(integerList));
        TextView tv_type = dialog.findViewById(R.id.tv_type);
        tv_type.setText("请选择年龄");
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                j = index;
            }
        });
        dialog.findViewById(R.id.tv_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    lighton();
                }
            }
        });
        dialog.findViewById(R.id.tv_queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNianling.setText(integerList.get(j) + "");
                dialog.dismiss();
                lighton();
            }
        });
    }

    //选择学历
    int i;

    private void showXueLiDialog() {
        //1、使用Dialog、设置style
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_type_item, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                lighton();
            }
        });
        WheelView wheelView = dialog.findViewById(R.id.wheel_view);
        wheelView.setTextSize(20);
        wheelView.setLineSpacingMultiplier(2f);
        // wheelView.setDividerWidth(6);
        wheelView.setDividerType(WheelView.DividerType.FILL);
        wheelView.setAdapter(new ArrayWheelAdapter(xueliList));
        TextView tv_type = dialog.findViewById(R.id.tv_type);
        tv_type.setText("请选择学历");
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                i = index;
            }
        });
        dialog.findViewById(R.id.tv_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    lighton();
                }
            }
        });
        dialog.findViewById(R.id.tv_queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvXueli.setText(xueliList.get(i));
                dialog.dismiss();
                lighton();
            }
        });
    }

    /**
     * 设置手机屏幕亮度变暗
     */
    private void lightoff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }

    /**
     * 设置手机屏幕亮度显示正常
     */
    private void lighton() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 添加takePhoto回调
//        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CAMERA:
                    if (file != null) {
                        scaleImage(Uri.fromFile(file));
                    }
                    break;
                case PHONES:
                    if (data != null) {
                        scaleImage(data.getData());
                    }
                    break;
            }
        }
    }

    private void scaleImage(Uri uri) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 500 || height > 500) {
                if (width > height) {
                    float scaleRate = (float) (500.0 / width);
                    width = 500;
                    height = (int) (height * scaleRate);
                    Bitmap map = Bitmap.createScaledBitmap(bitmap, width, height, true);
                    saveBitmap(map);
                } else {
                    float scaleRate = (float) (500.0 / height);
                    height = 500;
                    width = (int) (width * scaleRate);
                    Bitmap map = Bitmap.createScaledBitmap(bitmap, width, height, true);
                    saveBitmap(map);
                }
            } else {
                saveBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveBitmap(Bitmap map) {
        if (imageTypeInt == 1) {
            fileMg = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
//            if(!fileMg.exists()){
//                boolean b = fileMg.canExecute();
//                if(b){
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileMg));
                map.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                Log.e(TAG, "saveBitmap: " + fileMg);
                ivMianguanzhao.setImageBitmap(map);
            } catch (Exception e) {
                e.printStackTrace();
//                    }
            }
//            }

        } else if (imageTypeInt == 2) {
            fileZyz = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
//            if(!fileZyz.exists()){
//                boolean b = fileZyz.canExecute();
//                if(b){
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileZyz));
                map.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                Log.e(TAG, "saveBitmap: " + fileZyz);
                ivLvshizhiyezheng.setImageBitmap(map);
            } catch (Exception e) {
                e.printStackTrace();
//                    }
//                }
            }
        } else if (imageTypeInt == 3) {
            fileNdba = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
//            if(!fileNdba.exists()){
//                boolean b = fileNdba.canExecute();
//                if(b){
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileNdba));
                map.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                Log.e(TAG, "saveBitmap: " + fileNdba);
                ivNiadubeian.setImageBitmap(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
//                }
//            }
        } else if (imageTypeInt == 4) {
            fileSfzZm = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
//            if(!fileSfzZm.exists()){
//                boolean b = fileSfzZm.canExecute();
//                if (b){
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileSfzZm));
                map.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                Log.e(TAG, "saveBitmap: " + fileSfzZm);
                ivShefenzhengZhengmian.setImageBitmap(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
//                }
//            }
        } else if (imageTypeInt == 5) {
            fileSfzFm = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
//            if(!fileSfzFm.exists()){
//                boolean b = fileSfzFm.canExecute();
//                if(b){
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileSfzFm));
                map.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
                Log.e(TAG, "saveBitmap: " + fileSfzFm);
                ivShefenzhengFanmian.setImageBitmap(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
//                }
//            }

        }

    }

    @Override
    public void onSelectInforSuccess(LawyerInformationBean informationBean) {
        LawyerInformationBean.InfoBean info = informationBean.getInfo();
        status = info.getStatus();
        name = info.getName();
        age = info.getAge();
        edu = info.getEdu();
        jigou = info.getJigou();
        address = info.getAddress();
        lingyu = info.getLingyu();
        jianjie = info.getJianjie();
        id = info.getId();
        GlideManager.loadImageByUrl(this, info.getPhoto(), ivMianguanzhao);
        tvName.setText(info.getName());
        tvNianling.setText(info.getAge());
        tvXueli.setText(info.getEdu());
        tvZhiyejigou.setText(info.getJigou());
        tvLvshuoaddress.setText(info.getAddress());
        tvShanchanglingyu.setText(info.getLingyu());
        tvGerenjianjie.setText(info.getJianjie());
        GlideManager.loadImageByUrl(this, info.getZhiyezheng(), ivLvshizhiyezheng);
        if (!TextUtils.isEmpty(info.getBeian())) {
            GlideManager.loadImageByUrl(this, info.getBeian(), ivNiadubeian);
        }
        GlideManager.loadImageByUrl(this, info.getCard1(), ivShefenzhengZhengmian);
        GlideManager.loadImageByUrl(this, info.getCard2(), ivShefenzhengFanmian);
        if ("3".equals(status)) {
            tvCommit.setVisibility(View.GONE);
        } else if ("4".equals(status)) {
            tvCommit.setVisibility(View.VISIBLE);
        } else if ("5".equals(status)) {
            tvCommit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLawyerInforUpdate(LawyerRzUpdateBean rzUpdateBean) {
        hideWaitingDialog();
        tvCommit.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onFiled(String error) {
        showCenterToast(error);
    }
}
