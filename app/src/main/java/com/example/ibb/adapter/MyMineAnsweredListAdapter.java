package com.example.ibb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.ui.ui.answered.MineAnsweredBean;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/19.
 */

public class MyMineAnsweredListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<List<MineAnsweredBean>> answeredBeenList;

    public MyMineAnsweredListAdapter(Context context,List<List<MineAnsweredBean>> answeredBeenList){
        this.context = context;
        this.answeredBeenList = answeredBeenList;
    }


    @Override
    public int getGroupCount() {
        return answeredBeenList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return answeredBeenList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return answeredBeenList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return answeredBeenList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        convertView  = View.inflate(context, R.layout.listview_check, null);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

         MyMineAnsweredListAdapter.MineAnsweredHolder mineansweredHolder;

        if (convertView == null) {

            convertView = View.inflate(context, R.layout.mineanswered_list_item, null);

        }
            mineansweredHolder = new MyMineAnsweredListAdapter.MineAnsweredHolder();
            mineansweredHolder.mineanswered_title = convertView.findViewById(R.id.mineanswered_title);
        RatingBar    all_ratingbar = convertView.findViewById(R.id.all_ratingbar);
            final TextView all_score_tv = convertView.findViewById(R.id.all_score_tv);
            final LinearLayout vis_ll = convertView.findViewById(R.id.vis_ll);
            convertView.setTag(mineansweredHolder);
//        }else{
            mineansweredHolder = (MyMineAnsweredListAdapter.MineAnsweredHolder) convertView.getTag();
            mineansweredHolder.mineanswered_title.setText(answeredBeenList.get(groupPosition).get(childPosition).getTitle());
            all_ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    vis_ll.setVisibility(View.VISIBLE);
                    float v = rating * 2;
                    all_score_tv.setText(v + "分");
//
                    Log.e("TAG","------------------->");

                    Log.e("TAG","------------------->");

                    notifyDataSetChanged();
                }
            });
//        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class MineAnsweredHolder{

        TextView mineanswered_title;
        RatingBar all_ratingbar;
        TextView all_score_tv;
        LinearLayout vis_ll;
    }


}
