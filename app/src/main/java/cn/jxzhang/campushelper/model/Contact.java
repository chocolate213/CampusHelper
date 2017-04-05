package cn.jxzhang.campushelper.model;

import cn.jxzhang.campushelper.R;

/**
 * Created on 2017-04-04 22:00
 * <p>Title:       Contact</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class Contact {
    private String name;
    private String letter;
    private int icon = R.mipmap.ic_launcher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
