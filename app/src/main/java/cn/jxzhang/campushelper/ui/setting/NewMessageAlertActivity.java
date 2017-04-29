package cn.jxzhang.campushelper.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class NewMessageAlertActivity extends BaseAppCompatActivity {

    private SharedPreferences preference;

    @BindView(R.id.switch_recv_new_msg)
    Switch mRecvNewMsg;

    @BindView(R.id.switch_show_message_detail)
    Switch mShowMessageDetail;

    @BindView(R.id.switch_sound)
    Switch mSound;

    @BindView(R.id.switch_vibrator)
    Switch mVibrator;

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
        mRecvNewMsg.setChecked(preference.getBoolean("switch_recv_new_msg", false));
        mShowMessageDetail.setChecked(preference.getBoolean("switch_show_message_detail", false));
        mSound.setChecked(preference.getBoolean("switch_sound", false));
        mVibrator.setChecked(preference.getBoolean("switch_vibrator", false));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_new_message_alert;
    }

    @OnCheckedChanged(R.id.switch_recv_new_msg)
    public void switchRecvNewMsg(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_recv_new_msg", isChecked).apply();
    }

    @OnCheckedChanged(R.id.switch_show_message_detail)
    public void switchShowMessageDetail(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_show_message_detail", isChecked).apply();
    }

    @OnCheckedChanged(R.id.switch_sound)
    public void switchSound(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_sound", isChecked).apply();
    }

    @OnCheckedChanged(R.id.switch_vibrator)
    public void switchVibrator(boolean isChecked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("switch_vibrator", isChecked).apply();
    }
}
