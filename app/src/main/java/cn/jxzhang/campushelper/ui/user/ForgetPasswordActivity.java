package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.constant.Urls;
import cn.jxzhang.campushelper.model.ResponseMessage;
import cn.jxzhang.campushelper.model.User;
import cn.jxzhang.campushelper.util.JsonUtils;
import okhttp3.Call;
import okhttp3.Response;

public class ForgetPasswordActivity extends BaseAppCompatActivity {

    @BindView(R.id.forget_pwd_account)
    EditText mForgetPwdAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @OnClick(R.id.forget_pwd_next)
    public void nextStep(){
        String account = mForgetPwdAccount.getText().toString();
        isAccountExist(account);
    }

    private void isAccountExist(final String account) {
        if (TextUtils.isEmpty(account)) {
            return;
        } else if (account.length() < 6) {
            return;
        }
        User user = new User();
        user.setIdentifier(account);
        OkGo.post(Urls.GET_ACCOUNT_TYPE.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage<User> result = JsonUtils.fromJson(s, new TypeToken<ResponseMessage<User>>(){}.getType());
                        if (result.isSuccess()) {
                            if (result.getResult() != null) {
                                User u = result.getResult();
                                String identityType = u.getIdentityType();
                                Intent intent = new Intent(ForgetPasswordActivity.this, ForgetPasswordVerifyAccountActivity.class);
                                intent.putExtra("account", account);
                                intent.putExtra("type", identityType);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            showErrorDialog(result.getMessage(), ForgetPasswordActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), ForgetPasswordActivity.this);
                    }
                });

    }
}
