package com.example.ibb.splash;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseActivity {

    // 图片
    private int[] imageView = { R.drawable.loginbackground, R.drawable.black_background,
            R.drawable.loginbackground, R.drawable.black_background };

    private ViewPager guide_viewpager;
    // 底部小点的图片
    private LinearLayout llPoint;
    //立即进入按钮
    private TextView guide_tv;
    private List<View> viewList;

    @Override
    protected void initview() {
        guide_viewpager =(ViewPager)findViewById(R.id.guide_viewpager);
        llPoint =(LinearLayout)findViewById(R.id.llPoint);
        guide_tv =(TextView)findViewById(R.id.guide_tv);
    }

    @Override
    protected void initdata() {
        //进入按钮
        guide_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PageManage.toPageKeepOldPageState(PageDataKey.login);
                finish();
            }
        });
        
        // 2.监听当前显示的页面，将对应的小圆点设置为选中状态，其它设置为未选中状态  
        guide_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                monitorPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addView();
        addPoint();
    }

    /**
     * 添加图片到view
     */
    private void addView() {
        viewList = new ArrayList<>();
        // 将imageview添加到view
       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < imageView.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(imageView[i]);
            viewList.add(iv);
        }
        // 加入适配器
        guide_viewpager.setAdapter(new GuideViewAdapter(viewList));

    }

    /**
     * 添加小圆点
     */
    private void addPoint() {
        // 1.根据图片多少，添加多少小圆点
        for (int i = 0; i < imageView.length; i++) {
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (i < 1) {
                pointParams.setMargins(0, 0, 0, 0);
            } else {
                pointParams.setMargins(10, 0, 0, 0);
            }
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(pointParams);
            iv.setBackgroundResource(R.mipmap.report_unchecked);
            llPoint.addView(iv);
        }
        llPoint.getChildAt(0).setBackgroundResource(R.mipmap.report_unchecked);

    }

    /**
     * 判断小圆点
     *
     * @param position
     */
    private void monitorPoint(int position) {
        for (int i = 0; i < imageView.length; i++) {
            if (i == position) {
                llPoint.getChildAt(position).setBackgroundResource(
                        R.mipmap.report_unchecked);
            } else {
                llPoint.getChildAt(i).setBackgroundResource(
                        R.mipmap.report_unchecked);
            }
        }
        // 3.当滑动到最后一个添加按钮点击进入，
        if (position == imageView.length - 1) {
            guide_tv.setVisibility(View.VISIBLE);
        } else {
            guide_tv.setVisibility(View.GONE);
        }
    }
    @Override
    protected int initlayout() {
        return R.layout.activity_guide;
    }

    private class GuideViewAdapter extends PagerAdapter {
        private List<View> viewList;
        public GuideViewAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }
}
