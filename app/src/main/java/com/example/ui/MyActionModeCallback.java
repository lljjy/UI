package com.example.ui;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyActionModeCallback implements ActionMode.Callback {

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // ActionMode创建，定义menu菜单。
        MenuInflater menuInflater = mode.getMenuInflater();
        menuInflater.inflate(R.menu.menu2, menu);
        return true;
    }



    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // ActionMode工作前的预先处理
        mode.setTitle("ActionMode");
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        // menu菜单Item点击回调
        /*if (item.getItemId() == R.id.menu_confirm) {
            mode.finish();
        }*/
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // ActionMode模式结束处理（点击左侧取消，或者点击返回键结束ActionMode模式）。
        // ActionMode.finish()执行后会结束ActionMode模式，此时会执行这里。
    }
}