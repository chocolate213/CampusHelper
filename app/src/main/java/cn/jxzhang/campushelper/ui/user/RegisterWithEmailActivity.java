package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.constant.IdentityType;
import cn.jxzhang.campushelper.constant.RegexString;
import cn.jxzhang.campushelper.constant.RequestAddress;
import cn.jxzhang.campushelper.model.ResponseMessage;
import cn.jxzhang.campushelper.model.User;
import cn.jxzhang.campushelper.util.JsonUtils;
import okhttp3.Call;
import okhttp3.Response;

public class RegisterWithEmailActivity extends BaseAppCompatActivity {

    @BindView(R.id.register_username_edit)
    EditText mRegisterUsername;

    @BindView(R.id.register_email_edit)
    EditText mRegisterEmail;

    @BindView(R.id.register_password_edit)
    EditText mRegisterPassword;

    @BindView(R.id.register_confirm_password_edit)
    EditText mRegisterConfirmPassword;

    @BindView(R.id.regist_with_mail_progress)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkGo.init(this.getApplication());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_register_with_email;
    }

    private void attemptSubmit() {
        mRegisterUsername.setError(null);
        mRegisterEmail.setError(null);
        mRegisterPassword.setError(null);
        mRegisterConfirmPassword.setError(null);

        String username = mRegisterUsername.getText().toString();
        String email = mRegisterEmail.getText().toString();
        String password = mRegisterPassword.getText().toString();
        String confirmPassword = mRegisterConfirmPassword.getText().toString();

        boolean cancel = false;

        if (TextUtils.isEmpty(username)) {
            cancel = true;
        } else if (!isUsernameValid(username)) {
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            cancel = true;
        } else if (!isEmailValid(email)) {
            cancel = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            cancel = true;
        }

        if (!cancel) {
            showProgress(true);
            verifyIsEmailExist();
        }
    }

    public void showProgress(final boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void verifyIsEmailExist() {
        User user = new User();
        user.setIdentifier(mRegisterEmail.getText().toString());
        user.setIdentityType(IdentityType.EMAIL.value());
        OkGo.post(RequestAddress.IS_ACCOUNT_EXIST.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        showProgress(false);
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if(result.isSuccess()) {
                            if ((boolean) result.getResult()) {
                                showErrorDialog("邮箱已经注册",RegisterWithEmailActivity.this);
                            } else {
                                Intent intent = new Intent(RegisterWithEmailActivity.this, ConfirmEmailActivity.class);
                                intent.putExtra("email", mRegisterEmail.getText().toString());
                                intent.putExtra("username", mRegisterUsername.getText().toString());
                                intent.putExtra("password", mRegisterPassword.getText().toString());
                                startActivity(intent);
                            }
                        } else {
                            showErrorDialog(result.getMessage(),RegisterWithEmailActivity.this);
                        }
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showProgress(false);
                        showErrorDialog(e.getMessage(),RegisterWithEmailActivity.this);
                    }


                });
    }

    private boolean isUsernameValid(String username) {
        return username.length() > 4 && username.length() < 16;
    }

    private boolean isEmailValid(String email) {
        return email.matches(RegexString.REGEX_EMAIL);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4 && password.length() < 16;
    }


    @OnClick(R.id.register_with_email_submit)
    public void signUp() {
        attemptSubmit();
    }

    @OnClick(R.id.register_with_phone)
    public void tooPhoneRegister() {
        Intent intent = new Intent(RegisterWithEmailActivity.this, RegisterWithPhoneActivity.class);
        startActivity(intent);
        finish();
    }
}
