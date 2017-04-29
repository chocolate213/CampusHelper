package cn.jxzhang.campushelper.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class QuiteModeActivity extends BaseAppCompatActivity {

    private SharedPreferences preference;

    @BindView(R.id.switch_quite_mode)
    Switch mSwitchQuiteMode;

    @BindView(R.id.quite_mode_time_area)
    LinearLayout mQuiteModeTimeArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showActionBar();
        initSharedPreferences();
        initSetting();
    }

    private void initSharedPreferences() {
        preference = getPreferences(Activity.MODE_PRIVATE);
    }

    private void initSetting() {
        boolean isChecked =  preference.getBoolean("switch_quite_mode", false);
        mSwitchQuiteMode.setChecked(isChecked);
        if (isChecked) {
            mQuiteModeTimeArea.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_quite_mode;
    }

    @OnCheckedChanged(R.id.switch_quite_mode)
    public void switchQuiteMode(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_quite_mode", isChecked).apply();
        if (isChecked) {
            mQuiteModeTimeArea.setVisibility(View.VISIBLE);
        } else {
            mQuiteModeTimeArea.setVisibility(View.GONE);
        }
    }
}
