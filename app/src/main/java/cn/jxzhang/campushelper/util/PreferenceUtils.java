package cn.jxzhang.campushelper.util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created on 2017-02-06 16:23
 * <p>Title:       PreferenceUtils</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>J.X.Zhang</a>
 * @version 1.0
 */

public class PreferenceUtils {

    /**
     * 获取SharedPreference
     * @param context 上下文
     * @return SharedPreference对象
     */
    public SharedPreferences.Editor getSharedPreferences(Activity context){
        return context.getPreferences(Activity.MODE_PRIVATE).edit();
    }

    /**
     * 保存Shared Preference
     * @param editor SharedPreferences.Editor对象
     */
    public void save(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
