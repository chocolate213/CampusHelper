package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.constant.Urls;
import cn.jxzhang.campushelper.model.ResponseMessage;
import cn.jxzhang.campushelper.model.User;
import cn.jxzhang.campushelper.util.DigestUtils;
import cn.jxzhang.campushelper.util.JsonUtils;
import okhttp3.Call;
import okhttp3.Response;

public class ForgetPasswordInputPasswordActivity extends BaseAppCompatActivity {

    private String account;

    private String identifyType;

    @BindView(R.id.forget_password_new_password)
    EditText mPassword;

    @BindView(R.id.forget_password_confirm_new_password)
    EditText mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        account = intent.getStringExtra("account");
        identifyType = intent.getStringExtra("type");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password_input_password;
    }

    @OnClick(R.id.submit_new_password)
    public void submitNewPassword(){
        verifyField();
    }

    private void verifyField() {
        boolean cancel = false;

        String password = mPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            cancel = true;
        }

        if (!cancel) {
            resetPassword();
        }
    }

    private void resetPassword() {

        String password = mConfirmPassword.getText().toString();

        User user = new User();
        user.setIdentifier(account);
        user.setIdentityType(identifyType);
        user.setCredential(DigestUtils.md5DigestAsHex(password));

        OkGo.post(Urls.RESET_PASSWORD.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            Intent intent = new Intent(ForgetPasswordInputPasswordActivity.this, ForgetPasswordResetPasswordSuccessActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            showErrorDialog(result.getMessage(), ForgetPasswordInputPasswordActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), ForgetPasswordInputPasswordActivity.this);
                    }
                });
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4 && password.length() < 16;
    }
}
