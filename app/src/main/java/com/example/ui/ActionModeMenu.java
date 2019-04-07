package com.example.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActionModeMenu extends AppCompatActivity {

    private ListView list;

    private ItemBean itemBean;

    private MyAdapter mAdapter;

    private String[] names = new String[]
            { "One", "Two", "Three", "Four","Five"};
    private int[] imageIds = new int[]
            { R.drawable.timg,R.drawable.timg,R.drawable.timg,R.drawable.timg,R.drawable.timg,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        //ActionMode action = this.startActionMode(new MyActionModeCallback()); 开启actionmode

        List<ItemBean> itemBeanlist=new ArrayList<>();
        for(int i=0;i<5;i++){
            itemBeanlist.add(new ItemBean(imageIds[i],names[i]));
        }
        list=(ListView)findViewById(R.id.mylist);
        mAdapter=new MyAdapter(this,itemBeanlist);
        list.setAdapter(mAdapter);
        //ActionMode action = this.startActionMode(new MyActionModeCallback());


        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            private boolean allCheckedMode;             //判断是否全选
            private View mMultiSelectionbarView;
            private TextView mSelectedItemCount;

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                allCheckedMode=false;
                mode.getMenuInflater().inflate(R.menu.menu2, menu);        //新建菜单
                if (mMultiSelectionbarView == null) {
                    mMultiSelectionbarView = LayoutInflater.from(ActionModeMenu.this).inflate(R.layout.custom_view, null, false);
                    mSelectedItemCount = (TextView) mMultiSelectionbarView.findViewById(R.id.selected_conv_count);
                }
                mode.setCustomView(mMultiSelectionbarView);    //设置新的ActionBar样式
                ((TextView) mMultiSelectionbarView.findViewById(R.id.title)).setText("选择项目");
                mSelectedItemCount.setText(mAdapter.getCheckedItemCount() + "");
                return true;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                int checkedCount = 0;
                if (allCheckedMode) {
                    if (checked) {
                        mAdapter.getItemState()[position] = 0;
                    } else {
                        mAdapter.getItemState()[position] = 1;
                    }
                    checkedCount = mAdapter.getCheckedItemCount();
                } else {
                    if (checked) {
                        mAdapter.getItemState()[position] = 1;
                    } else {
                        mAdapter.getItemState()[position] = 0;
                    }
                    checkedCount = mAdapter.getCheckedItemCount();
                }
                mSelectedItemCount.setText(checkedCount + "");
                mAdapter.notifyDataSetChanged();   //通知更新listView
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Log.d("menuClick", "onActionItemClicked");
                switch (item.getItemId()) {
                    case R.id.action_mode1:
                        mAdapter.deleteSelectedItems();
                        mode.finish();
                        break;
                    case R.id.action_mode2:
                        if (mAdapter.isAllChecked()) {          //若已全选，则取消全选
                            mAdapter.unCheckAll();
                            list.clearChoices();         //取消全部选定状态
                            allCheckedMode=false;
                            //mode.finish();
                        } else {
                            mAdapter.checkAll();
                            for (int i = 0; i < mAdapter.getCount(); i++) {
                                list.setSelection(i);
                            }
                            allCheckedMode = true;
                        }
                        mAdapter.notifyDataSetChanged();
                        mSelectedItemCount.setText(mAdapter.getCheckedItemCount() + "");
                        break;
                    default:
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Log.d("destroy","onDestroyActionMode");
                allCheckedMode = false;
                mAdapter.unCheckAll();
            }
        });
    }


    }

