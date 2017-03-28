package cn.jxzhang.campushelper.ui.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

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
import cn.jxzhang.campushelper.util.DigestUtils;
import cn.jxzhang.campushelper.util.JsonUtils;
import cn.jxzhang.campushelper.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

public class RegistWithEmailConfirmPasswordActivity extends BaseAppCompatActivity {

    @BindView(R.id.regist_phone_password)
    EditText mPasswordEditText;

    @BindView(R.id.regist_phone_confirm_password)
    EditText mConfirmPasswordEditText;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_regist_with_phone_input_password;
    }

    @OnClick(R.id.register_with_phone_submit)
    public void signUpWithPhone() {
        verifyField();
    }

    private void verifyField() {
        boolean cancel = false;

        String password = mPasswordEditText.getText().toString();
        String confirmPassword = mConfirmPasswordEditText.getText().toString();

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            cancel = true;
        } else if (!password.equals(confirmPassword)) {
            cancel = true;
        }

        if (!cancel) {
            attemptSignUpWithPhone();
        }
    }

    private void attemptSignUpWithPhone() {

        String password = mPasswordEditText.getText().toString();

        User user = new User();
        user.setIdentifier(phone);
        user.setCredential(DigestUtils.md5DigestAsHex(password));
        user.setIdentityType(IdentityType.PHONE.value());

        Log.d("sign up : ", user.toString());

        OkGo.post(Urls.SIGN_UP.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            ToastUtils.toast(RegistWithEmailConfirmPasswordActivity.this, "注册成功");
                        } else {
                            showErrorDialog(result.getMessage(), RegistWithEmailConfirmPasswordActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), RegistWithEmailConfirmPasswordActivity.this);
                    }
                });
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4 && password.length() < 16;
    }
}
