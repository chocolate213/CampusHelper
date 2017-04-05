package cn.jxzhang.campushelper.ui.setting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.open.Constants;

public class HelpAndFeedbackActivity extends AppCompatActivity {

    public static final String USER_FEEDBACK_SUBJECT = "用户反馈";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_feedback);

        sendEmail();
    }

    /**
     * 发送邮件反馈
     */
    private void sendEmail() {
        composeEmail(Constants.ADDRESS,USER_FEEDBACK_SUBJECT,"请简要描述您遇到的问题和意见,帮助我们做的更好。建议您留下联系方式，方便我们与您联系，感谢您的支持!");
    }

    /**
     * 反馈
     * @param addresses 收件人地址
     * @param subject   主题
     * @param text      内容
     */
    public void composeEmail(String[] addresses, String subject, String text){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:chocolatepie213@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);                   // 正文
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
        }
    }
}
