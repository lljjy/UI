package com.example.ui;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    /**
     * 用来记录item的状态，1表示选中，0表示未选中
     */
    private int[] mItemState;
    //private boolean isCachedBackground = false;
    private Drawable mBackground;
    private boolean mActionModeStarted = false;

    public MyAdapter(Context context, List<ItemBean> list) {
        mList = list;
        mInflater=LayoutInflater.from(context);
        mBackground=ContextCompat.getDrawable(context,R.color.yello);
        mItemState = new int[mList.size()];
        for (int i = 0; i < mItemState.length; i++) {
            mItemState[i] = 0;
        }

    }

    public int[] getItemState() {
        return mItemState;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {     //ListView加载数据的方法
        ViewHolder holder;
        if (convertView == null) {            //判断是否是第一次加载
            holder = new ViewHolder();
            convertView=mInflater.inflate( R.layout.simple_item2,null);            //加载item布局
            holder.mtext = (TextView)convertView.findViewById(R.id.textView);
            holder.mimage=(ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);             //存储标签
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //更新视图数数据
        ItemBean bean=mList.get(position);
        holder.mimage.setImageResource(bean.ItemImageId);
        holder.mtext.setText(bean.ItemTitle);

        updateBackground(position, convertView);
        //holder.mtext.setText(position+"");
        return convertView;
    }

    public int getCheckedItemCount() {
        int count = 0;
        for (int i : mItemState) {
            if (i == 1) count++;
        }
        return count;
    }

    private void updateBackground(int position,View convertView) {
        if (getItemState()[position] == 1) {
            convertView.setBackgroundColor(Color.parseColor("#2F4F4F"));
        } else if (getItemState()[position] == 0) {
            convertView.setBackgroundColor(Color.WHITE);
        }
    }

    public void checkAll() {
        for (int i = 0; i < mItemState.length; i++) {
            mItemState[i] = 1;
        }
    }

    public void unCheckAll() {
        for (int i = 0; i < mItemState.length; i++) {
            mItemState[i] = 0;
        }
    }

    public boolean isAllChecked() {
        for (int i : mItemState) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    public void setActionModeState(boolean flag) {
        mActionModeStarted = flag;
    }

    public boolean isActionModeStarted() {
        return mActionModeStarted;
    }

    public void deleteSelectedItems() {
        for (int i = mItemState.length - 1; i >= 0; i--) {
            if (mItemState[i] == 1) {
                mList.remove(i);
            }
        }
        notifyDataSetChanged();
        mItemState = new int[mList.size()];
        for (int i : mItemState) {
            i = 0;
        }
    }

    public boolean isActionModeStart() {
        return mActionModeStarted;
    }

    static class ViewHolder{

        TextView mtext;
        ImageView mimage;
    }
}
