package cn.jxzhang.campushelper.model;

/**
 * Created on 2017-04-05 23:26
 * <p>Title:       Article</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */
public class Article {
    private String title;
    private String description;


    public Article() {
    }

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Article(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Article(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
