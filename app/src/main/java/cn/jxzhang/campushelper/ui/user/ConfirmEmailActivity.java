package cn.jxzhang.campushelper.ui.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class ConfirmEmailActivity extends BaseAppCompatActivity {

    private String email;
    private String username;
    private String password;

    @BindView(R.id.confirm_email)
    TextView mConfirmEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getStringExtra("email");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        mConfirmEmail.setText(email);
    }

    @OnClick(R.id.submit_confirm_email)
    public void verifyUserName() {
        User user = new User();
        user.setIdentifier(username);
        user.setIdentityType(IdentityType.USERNAME.value());
        OkGo.post(Urls.IS_ACCOUNT_EXIST.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            if ((Boolean) result.getResult()) {
                                showErrorDialog("用户名已存在", ConfirmEmailActivity.this);
                            } else {
                                attemptSignUp();
                            }
                        } else {
                            showErrorDialog(result.getMessage(), ConfirmEmailActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), ConfirmEmailActivity.this);
                    }
                });
    }

    private void attemptSignUp() {
        User user = new User();
        user.setIdentifier(email);
        user.setAccountName(username);
        user.setCredential(DigestUtils.md5DigestAsHex(password));
        user.setIdentityType(IdentityType.EMAIL.value());

        Log.d("sign up : ", user.toString());

        OkGo.post(Urls.SIGN_UP.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            ToastUtils.toast(ConfirmEmailActivity.this, "注册成功");
                        } else {
                            showErrorDialog(result.getMessage(), ConfirmEmailActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), ConfirmEmailActivity.this);
                    }
                });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_confirm_email;
    }
}
