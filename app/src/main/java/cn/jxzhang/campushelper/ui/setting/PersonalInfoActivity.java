package cn.jxzhang.campushelper.ui.setting;

import android.os.Bundle;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class PersonalInfoActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showActionBar();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal_info;
    }
}
