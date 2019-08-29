package com.example.ibb.ui.ui.add;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibb.BuildConfig;
import com.example.ibb.R;
import com.example.ibb.adapter.AddListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.custom_view.MyEditText;
import com.example.ibb.custom_view.SelectIconDialog;
import com.example.ibb.entity.AddEventBusBean;
import com.example.ibb.entity.MyEventBusBean_ForADD;
import com.example.ibb.entity.PushBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.utils.FileUtils;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.IStringUtils;
import com.example.ibb.utils.InputMethodUtils;
import com.example.ibb.utils.PermissionUtils;
import com.yanzhenjie.nohttp.FileBinary;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends BaseNetFragment implements View.OnClickListener {

    private ImageView add_back_iv;
    private RelativeLayout add_relativebutton;
    private EditText add_title_et;
    private TextView add_publish_tv;
    private Toast toast = null;
    private ImageView add_picture_iv;
    private EditText add_titlebody_et;
    private TextView add_tv;
    private ImageView add_iv;
    private ImageView addtwo_finish_iv;
    private File file;
    private ListView add_listview;
    List<PushBean.AttrsBean> pb = new ArrayList<>();
    private ArrayList<String> arrslist = new ArrayList<>();
    private File upLoadFile;

    @Override
    public void onResume() {
        super.onResume();
        PermissionUtils.getPermission(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
    }

    protected void initview(View view) {
        add_back_iv = (ImageView) view.findViewById(R.id.add_back_iv);
        add_title_et = (EditText) view.findViewById(R.id.add_title_et);
        add_titlebody_et = (MyEditText) view.findViewById(R.id.add_titlebody_et);
        add_relativebutton = (RelativeLayout) view.findViewById(R.id.add_relativebutton);
        add_picture_iv = (ImageView) view.findViewById(R.id.add_picture_iv);
        add_tv = (TextView) view.findViewById(R.id.add_tv);
        add_iv = (ImageView) view.findViewById(R.id.add_iv);

        add_title_et.setFocusable(true);
        add_title_et.setOnClickListener(this);
        add_back_iv.setOnClickListener(this);
        add_titlebody_et.setOnClickListener(this);
        add_relativebutton.setOnClickListener(this);
        add_picture_iv.setOnClickListener(this);
        add_iv.setOnClickListener(this);

        //发布按钮
        add_publish_tv = (TextView) view.findViewById(R.id.add_publish_tv);
        add_publish_tv.setOnClickListener(this);

        //ListView
        add_listview = (ListView) view.findViewById(R.id.add_listview);

        add_title_et.setFilters(new InputFilter[]{new MaxTextLengthFilter(38)});

        add_titlebody_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int num = s.length();
                if (num > 100) {
                    add_tv.setVisibility(View.VISIBLE);
                    add_tv.setText(num + "/140");
                    if (num > 140) {
                        add_tv.setTextColor(Color.RED);
                    }
                } else {
                    add_tv.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String title = add_title_et.getText().toString().trim();
        String content = add_titlebody_et.getText().toString().trim();

        switch (v.getId()) {
            case R.id.add_back_iv:
                getActivity().finish();
                break;

            case R.id.add_iv:
                View addview = LayoutInflater.from(getContext()).inflate(R.layout.addtwo_popupwindow, null);
                final PopupWindow addpopupWindow = new PopupWindow(addview, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, true);
                addpopupWindow.setOutsideTouchable(true);
                addpopupWindow.setBackgroundDrawable(new ColorDrawable());
                addpopupWindow.showAtLocation(add_iv, Gravity.CENTER, 0, 0);//parent view随意
                addtwo_finish_iv = (ImageView) addview.findViewById(R.id.addtwo_finish_iv);
                addtwo_finish_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addpopupWindow.dismiss();
                    }
                });
                break;

            case R.id.add_relativebutton:
                startActivity(new Intent(MyApp.activity, ChooseProductActivity.class));
                break;

            //发布按钮
            case R.id.add_publish_tv:
                if (IStringUtils.isEmpty(title)) {
                    Toast.makeText(MyApp.activity, "标题不能为空", Toast.LENGTH_SHORT).show();
                } else if (title.length() < 5) {
                    Toast.makeText(MyApp.activity, "问题字数最少5个字哦！", Toast.LENGTH_SHORT).show();
                } else {
                    add_publish_tv.setBackgroundResource(R.drawable.text_shape);

                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("token", getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", ""))
                                    .build();
                            return chain.proceed(request);
                        }
                    }).build();

                    PushBean push = new PushBean();
                    push.setProductId(1);

                    PushBean.AttrsBean attrsBean = new PushBean.AttrsBean();
                    attrsBean.setAttributeId(1);
                    pb.add(attrsBean);

                    PushBean.AttrsBean attrsBean1 = new PushBean.AttrsBean();
                    attrsBean1.setAttributeId(2);
                    pb.add(attrsBean1);

                    push.attrs.addAll(pb);

                    StringBuilder str = new StringBuilder("[");
                    StringBuffer stringBuffer = new StringBuffer();

                    if (arrslist.size() > 0) {
                        for (int i = 0; i < arrslist.size(); i++) {
                            stringBuffer.append(content.replace("[local]1[/local]", "http://image.ibbtech.cn" + arrslist.get(i)));
                        }
                    } else {
                        stringBuffer.append(content);
                    }
                    str.append("]");
                    Log.e("-------", str.toString());
                    FormBody body = new FormBody.Builder()
                            .add("title", title)
                            .add("content", stringBuffer.toString())
                            .add("products", str.toString()).build();
                    Request request = new Request.Builder().url(URLApi.publishstring).post(body).build();
                    Call call = client.newCall(request);
                    showDialog();
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("TAG", "add失败" + e.getMessage());
                            hideDialog();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            hideDialog();
                            String string = response.body().string();
                            Log.i("TAG", "add成功" + string);
                            if (response.isSuccessful()){
                                if (getActivity() != null) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MyApp.activity, "发布成功", Toast.LENGTH_LONG).show();
                                            //发送事件
                                            EventBus.getDefault().post(new AddEventBusBean());
                                            InputMethodUtils.closeSoftKeyboard(getActivity());
                                        }
                                    });
                                }
                            }

                        }
                    });
                }
                break;

            case R.id.add_picture_iv:
                File file = FileUtils.getAppFile(getActivity(), Environment.DIRECTORY_PICTURES);
                upLoadFile = new File(file, "ibb" + System.currentTimeMillis() + ".jpg");
                SelectIconDialog dialog = new SelectIconDialog(this, upLoadFile, R.style.dialog);
                dialog.show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case FinalUtils.REQUEST_CAMERA_ICON:
                    Uri uri;
                    if (Build.VERSION.SDK_INT > 23) {
                        uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", upLoadFile);
                    } else {
                        uri = Uri.fromFile(upLoadFile);
                    }
                    cropImage(uri, Uri.fromFile(upLoadFile));
                    break;
                case FinalUtils.REQUEST_ALBUM_ICON:
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();
                        Uri uri2;
                        if (Build.VERSION.SDK_INT > 23) {
                            uri2 = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", new File(picturePath));
                        } else {
                            uri2 = Uri.parse("file://" + picturePath);
                        }
                        cropImage(uri2, Uri.fromFile(upLoadFile));
                    }
                    break;
                case FinalUtils.REQUEST_CROP:
                    Luban.with(getActivity()).load(upLoadFile)
                            .ignoreBy(350)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }

                                @Override
                                public void onSuccess(File file) {
                                    upLoadFile = file;
                                    ImageSpan imageSpan = new ImageSpan(MyApp.activity, BitmapFactory.decodeFile(file.getAbsolutePath()));
                                    //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                                    SpannableString spannableString = new SpannableString("[local]" + 1 + "[/local]");
                                    // 用ImageSpan对象替换face
                                    spannableString.setSpan(imageSpan, 0, "[local]1[local]".length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //将选择的图片追加到EditText中光标所在位置
                                    int index = add_titlebody_et.getSelectionStart(); //获取光标所在位置
                                    Editable edit_text = add_titlebody_et.getEditableText();
                                    if (index < 0 || index >= edit_text.length()) {
                                        edit_text.append(spannableString);
                                    } else {
                                        edit_text.insert(index, spannableString);
                                    }
                                    requestImages();
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
    private void requestImages() {
        com.yanzhenjie.nohttp.rest.Request request = buildPostListRequest(URLApi.uploadImage, String.class);
        request.add("file", new FileBinary(upLoadFile));
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            arrslist.addAll(((List<String>) data.getData()));
        }
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

    /**
     * 标题限制字数
     */
    private class MaxTextLengthFilter implements InputFilter {

        private final int mMaxLength;

        public MaxTextLengthFilter(int max) {
            mMaxLength = max - 1;
            toast = Toast.makeText(MyApp.activity, "字数不能超过38位", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int keep = mMaxLength - (dest.length() - (dend - dstart));
            if (keep < (end - start)) {
                toast.show();

            }
            if (keep <= 0) {
                return "";
            } else if (keep >= end - start) {
                return null;
            } else {
                return source.subSequence(start, start + keep);
            }
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MyEventBusBean_ForADD myEventBusBean_forADD) {
        List<String> delivery = myEventBusBean_forADD.getDelivery();

        for (int i = 0; i < delivery.size(); i++) {
            Log.e("传过来的集合", delivery.get(i));
        }
        AddListAdapter addListAdapter = new AddListAdapter(MyApp.activity, delivery);
        add_listview.setAdapter(addListAdapter);
    }
}
