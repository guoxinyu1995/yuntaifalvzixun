package com.ytfu.yuntaifawu.ui.mine.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PictureUploadBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetBirthdayBean;
import com.ytfu.yuntaifawu.ui.mine.present.GrzxPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IGrzxView;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DateUtil;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Auther gxy
 * @Date 2019/12/27
 * @Des 个人信息
 */
public class ActivityGrzx extends BaseActivity<IGrzxView, GrzxPresenter> implements IGrzxView, TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.iv_rg)
    ImageView ivRg;
    @BindView(R.id.cl_header)
    ConstraintLayout clHeader;
    @BindView(R.id.tv_bd)
    TextView tvBd;
    @BindView(R.id.iv_rg1)
    ImageView ivRg1;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.cl_bd_sjh)
    ConstraintLayout clBdSjh;
    @BindView(R.id.tv_xg)
    TextView tvXg;
    @BindView(R.id.iv_rg2)
    ImageView ivRg2;
    @BindView(R.id.cl_xg_pwd)
    ConstraintLayout clXgPwd;
    @BindView(R.id.tv_yx)
    TextView tvYx;
    @BindView(R.id.iv_rg3)
    ImageView ivRg3;
    @BindView(R.id.tv_email_num)
    TextView tvEmailNum;
    @BindView(R.id.cl_bd_email)
    ConstraintLayout clBdEmail;
    @BindView(R.id.tv_nc)
    TextView tvNc;
    @BindView(R.id.iv_rg4)
    ImageView ivRg4;
    @BindView(R.id.tv_nc_name)
    TextView tvNcName;
    @BindView(R.id.cl_nc)
    ConstraintLayout clNc;
    @BindView(R.id.tv_sr)
    TextView tvSr;
    @BindView(R.id.iv_rg5)
    ImageView ivRg5;
    @BindView(R.id.tv_sr_name)
    TextView tvSrName;
    @BindView(R.id.cl_sr)
    ConstraintLayout clSr;
    @BindView(R.id.tv_jj)
    TextView tvJj;
    @BindView(R.id.iv_rg6)
    ImageView ivRg6;
    @BindView(R.id.tv_jj_value)
    TextView tvJjValue;
    @BindView(R.id.cl_jianjie)
    ConstraintLayout clJianjie;
    private String uid;
    private int mobile_status;
    private int pwd_status;
    private int year, month, day;
    private DatePickerDialog mDatePickerDialog;

    /**
     * 上传头像
     */
    private String[] items = {"拍照", "相册选择"};
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private static final int CHOOSE_PICTURE = 0;
    private static final int TAKE_PICTURE = 1;
    private String user_login;
    private String user_email;
    private String signature;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_grzx;
    }

    @Override
    protected GrzxPresenter createPresenter() {
        return new GrzxPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setGrzx(map);
    }

    private void getData() {

    }

    @Override
    protected void initView() {
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        tvTopTitle.setText("个人信息");
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setGrzx(map);
    }

    @OnClick({R.id.iv_fanhui, R.id.cl_header, R.id.cl_bd_sjh, R.id.cl_xg_pwd, R.id.cl_bd_email, R.id.cl_nc, R.id.cl_sr, R.id.cl_jianjie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.cl_header:
// 头像
                AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                    @Override
                    public void onPermissionGranted() {
                        if (null == takePhoto) {
                            takePhoto = getTakePhoto();
                        }
                        showCropPicDialog();
                    }
                }, Permission.Group.CAMERA, Permission.Group.STORAGE);
                break;
            case R.id.cl_bd_sjh:
                if (mobile_status == 0) {
                    startActivity(new Intent(ActivityGrzx.this, ActivityBdPhoneNum.class));
                }else if(mobile_status == 1){
                    showCenterToast("你已绑定过手机号");
                }
                break;
            case R.id.cl_xg_pwd:
                if (pwd_status == 0) {
                    startActivity(new Intent(ActivityGrzx.this, ActivitySetPwd.class));
                } else if (pwd_status == 1) {
                    startActivity(new Intent(ActivityGrzx.this, ActivityUpDataPwd.class));
                }
                break;
            case R.id.cl_bd_email:
                Intent intentEmail = new Intent(ActivityGrzx.this, ActivitySetEmail.class);
                intentEmail.putExtra("user_email",user_email);
                startActivity(intentEmail);
                break;
            case R.id.cl_nc:
                Intent intentName = new Intent(ActivityGrzx.this, ActivitySetName.class);
                intentName.putExtra("user_login",user_login);
                startActivity(intentName);
                break;
            case R.id.cl_sr:
                showDateAndTimePickerDialog();
                break;
            case R.id.cl_jianjie:
                Intent intentJianJie = new Intent(ActivityGrzx.this, ActivityGrjj.class);
                intentJianJie.putExtra("signature",signature);
                startActivity(intentJianJie);
                break;
            default:
                break;
        }
    }
    //选择相机相册弹框
    private void showCropPicDialog() {
        File file = null;
//        if(Build.VERSION.SDK_INT >=29){
            file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "/temp/" + System.currentTimeMillis() + ".jpg");
