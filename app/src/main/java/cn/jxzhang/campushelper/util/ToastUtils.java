package cn.jxzhang.campushelper.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created on 2017-02-06 16:45
 * <p>Title:       ToastUtils</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>J.X.Zhang</a>
 * @version 1.0
 */

public class ToastUtils {

    /**
     * Show the given message in a {@link Toast}
     * <p>
     * This method may be called from any thread
     *
     * @param context  Activity
     * @param message   message to show.
     */
    public static void toast(final Context context, final String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Show the message with the given resource id in a {@link Toast}
     * <p>
     * This method may be called from any thread
     *
     * @param context
     * @param resId
     */
    public static void toast(final Context context, final int resId) {
        if (context == null) {
            return;
        }
        toast(context, context.getString(resId));
    }
}
