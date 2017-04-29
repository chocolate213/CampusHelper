package cn.jxzhang.campushelper.ui.setting;

import android.os.Bundle;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class FeatureActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("功能介绍");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_feature;
    }
}
