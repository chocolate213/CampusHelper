package cn.jxzhang.campushelper.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.ui.user.LoginActivity;

/**
 * Created on 2017-03-13 18:40
 * <p>Title:       WelcomeActivity</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class WelcomeActivity extends Activity {

    /** MainActivity 标识 */
    private static final int GOTO_MAIN_ACTIVITY = 0x00000001;

    /** 欢迎页面延时 */
    private static final int DELAY_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_welcome);
        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, DELAY_TIME);
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
            return true;
        }
    });
}
