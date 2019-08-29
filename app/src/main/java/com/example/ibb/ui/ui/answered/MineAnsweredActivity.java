package com.example.ibb.ui.ui.answered;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibb.BuildConfig;
import com.example.ibb.R;
import com.example.ibb.adapter.MyMineAnsweredListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.custom_view.MyEditText;
import com.example.ibb.custom_view.SelectIconDialog;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.add.SearchResultsActivity;
import com.example.ibb.utils.FileUtils;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.IStringUtils;
import com.example.ibb.utils.ToastUtils;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MineAnsweredActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView mineanswered_back_iv;
    private TextView mineanswered_publish_tv;
    private MyEditText header_titlebody_et;
    private ImageView header_picture_iv;
    private RelativeLayout header_relativebutton;
    private ExpandableListView mineanswered_listview;
    List<List<MineAnsweredBean>> bean = new ArrayList<>();
    List<MineAnsweredBean> bean_add = new ArrayList<>();
    private List<MineAnsweredBean> answeredBeenList = new ArrayList<>();
    private MyMineAnsweredListAdapter answeredListAdapter;
    private File upLoadFile;
    private long id;
    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_layout);
        id = getIntent().getLongExtra("id", -1000);
        initview();
    }

    protected void initview() {
//        返回键
        mineanswered_back_iv = (ImageView) findViewById(R.id.mineanswered_back_iv);
        mineanswered_back_iv.setOnClickListener(this);
        //发布
        mineanswered_publish_tv = (TextView) findViewById(R.id.mineanswered_publish_tv);
        mineanswered_publish_tv.setOnClickListener(this);

        //ListView
        mineanswered_listview = (ExpandableListView) findViewById(R.id.mineanswered_listview);
        answeredListAdapter = new MyMineAnsweredListAdapter(this, bean);
        mineanswered_listview.setAdapter(answeredListAdapter);


        View view = LayoutInflater.from(this).inflate(R.layout.header_layout, null);
        mineanswered_listview.addHeaderView(view);

        header_titlebody_et = (MyEditText) view.findViewById(R.id.header_titlebody_et);
        //添加商品按钮
        header_relativebutton = (RelativeLayout) view.findViewById(R.id.header_relativebutton);
        header_relativebutton.setOnClickListener(this);
        //选择图库照片
        header_picture_iv = (ImageView) view.findViewById(R.id.header_picture_iv);
        header_picture_iv.setOnClickListener(this);

    }

    public static void startAnsApply(Context context, long id, int requestCode) {
        Intent intent = new Intent(context, MineAnsweredActivity.class);
        intent.putExtra("id", id);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    private void requestImages() {
        Request request = buildPostListRequest(URLApi.uploadImage, String.class);
        request.add("file", new FileBinary(upLoadFile));
        doRequest(0, request);
    }

    private void requestApplyAnswer(String content) {
        Log.d(TAG, "requestApplyAnswer: " + content);
        Request request = buildRequest(URLApi.answerPOST, HttpBaseEntity.class);
        request.add("questionId", id);
        request.add("content", content);
        doRequest(1, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            urls.addAll(((List<String>) data.getData()));
        } else if (what == 1) {
            ToastUtils.show("发布成功");
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mineanswered_back_iv:
                finish();
                break;
            case R.id.mineanswered_publish_tv:
                String content = header_titlebody_et.getText().toString().trim();
                Log.d(TAG, "onClick: " + content);
                if (IStringUtils.isEmpty(content)) {
                    Toast.makeText(MyApp.activity, "回答不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (content.length() > 30000) {
                        Toast.makeText(MyApp.activity, "回答描述不能超过30000个字", Toast.LENGTH_SHORT).show();
                    } else {
                        mineanswered_publish_tv.setBackgroundResource(R.drawable.text_shape);
                        //212132132[local]1[/local]asdasd[local]1[/local]     1
                        //[local]1[/local]212132132[local]1[/local]asdasd[local]1[/local]    2
                        //212132132[local]1[/local]asdasd    3
                        if (content.contains("[local]1[/local]")) {
                            String[] split = content.split("\\[local\\]1\\[/local\\]");
                            StringBuilder sb = new StringBuilder();
                            if (urls.size()==split.length){
                                for (int i = 0; i < split.length; i++) {
                                    sb.append(split[i]).append(FinalUtils.IMGS_BASE).append(urls.get(i));
                                }
                            }else if(urls.size()>split.length){
                                for (int i = 0; i < urls.size(); i++) {
                                    sb.append(FinalUtils.IMGS_BASE).append(urls.get(i)).append(split[i]);
                                }
                            }else {
                                for (int i = 0; i < split.length; i++) {
                                    if (i==split.length-1){
                                        sb.append(split[i]);
                                    }else {
                                        sb.append(split[i]).append(FinalUtils.IMGS_BASE).append(urls.get(i));
                                    }
                                }
                            }

                            content = sb.toString();
                        }
                        requestApplyAnswer(content);
                    }
                }
                break;

            case R.id.header_picture_iv:
                File file = FileUtils.getAppFile(this, Environment.DIRECTORY_PICTURES);
                upLoadFile = new File(file, "ibb" + System.currentTimeMillis() + ".jpg");
                SelectIconDialog dialog = new SelectIconDialog(this, upLoadFile, R.style.dialog);
                dialog.show();
                break;

            case R.id.header_relativebutton:
                Intent it = new Intent(MineAnsweredActivity.this, SearchResultsActivity.class);
                startActivityForResult(it, 1001);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                                    upLoadFile = file;
                                    ImageSpan imageSpan = new ImageSpan(MyApp.activity, BitmapFactory.decodeFile(file.getAbsolutePath()));
                                    //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                                    SpannableString spannableString = new SpannableString("[local]" + 1 + "[/local]");
                                    // 用ImageSpan对象替换face
                                    spannableString.setSpan(imageSpan, 0, "[local]1[local]".length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //将选择的图片追加到EditText中光标所在位置
                                    int index = header_titlebody_et.getSelectionStart(); //获取光标所在位置
                                    Editable edit_text = header_titlebody_et.getEditableText();
                                    if (index < 0 || index >= edit_text.length()) {
                                        edit_text.append(spannableString);
                                    } else {
                                        edit_text.insert(index, spannableString);
                                    }
                                    edit_text.append("\n");
                                    header_titlebody_et.setSelection(edit_text.length());
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
        } else if (resultCode == 1002) {
            MineAnsweredBean ben = (MineAnsweredBean) data.getSerializableExtra("list_check");
            bean_add.add(ben);
            answeredListAdapter.notifyDataSetChanged();
        }
    }

    //裁剪图片
    private void cropImage(Uri inUri, Uri outUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inUri, "image/*");
        //是否裁剪
        intent.putExtra("crop", "true");
        /*//设置xy的裁剪比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置输出的宽高
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 720);*/
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
}
