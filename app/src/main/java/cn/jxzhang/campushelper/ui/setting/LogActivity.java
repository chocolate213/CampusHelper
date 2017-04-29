package cn.jxzhang.campushelper.ui.setting;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class LogActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("更新日志");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_log;
    }

}
