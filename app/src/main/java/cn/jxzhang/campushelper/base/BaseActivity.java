package cn.jxzhang.campushelper.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import butterknife.ButterKnife;

/**
 * Created on 2017-03-15 20:37
 * <p>Title:       BaseActivity</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public abstract class  BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
    }

    protected abstract int getContentView();

    public void showErrorDialog(String message, Context context) {
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
