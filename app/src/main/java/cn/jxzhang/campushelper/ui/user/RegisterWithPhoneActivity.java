package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.constant.IdentityType;
import cn.jxzhang.campushelper.constant.RequestAddress;
import cn.jxzhang.campushelper.model.ResponseMessage;
import cn.jxzhang.campushelper.model.User;
import cn.jxzhang.campushelper.util.JsonUtils;
import okhttp3.Call;
import okhttp3.Response;

public class RegisterWithPhoneActivity extends BaseAppCompatActivity {

    private static final long COUNT_DOWN_TIME = 60 * 1000;

    private static final long COUNT_DOWN_STEP = 1 * 1000;

    private static final int VERIFY_CODE_LENGTH = 4;

    private static final String CURRECT_VERIFY_CODE = "1234";

    @BindView(R.id.regist_phone)
    EditText mPhoneNumber;

    @BindView(R.id.verify_code)
    EditText mVerifyCode;

    @BindView(R.id.get_verify_code)
    TextView mGetVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGetVerifyCode.setClickable(false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_reigster_with_phone;
    }

    @OnClick(R.id.register_with_email)
    public void toEmailRegister() {
        Intent intent = new Intent(RegisterWithPhoneActivity.this, RegisterWithEmailActivity.class);
        startActivity(intent);
        finish();
    }

    @OnTextChanged(R.id.regist_phone)
    public void onPhoneNumberChanged(CharSequence text) {
        Log.d("phone number", text.toString());
        String phone = text.toString();
        if (phone.length() == 11 && phone.startsWith("1")) {
            setGetVerifyCodeWidgetClickable(true);
        } else {
            setGetVerifyCodeWidgetClickable(false);
        }
    }

    @OnClick(R.id.get_verify_code)
    public void getVerifyCode() {
        verifyIsPhoneExist();
    }

    private void verifyIsPhoneExist() {
        User user = new User();
        user.setIdentifier(mPhoneNumber.getText().toString());
        user.setIdentityType(IdentityType.PHONE.value());
        OkGo.post(RequestAddress.IS_ACCOUNT_EXIST.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if(result.isSuccess()) {
                            if ((boolean) result.getResult()) {
                                showErrorDialog("手机号已经注册",RegisterWithPhoneActivity.this);
                            } else {
                                setGetVerifyCodeWidgetClickable(false);
                                sendVerifyCode();
                                timer.start();
                            }
                        } else {
                            showErrorDialog(result.getMessage(),RegisterWithPhoneActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(),RegisterWithPhoneActivity.this);
                    }

                });
    }

    private void sendVerifyCode() {

    }

    @OnClick(R.id.submit_regist_phone)
    public void signUp() {
        String inputVerifyCode = mVerifyCode.getText().toString();
        if (!TextUtils.isEmpty(inputVerifyCode) && inputVerifyCode.length() == VERIFY_CODE_LENGTH) {
            if(verifyVerifyCode(inputVerifyCode)) {
                timer.cancel();
                goInputPassword();
            } else {
                showErrorDialog("验证码错误，请重新输入",RegisterWithPhoneActivity.this);
            }
        }
    }

    private void goInputPassword() {
        String phone = mPhoneNumber.getText().toString();
        Intent intent = new Intent(RegisterWithPhoneActivity.this, InputPasswordActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }

    private boolean verifyVerifyCode(String inputVerifyCode) {
        return CURRECT_VERIFY_CODE.equals(inputVerifyCode);
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

    private void setGetVerifyCodeWidgetClickable(boolean clickable){
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
