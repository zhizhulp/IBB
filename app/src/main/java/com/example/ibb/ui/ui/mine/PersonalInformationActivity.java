package com.example.ibb.ui.ui.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.ibb.BuildConfig;
import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.custom_view.SelectIconDialog;
import com.example.ibb.db.AreaBean;
import com.example.ibb.db.CityBean;
import com.example.ibb.db.DBManager;
import com.example.ibb.db.ProvinceBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.FileUtils;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.ToastUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class PersonalInformationActivity extends BaseNetActivity implements View.OnClickListener {


    private ImageView personalxx_back_iv;
    private RelativeLayout setting_nickname;
    private RelativeLayout signature;
    private RelativeLayout gender;
    private RelativeLayout area;
    private LinearLayout linearLayout;
    private String[] sexArry = new String[]{"女", "男", "保密"};
    private TextView gender_et;
    private TextView nickname_edit;
    private TextView edit_gexingqianming;
    private TextView area_et;
    private OptionsPickerView pvOptions;//地址选择器
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<AreaBean>>> options3Items = new ArrayList<>();//区
    private ArrayList<String> Provincestr = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> Citystr = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> Areastr = new ArrayList<>();//区
    private OkHttpClient client;
    private SharedPreferences user;
    private ImageView imHead;
    private File upLoadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        initview();
        initdata();
    }

    protected void initview() {
        linearLayout = new LinearLayout(MyApp.activity);
        //返回键
        personalxx_back_iv = (ImageView) findViewById(R.id.personalxx_back_iv);
        personalxx_back_iv.setOnClickListener(this);
        //设置昵称
        setting_nickname = (RelativeLayout) findViewById(R.id.setting_nickname);
        setting_nickname.setOnClickListener(this);
        //个性签名
        signature = (RelativeLayout) findViewById(R.id.signature);
        signature.setOnClickListener(this);
        //性别
        gender = (RelativeLayout) findViewById(R.id.gender);
        gender.setOnClickListener(this);

        gender_et = (TextView) findViewById(R.id.gender_et);
        edit_gexingqianming = (TextView) findViewById(R.id.edit_gexingqianming);
        nickname_edit = (TextView) findViewById(R.id.nickname_edit);

        //地区
        area = (RelativeLayout) findViewById(R.id.area);
        area.setOnClickListener(this);

        area_et = (TextView) findViewById(R.id.area_et);
        //头像
        imHead = findViewById(R.id.im_head);
        imHead.setOnClickListener(this);
    }

    protected void initdata() {
        user = getSharedPreferences("user", Context.MODE_PRIVATE);

        long id = user.getLong("id", 1);
        final String token = user.getString("token", null);
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token", token)
                        .build();
                return chain.proceed(request);
            }
        }).build();
        //选项选择器
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                final String tx = options1Items.get(options1).getPro_name()
                        + options2Items.get(options1).get(options2).getName()
                        + options3Items.get(options1).get(options2).get(options3).getName();
                area_et.setText(tx);
                FormBody body = new FormBody.Builder()
                        .add("location", tx)
                        .build();
                Request request = new Request.Builder().url(URLApi.setLocation).post(body).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("LGA", "location----" + response.body().string());
                        if (response.isSuccessful()) {
                            AppConfig.getInstance().putString("location", tx);
                        }
                    }
                });

            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(16)//确定和取消文字大小
                .setTitleSize(18)//标题文字大小
                .setContentTextSize(15)
                .build();
        // 获取数据库
        SQLiteDatabase db = DBManager.getdb(getApplication());
        //省
        @SuppressLint("Recycle") Cursor cursor = db.query("province", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int pro_id = cursor.getInt(0);
            String pro_code = cursor.getString(1);
            String pro_name = cursor.getString(2);
            String pro_name2 = cursor.getString(3);
            ProvinceBean provinceBean = new ProvinceBean(pro_id, pro_code, pro_name, pro_name2);
            options1Items.add(provinceBean);//添加一级目录
            Provincestr.add(cursor.getString(2));
            //查询二级目录，市区
            @SuppressLint("Recycle") Cursor cursor1 = db.query("city", null, "province_id=?", new String[]{pro_id + ""}, null, null,
                    null);
            ArrayList<CityBean> cityBeanList = new ArrayList<>();
            ArrayList<String> cityStr = new ArrayList<>();
            //地区集合的集合（注意这里要的是当前省份下面，当前所有城市的地区集合我去）
            ArrayList<ArrayList<AreaBean>> options3Items_03 = new ArrayList<>();
            ArrayList<ArrayList<String>> options3Items_str = new ArrayList<>();
            while (cursor1.moveToNext()) {
                int cityid = cursor1.getInt(0);
                int province_id = cursor1.getInt(1);
                String code = cursor1.getString(2);
                String name = cursor1.getString(3);
                String provincecode = cursor1.getString(4);
                CityBean cityBean = new CityBean(cityid, province_id, code, name, provincecode);
                //添加二级目录
                cityBeanList.add(cityBean);
                cityStr.add(cursor1.getString(3));
                //查询三级目录
                @SuppressLint("Recycle")
                Cursor cursor2 = db.query("area", null, "city_id=?", new String[]{cityid + ""}, null, null, null);
                ArrayList<AreaBean> areaBeanList = new ArrayList<>();
                ArrayList<String> areaBeanstr = new ArrayList<>();
                while (cursor2.moveToNext()) {
                    int areaid = cursor2.getInt(0);
                    int city_id = cursor2.getInt(1);
                    String areaname = cursor2.getString(3);
                    String citycode = cursor2.getString(4);
                    AreaBean areaBean = new AreaBean(areaid, city_id, areaname, citycode);
                    areaBeanList.add(areaBean);
                    areaBeanstr.add(cursor2.getString(3));
                }
                options3Items_str.add(areaBeanstr);//本次查询的存储内容
                options3Items_03.add(areaBeanList);
            }
            options2Items.add(cityBeanList);//增加二级目录数据
            Citystr.add(cityStr);
            options3Items.add(options3Items_03);//添加三级目录
            Areastr.add(options3Items_str);
        }
        pvOptions.setPicker(Provincestr, Citystr, Areastr);
        pvOptions.setSelectOptions(0, 0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回键
            case R.id.personalxx_back_iv:
                finish();
                break;
            //设置昵称
            case R.id.setting_nickname:
                startActivity(new Intent(MyApp.activity, SettingNicknameActivity.class));
                break;
            //个性签名
            case R.id.signature:
                Intent it = new Intent(MyApp.activity, SignatureActivity.class);
                it.putExtra("signa", edit_gexingqianming.getText().toString());
                startActivityForResult(it, 10002);
                break;
            //性别
            case R.id.gender:
                showSexChooseDialog(AppConfig.getInstance().getInt("sex", 0));
                break;
            //地区
            case R.id.area:
                //弹出地区选择器
                pvOptions.show();
                break;
            case R.id.gender_et:
                showSexChooseDialog(AppConfig.getInstance().getInt("sex", 0));
                break;
            case R.id.im_head://选择用户头像
                File file = FileUtils.getAppFile(this, Environment.DIRECTORY_PICTURES);
                upLoadFile = new File(file, "ibb" + System.currentTimeMillis() + ".jpg");
                SelectIconDialog dialog = new SelectIconDialog(this, upLoadFile, R.style.dialog);
                dialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10002 && resultCode == 10003) {
            edit_gexingqianming.setText(data.getStringExtra("signa"));
            return;
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FinalUtils.REQUEST_CAMERA_ICON:
                    Uri uri;
                    if (Build.VERSION.SDK_INT > 23) {
                        uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", upLoadFile);
                    } else {
                        uri = Uri.fromFile(upLoadFile);
                    }
                    cropImage(uri, Uri.fromFile(upLoadFile));
                    break;
                case FinalUtils.REQUEST_ALBUM_ICON:
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();
                        Uri uri2;
                        if (Build.VERSION.SDK_INT > 23) {
                            uri2 = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(picturePath));
                        } else {
                            uri2 = Uri.parse("file://" + picturePath);
                        }
                        cropImage(uri2, Uri.fromFile(upLoadFile));
                    }
                    break;
                case FinalUtils.REQUEST_CROP:
                    Luban.with(this).load(upLoadFile)
                            .ignoreBy(350)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }

                                @Override
                                public void onSuccess(File file) {
                                    imHead.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                                    upLoadFile = file;
                                    requestUploadImage();
                                }

                                @Override
                                public void onError(Throwable e) {
                                }
                            }).launch();
                    break;
                default:
                    break;
            }
        }

    }

    private void requestSetPortrait(String ss) {
        com.yanzhenjie.nohttp.rest.Request request = buildRequest(URLApi.setPortrait, HttpBaseEntity.class);
        request.add("portrait", ss);
        doRequest(1, request);
    }

    private void requestUploadImage() {
        com.yanzhenjie.nohttp.rest.Request request = buildPostListRequest(URLApi.uploadImage, String.class);
        request.add("file", upLoadFile);
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            requestSetPortrait(FinalUtils.IMGS_BASE + ((List<String>) data.getData()).get(0));
        } else if (what == 1) {
            ToastUtils.show("头像设置成功");
        }
    }

    //性别选择器
    private void showSexChooseDialog(int which) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyApp.activity);//自定义对话框
        builder.setSingleChoiceItems(sexArry, which, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, final int which) {// which是被选中的位置
                gender_et.setText(sexArry[which]);
                FormBody body = new FormBody.Builder()
                        .add("sex", which + "")
                        .build();
                Request request = new Request.Builder().url(URLApi.setSex).post(body).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("LGA", "sex------" + response.body().string());
                        if (response.isSuccessful()) {
                            AppConfig.getInstance().putInt("sex", which);
                        }
                    }
                });

                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });

        builder.show();// 让弹出框显示
    }

    //裁剪图片
    private void cropImage(Uri inUri, Uri outUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inUri, "image/*");
        //是否裁剪
        intent.putExtra("crop", "true");
        //设置xy的裁剪比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置输出的宽高
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 720);
        intent.putExtra("scale", true);//去除黑边
        intent.putExtra("scaleUpIfNeeded", true);//去除黑边
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //输入图片的Uri，指定以后，可以在这个uri获得图片
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        //是否返回图片数据，可以不用，直接用uri就可以了
        intent.putExtra("return-data", false);
        //设置输入图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //是否关闭面部识别
        intent.putExtra("noFaceDetection", true); // no face detection
        //启动
        startActivityForResult(intent, FinalUtils.REQUEST_CROP);
    }

    private void setUser() {
        AppConfig instance = AppConfig.getInstance();
        String token = instance.getString("token", null);
        if (token != null) {//设置用户信息
            nickname_edit.setText(instance.getString("nickname", null));
            edit_gexingqianming.setText(instance.getString("signature", null));
            area_et.setText(instance.getString("location", null));
            Picasso.with(getApplicationContext()).load(instance.getString("portrait", null)).placeholder(R.mipmap.b3).into(imHead);
            int sex = instance.getInt("sex", 0);
            if (sex == 0) {
                gender_et.setText("女");
            } else if (sex == 1) {
                gender_et.setText("男");
            } else {
                gender_et.setText("保密");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUser();
    }
}
