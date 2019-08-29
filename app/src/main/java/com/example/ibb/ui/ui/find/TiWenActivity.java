package com.example.ibb.ui.ui.find;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.MyEditText;

/**
 * 我要提问
 */
public class TiWenActivity extends BaseActivity implements View.OnClickListener {

    private ImageView tiwen_back_iv;
    private EditText tiwen_title_et;
    private MyEditText tiwen_titlebody_et;
    private ImageView tiwen_picture_iv;
    private TextView tiwen_publish_tv;
    private Button add_takeGallery_popup;
    private Button add_takePic_popup;
    private static final int PHOTO_SUCCESS = 1;
    private static final int CAMERA_SUCCESS = 2;
    private Button add_return_popup;

    @Override
    protected void initview() {
        //返回键
        tiwen_back_iv = (ImageView)findViewById(R.id.tiwen_back_iv);
        tiwen_back_iv.setOnClickListener(this);
        //标题
        tiwen_title_et = (EditText)findViewById(R.id.tiwen_title_et);
        //内容
        tiwen_titlebody_et = (MyEditText)findViewById(R.id.tiwen_titlebody_et);
        //图片
        tiwen_picture_iv = (ImageView)findViewById(R.id.tiwen_picture_iv);
        //发布按钮
        tiwen_publish_tv = (TextView)findViewById(R.id.tiwen_publish_tv);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_ti_wen;
    }

    @Override
    public void onClick(View v) {
        String tiwentitle = tiwen_title_et.getText().toString().trim();
        String tiwencontent = tiwen_titlebody_et.getText().toString().trim();

        switch (v.getId()){
            case R.id.tiwen_back_iv:
                finish();
                break;
            //选择照片
            case R.id.tiwen_picture_iv:

                View view = LayoutInflater.from(MyApp.activity).inflate(R.layout.add_popupwindow, null);
                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.showAtLocation(tiwen_picture_iv, Gravity.BOTTOM, 0, 0);//parent view随意

                /**
                 * 找id
                 * 打开相册
                 */
                add_takeGallery_popup = (Button) view.findViewById(R.id.add_takeGallery_popup);
                add_takeGallery_popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                        getImage.addCategory(Intent.CATEGORY_OPENABLE);
                        getImage.setType("image/*");
                        startActivityForResult(getImage, PHOTO_SUCCESS);
                    }
                });
                /**
                 * 找id
                 * 打开相机
                 */
                add_takePic_popup = (Button) view.findViewById(R.id.add_takePic_popup);
                add_takePic_popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (CAMERA_FLG) {
                            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                            startActivityForResult(getImageByCamera, CAMERA_SUCCESS);
                        } else {
                            Toast.makeText(MyApp.activity, "相机权限已被禁止,请在设置中打开", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                add_return_popup = (Button) view.findViewById(R.id.add_return_popup);
                add_return_popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }

    //判断当前相机权限  --  用户是否同意   --  true为用户同意   --  false是不同意   在onRequestPermissionsResult方法中修改这个值
    private boolean CAMERA_FLG = true;
}
