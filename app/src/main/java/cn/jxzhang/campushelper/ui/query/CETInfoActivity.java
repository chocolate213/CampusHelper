package cn.jxzhang.campushelper.ui.query;

import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.util.ToastUtils;

public class CETInfoActivity extends BaseAppCompatActivity {

    @BindView(R.id.cet_marks_info_webview)
    WebView cetMarksInfoWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("CET分数解释");
        cetMarksInfoWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                ToastUtils.toast(CETInfoActivity.this, "加载失败");
            }

            /**
             * 在当前窗口打开页面链接
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        cetMarksInfoWebView.loadUrl("file:///android_asset/cet_info.html" );
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cetinfo;
    }
}
