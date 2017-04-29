package cn.jxzhang.campushelper.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class SettingActivity extends BaseAppCompatActivity {

    private SharedPreferences preference;

    @BindView(R.id.switch_recv_campus_msg)
    Switch mSwitchRecvCampusMsg;

    @BindView(R.id.switch_auto_refresh_grade)
    Switch mSwitchAutoRefreshGrade;

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
        mSwitchRecvCampusMsg.setChecked(preference.getBoolean("switch_recv_campus_msg", false));
        mSwitchAutoRefreshGrade.setChecked(preference.getBoolean("switch_auto_refresh_grade", false));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.action_new_msg_alert)
    public void actionNewMessageAlert(){
        startTargetActivity(NewMessageAlertActivity.class);
    }

    @OnClick(R.id.action_quiet_mode)
    public void actionQuiteMode(){
        startTargetActivity(QuiteModeActivity.class);
    }

    @OnClick(R.id.action_account_and_safety)
    public void actionAccountAndSafety(){
        startTargetActivity(AccountAndSafetyActivity.class);
    }

    @OnCheckedChanged(R.id.switch_recv_campus_msg)
    public void switchRecvCampusMsg(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_recv_campus_msg", isChecked).apply();
    }

    @OnCheckedChanged(R.id.switch_auto_refresh_grade)
    public void switchAutoRefreshGrade(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_auto_refresh_grade", isChecked).apply();
    }
}
