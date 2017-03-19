package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseActivity;

/**
 * Created on 2017-03-15 20:31
 * <p>Title:       LoginActivity</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.forget_password_text)
    public void toForgetPassword(){
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.new_for_register_text)
    public void toEmailRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterWithEmailActivity.class);
        startActivity(intent);
    }
}
