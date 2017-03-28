package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.constant.IdentityType;
import cn.jxzhang.campushelper.constant.Urls;
import cn.jxzhang.campushelper.model.ResponseMessage;
import cn.jxzhang.campushelper.model.User;
import cn.jxzhang.campushelper.util.JsonUtils;
import cn.jxzhang.campushelper.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

public class ForgetPasswordVerifyAccountActivity extends BaseAppCompatActivity {

    private static final long COUNT_DOWN_TIME = 60 * 1000;

    private static final long COUNT_DOWN_STEP = 1 * 1000;

    private static final int VERIFY_CODE_LENGTH = 6;

    private String account;

    private String type;

    @BindView(R.id.register_with_account_and_account_type_text)
    TextView mTextView;

    @BindView(R.id.forget_password_verify_code)
    EditText mVerifyCode;

    @BindView(R.id.get_verify_code2)
    TextView mGetVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getIntent().getStringExtra("account");
        type = getIntent().getStringExtra("type");
        showAccountTypeText();
        sendVerifyCode();
    }

    /**
     * 显示发送的账户类型及账户名
     */
    private void showAccountTypeText() {
        if (IdentityType.PHONE.value().equals(type)) {
            mTextView.setText(String.format(getResources().getString(R.string.send_sms_to_account_text), account));
        } else if (IdentityType.EMAIL.value().equals(type)) {
            mTextView.setText(String.format(getResources().getString(R.string.send_email_to_account_text), account));
        }
    }

    /**
     * 发送验证码
     */
    private void sendVerifyCode() {
        setGetVerifyCodeWidgetClickable(false);
        timer.start();
        OkGo.get(Urls.SEND_VERIFY_CODE.value() + "/" + account)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage<User> result = JsonUtils.fromJson(s, new TypeToken<ResponseMessage<User>>(){}.getType());
                        if (result.isSuccess()) {
                            if (result.getResult() != null) {
                                User user = result.getResult();
                                String code = user.getVerifyCode();
                                String identityType = user.getIdentityType();
                                if (IdentityType.PHONE.value().equals(identityType)) {
                                    ToastUtils.toast(ForgetPasswordVerifyAccountActivity.this, "自动识别短信验证码 ： " + code);
                                }
                            } else {
                                showErrorDialog("验证码请求失败", ForgetPasswordVerifyAccountActivity.this);
                            }
                        } else {
                            showErrorDialog(result.getMessage(), ForgetPasswordVerifyAccountActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), ForgetPasswordVerifyAccountActivity.this);
                    }
                });

    }

    @OnClick(R.id.get_verify_code2)
    public void getVerifyCodeView() {
        sendVerifyCode();
    }

    @OnClick(R.id.forget_password_submit_verify_code)
    public void attemptSubmitVerifyCode() {
        String s = mVerifyCode.getText().toString();
        if (TextUtils.isEmpty(s) || s.length() != VERIFY_CODE_LENGTH) {
            return;
        }
        submitVerifyCode(s);
    }

    private void submitVerifyCode(final String inputVerifyCode){
        OkGo.get(Urls.VERIFY_VERIFY_CODE.value() + "/" + account + "/" + inputVerifyCode)     // 请求方式和请求url
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            if ((boolean) result.getResult()) {
                                Intent intent = new Intent(ForgetPasswordVerifyAccountActivity.this, ForgetPasswordInputPasswordActivity.class);
                                intent.putExtra("account", account);
                                intent.putExtra("type", type);
                                startActivity(intent);
                                finish();
                            } else {
                                showErrorDialog("验证码输入错误，请重新输入", ForgetPasswordVerifyAccountActivity.this);
                            }
                        } else {
                            showErrorDialog(result.getMessage(), ForgetPasswordVerifyAccountActivity.this);
                        }
                    }
                });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password_confirm_password;
    }

    private CountDownTimer timer = new CountDownTimer(COUNT_DOWN_TIME, COUNT_DOWN_STEP) {
        @Override
        public void onTick(long millisUntilFinished) {
            mGetVerifyCode.setText(String.format(getString(R.string.after_second_resend), millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            setGetVerifyCodeWidgetClickable(true);
            mGetVerifyCode.setText("重新发送");
        }
    };

    private void setGetVerifyCodeWidgetClickable(boolean clickable) {
        if (clickable) {
            mGetVerifyCode.setTextColor(getResources().getColor(R.color.colorPrimary));
            mGetVerifyCode.setBackgroundResource(R.drawable.verify_code_text_active);
            mGetVerifyCode.setClickable(true);
        } else {
            mGetVerifyCode.setTextColor(getResources().getColor(R.color.color_grey_999));
            mGetVerifyCode.setBackgroundResource(R.drawable.verify_code_text);
            mGetVerifyCode.setClickable(false);
        }
    }
}
