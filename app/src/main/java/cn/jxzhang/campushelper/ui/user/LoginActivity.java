package cn.jxzhang.campushelper.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseActivity;
import cn.jxzhang.campushelper.constant.IdentityType;
import cn.jxzhang.campushelper.constant.Urls;
import cn.jxzhang.campushelper.model.ResponseMessage;
import cn.jxzhang.campushelper.model.User;
import cn.jxzhang.campushelper.model.WeiboVO;
import cn.jxzhang.campushelper.open.Constants;
import cn.jxzhang.campushelper.ui.MainActivity;
import cn.jxzhang.campushelper.util.DigestUtils;
import cn.jxzhang.campushelper.util.JsonUtils;
import cn.jxzhang.campushelper.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created on 2017-03-15 20:31
 * <p>Title:       LoginActivity</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getName();

    private AuthInfo weiboAuthInfo;

    private SsoHandler weiboSsoHandler;

    private Oauth2AccessToken weiboAccessToken;

    @BindView(R.id.account)
    EditText mAccount;

    @BindView(R.id.password)
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWeiboLogin();
    }

    private void initWeiboLogin() {
        weiboAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        weiboSsoHandler = new SsoHandler(LoginActivity.this, weiboAuthInfo);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.forget_password_text)
    public void toForgetPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.new_for_register_text)
    public void toEmailRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterWithEmailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_button)
    public void attemptLogin() {
        verifyFieldAndLogin();
    }

    @OnClick(R.id.weibo_login_icon)
    public void weiboLogin() {
        weiboSsoHandler. authorizeClientSso(new AuthListener());
    }

    @OnClick(R.id.weixin_login_icon)
    public void wechatLogin() {
        ToastUtils.toast(this, "Wechat login...");
    }

    @OnClick(R.id.qq_login_icon)
    public void qqLogin() {
        ToastUtils.toast(this, "QQ login...");
    }

    /**
     * 判断用户输入字段是否合法
     */
    private void verifyFieldAndLogin() {
        String account = mAccount.getText().toString();

        String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(account) || account.length() < 6 || account.length() > 32) {
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6 || password.length() > 32) {
            return;
        }

        password = DigestUtils.md5DigestAsHex(password);

        login(account, password);

    }

    /**
     * 登陆
     *
     * @param account    用户名
     * @param credential 用户凭证
     */
    private void login(String account, String credential) {
        User user = new User();
        user.setIdentifier(account);
        user.setCredential(DigestUtils.md5DigestAsHex(credential));
        OkGo.post(Urls.SIGN_IN.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage<User> result = JsonUtils.fromJson(s, new TypeToken<ResponseMessage<User>>() {}.getType());
                        if (result.isSuccess()) {
                            if (result.getResult() != null) {
                                User user = result.getResult();
                                saveUser(user);
                                ToastUtils.toast(LoginActivity.this, "登陆成功");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            showErrorDialog(result.getMessage(), LoginActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), LoginActivity.this);
                    }
                });
    }

    /**
     * 登陆成功后保存用户信息
     *
     * @param user
     */
    private void saveUser(User user) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (weiboSsoHandler != null) {
            weiboSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 微博授权事件监听
     */
    private class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            weiboAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (weiboAccessToken.isSessionValid()) {
                AccessTokenKeeper.writeAccessToken(LoginActivity.this, weiboAccessToken);
                attemptToVerifyIsAccountExist();
            } else {
                ToastUtils.toast(LoginActivity.this, "error: " + values.getString("code"));
            }
        }

        @Override
        public void onCancel() {
            ToastUtils.toast(LoginActivity.this, "取消微博授权");
        }

        @Override
        public void onWeiboException(WeiboException e) {
            ToastUtils.toast(LoginActivity.this, e.getMessage());
        }
    }

    /**
     * 尝试判断用户是否存在
     */
    private void attemptToVerifyIsAccountExist() {
        weiboAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (weiboAccessToken.isSessionValid()) {
            String uid = weiboAccessToken.getUid();
            String token = weiboAccessToken.getToken();
            verifyIsAccountExist(uid, token, IdentityType.WEIBO);
        }
    }

    /**
     * 判断账户是是否存在
     * @param identifier           用户唯一识别码
     * @param credential           用户凭证（用于用户存在后直接登陆）
     * @param identityType         用户登陆账户类别
     */
    private void verifyIsAccountExist(final String identifier, final String credential, final IdentityType identityType) {
        User user = new User();
        user.setIdentifier(identifier);
        user.setIdentityType(identityType.value());
        OkGo.post(Urls.IS_ACCOUNT_EXIST.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            if ((boolean) result.getResult()) {
                                Log.d(TAG, "account exist, attempt to login");
                                LoginActivity.this.login(identifier, credential);
                            } else {
                                Log.d(TAG, "account not exist, attempt to regist");
                                attemptToGetWeiboUserInfo();
                            }
                        } else {
                            showErrorDialog(result.getMessage(), LoginActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), LoginActivity.this);
                    }
                });
    }


    /**
     * 尝试获取微博授权用户信息
     */
    private void attemptToGetWeiboUserInfo() {
        weiboAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (weiboAccessToken.isSessionValid()) {
            String token = weiboAccessToken.getToken();
            String uid = weiboAccessToken.getUid();
            getWeiboUserInfo(token, uid);
        }
    }

    /**
     * 获取授权后的微博用户信息
     *
     * @param token TOKEN
     * @param uid   用户ID
     */
    private void getWeiboUserInfo(final String token, String uid) {
        OkGo.get("https://api.weibo.com/2/users/show.json?access_token=" + token + "&uid=" + uid)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        WeiboVO userInfo = JsonUtils.fromJson(s, WeiboVO.class);
                        if (userInfo != null) {
                            registWeiboAccount(userInfo, token);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), LoginActivity.this);
                    }
                });
    }

    /**
     * 注册微博账户登陆权限
     *
     * @param userInfo
     */
    private void registWeiboAccount(final WeiboVO userInfo, final String token) {
        User user = new User();
        user.setIdentityType(IdentityType.WEIBO.value());
        user.setIdentifier(String.valueOf(userInfo.getId()));
        user.setAccountName(userInfo.getName());
        user.setCredential(DigestUtils.md5DigestAsHex(token));
        user.setGender("n".equals(userInfo.getGender()) ? "0" : "f".equals(userInfo.getGender()) ? "1" : "2");
        user.setHead(userInfo.getAvatarHd());
        user.setNickname(userInfo.getName());

        OkGo.post(Urls.SIGN_UP.value())
                .tag(this)
                .upJson(JsonUtils.toJson(user))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ResponseMessage result = JsonUtils.fromJson(s, ResponseMessage.class);
                        if (result.isSuccess()) {
                            Log.d(TAG, "weibo account regist success, attempt to login");
                            LoginActivity.this.login(String.valueOf(userInfo.getId()), token);
                        } else {
                            showErrorDialog(result.getMessage(), LoginActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorDialog(e.getMessage(), LoginActivity.this);
                    }
                });
    }
}
