package com.example.ibb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.entity.BeautyBean;
import com.example.ibb.entity.BeautyEventBus;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/28.
 */

public class MyPopularBeautyListAdapter extends BaseAdapter {
    private Context context;
    private List<BeautyBean.DataBean> beautyBeenList;

    private  BeautyEventBus beautyEventBus;

    public MyPopularBeautyListAdapter(Context context, List<BeautyBean.DataBean> beautyBeenList) {
        this.context = context;
        this.beautyBeenList = beautyBeenList;
        beautyEventBus = new BeautyEventBus();

    }

    @Override
    public int getCount() {
        return beautyBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return beautyBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PopularBeautyHolder beautyHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.beauty_list_item,null);
            beautyHolder = new PopularBeautyHolder();
            beautyHolder.popularbeauty_title = convertView.findViewById(R.id.popularbeauty_title);
            beautyHolder.beauty_score = convertView.findViewById(R.id.beauty_score);
            beautyHolder.beauty_min = convertView.findViewById(R.id.beauty_min);
            beautyHolder.beauty_max = convertView.findViewById(R.id.beauty_max);
            beautyHolder.beauty_image = convertView.findViewById(R.id.beauty_image);
            beautyHolder.beauty_rb = convertView.findViewById(R.id.beauty_rb);
            convertView.setTag(beautyHolder);
        }else {
            beautyHolder = (PopularBeautyHolder) convertView.getTag();
        }
        final BeautyBean.DataBean beautydataBean = beautyBeenList.get(position);
        beautyHolder.popularbeauty_title.setText(beautydataBean.getName());
        beautyHolder.beauty_score.setText(beautydataBean.getScore() + "分");
        beautyHolder.beauty_min.setText(beautydataBean.getMinPrice() + "");
        beautyHolder.beauty_max.setText(beautydataBean.getMaxPrice() + "");

        if(beautydataBean.isCheck_flg()){
            beautyHolder.beauty_rb.setChecked(true);
        }else {
            beautyHolder.beauty_rb.setChecked(false);
        }


        try {
            String s = URLEncoder.encode(beautydataBean.getImgs().get(0).substring(("http://image.ibbtech.cn/image/".length())-1), "utf-8").replaceAll("\\+", "%20");
            Glide.with(context).load("http://image.ibbtech.cn/image/"+s).error(R.mipmap.ic_launcher).into(beautyHolder.beauty_image);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        final GlobalValue globalValue = new GlobalValue();
        beautyHolder.beauty_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<beautyBeenList.size();i++){
                    beautyBeenList.get(i).setCheck_flg(false);
                }
                beautyBeenList.get(position).setCheck_flg(true);

                notifyDataSetChanged();

                beautyEventBus.setId(beautyBeenList.get(position).getId());
                beautyEventBus.setName(beautyBeenList.get(position).getName());

                EventBus.getDefault().post(beautyEventBus);

                Log.e("TAG","当前点击了第几个checkbox"+position);
            }
        });
        return convertView;
    }

    class PopularBeautyHolder{
        TextView popularbeauty_title;
        TextView beauty_score;
        TextView beauty_min;
        TextView beauty_max;
        ImageView beauty_image;
        CheckBox beauty_rb;
    }
    private class GlobalValue {
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private boolean isCheck;
    }
}
