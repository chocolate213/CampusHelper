/**
 * Copyright 2017 bejson.com
 */
package cn.jxzhang.campushelper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2017-03-28 20:38:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WeiboVO {
    /**
     * 用户UID
     */
    private int id;

    /**
     * 字符串型的用户UID
     */
    private String idstr;

    /**
     * 用户昵称
     */
    @SerializedName("screen_name")
    private String screenName;

    /**
     * 友好显示名称
     */
    private String name;

    /**
     * 用户所在省级ID
     */
    private String province;

    /**
     * 用户所在城市ID
     */
    private String city;

    /**
     * 用户所在地
     */
    private String location;

    /**
     * 用户个人描述
     */
    private String description;

    /**
     * 用户博客地址
     */
    private String url;

    /**
     * 用户头像地址（中图），50×50像素
     */
    @SerializedName("profile_image_url")
    private String profileImageUrl;

    /**
     * 背景卡片
     */
    @SerializedName("cover_image_phone")
    private String coverImagePhone;

    /**
     * 用户的微博统一URL地址
     */
    @SerializedName("profile_url")
    private String profileUrl;

    /**
     * 用户的个性化域名
     */
    private String domain;

    /**
     * 用户的微号
     */
    private String weihao;

    /**
     * 性别，m：男、f：女、n：未知
     */
    private String gender;

    /**
     * 粉丝数
     */
    @SerializedName("followers_count")
    private int followersCount;

    /**
     * 关注数
     */
    @SerializedName("friends_count")
    private int friendsCount;


    /**
     * 微博数
     */
    @SerializedName("statuses_count")
    private int statusesCount;

    /**
     * 	收藏数
     */
    @SerializedName("favourites_count")
    private int favouritesCount;

    /**
     * 用户创建（注册）时间
     */
    @SerializedName("created_at")
    private String createdAt;

    /**
     * 用户头像地址（大图），180×180像素
     */
    @SerializedName("avatar_large")
    private String avatarLarge;

    /**
     * 用户头像地址（高清），高清头像原图
     */
    @SerializedName("avatar_hd")
    private String avatarHd;

    /**
     * 用户的在线状态，0：不在线、1：在线
     */
    @SerializedName("online_status")
    private int onlineStatus;

    /**
     * 用户的互粉数
     */
    @SerializedName("bi_followers_count")
    private int biFollowersCount;

    /**
     * 用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
     */
    private String lang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCoverImagePhone() {
        return coverImagePhone;
    }

    public void setCoverImagePhone(String coverImagePhone) {
        this.coverImagePhone = coverImagePhone;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public String getAvatarHd() {
        return avatarHd;
    }

    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getBiFollowersCount() {
        return biFollowersCount;
    }

    public void setBiFollowersCount(int biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}