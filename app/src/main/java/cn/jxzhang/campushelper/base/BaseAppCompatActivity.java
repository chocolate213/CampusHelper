package cn.jxzhang.campushelper.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import butterknife.ButterKnife;

/**
 * Created on 2017-03-17 20:03
 * <p>Title:       BaseAppCompatActivity</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        OkGo.init(this.getApplication());
        OkGo.getInstance().setCookieStore(new PersistentCookieStore());
    }

    protected abstract int getContentView();

    public void showErrorDialog(String message, Context context) {
        Log.v("log", "log");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // FIRE ZE MISSILES!
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