//        }else{
//            file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
//        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
//        //设置裁剪参数
        CropOptions cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        CompressConfig config = new CompressConfig.Builder()
                .setMaxSize(50*1024)
                .setMaxPixel(800)
                .create();
        takePhoto.onEnableCompress(config,true);
        AlertDialog alertDialog = new AlertDialog.Builder(this).
                setTitle("上传头像").
                setItems(items, (dialog, which) -> {
                    switch (which) {
                        case CHOOSE_PICTURE:
                            // 拍照+裁剪
//                            takePhoto.onPickFromCapture(imageUri);
                            takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                            break;
                        case TAKE_PICTURE:
                            // 相册选择+裁剪
                            takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
//                            takePhoto.onPickFromGallery();
                            break;
                        default:
                            break;
                    }
                })
                .setPositiveButton("取消", null)
                .create();
        alertDialog.show();
        // 自定义取消按钮的颜色
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(CommonUtil.getColor(R.color.primaryColor));
    }

    private TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    private void showDateAndTimePickerDialog() {
        // 实例化日期类
        long curTime = DateUtil.getCurTimeLong() + 15 * 60 * 1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date dateTime = new Date(curTime);
        String formatTime = simpleDateFormat.format(dateTime);
        year = Integer.valueOf(formatTime.substring(0, 4));
        month = Integer.valueOf(formatTime.substring(5, 7));
        day = Integer.valueOf(formatTime.substring(8, 10));
        if (mDatePickerDialog == null) {
            mDatePickerDialog = new DatePickerDialog(ActivityGrzx.this, R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                    tvSrName.setText(date);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("birthday", date);
                    getPresenter().setBirthday(map);
                }
            }, year, month, day);
            mDatePickerDialog.getDatePicker().setMaxDate(DateUtil.getCurTimeLong());
        }
        mDatePickerDialog.show();
    }

    @Override
    public void onGrzxSuccess(InformationBean informationBean) {
        if (informationBean != null || informationBean.getUser() != null) {
            mobile_status = informationBean.getMobile_status();
            pwd_status = informationBean.getPwd_status();
            if(mobile_status == 0){
                clXgPwd.setVisibility(View.GONE);
            }else if(mobile_status == 1){
                clXgPwd.setVisibility(View.VISIBLE);
                if (pwd_status == 0) {
                    tvXg.setText("设置密码");
                } else if (pwd_status == 1) {
                    tvXg.setText("修改密码");
                }
            }
            InformationBean.UserBean user = informationBean.getUser();
            user_login = user.getUser_login();
            user_email = user.getUser_email();
            signature = user.getSignature();
            tvEmailNum.setText(user.getUser_email());
            tvNcName.setText(user.getUser_login());
            tvSrName.setText(user.getBirthday());
            tvPhoneNum.setText(user.getMobile());
            tvJjValue.setText(user.getSignature());
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                    .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                    .error(R.drawable.touxiang);//图片加载失败后，显示的图片
            Glide.with(this).load(user.getAvatar())
                    .apply(options)
                    .into(ivHeader);
        }
    }

    @Override
    public void onBirthdaySuccess(SetBirthdayBean setBirthdayBean) {
        if (setBirthdayBean != null) {
            int status = setBirthdayBean.getStatus();
            if (status == 1) {
                showToast("修改成功");
            } else {
                showToast("修改失败");
            }
        }
    }

    @Override
    public void onPictureUpLoadSuccess(PictureUploadBean uploadBean) {
        if (uploadBean != null) {
            int status = uploadBean.getStatus();
            switch (status) {
                case 1:
                    ToastUtil.showToast("上传成功");
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                            .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                            .error(R.drawable.touxiang);//图片加载失败后，显示的图片
                    Glide.with(this).load(uploadBean.getSrc())
                            .apply(options)
                            .into(ivHeader);
                    break;
                case 2:
                    ToastUtil.showToast(uploadBean.getMsg());
                    break;
                case 3:
                    ToastUtil.showToast("头像为空");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onFiled() {

    }

    @Override
    public void takeSuccess(TResult result) {
        File file = new File(result.getImage().getCompressPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody uidBody = RequestBody.create(MediaType.parse("uid"), uid);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        getPresenter().upLoadImage(uidBody, multipartBody);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Logger.e("上传头像takeFail(): " + msg);
    }

    @Override
    public void takeCancel() {
        Logger.e("上传头像takeCancel(): " + "已取消");
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 添加takePhoto回调
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
    }
}
