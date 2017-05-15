package cn.jxzhang.campushelper.open;

/**
 * 该类定义了微博授权时所需要的参数。
 * 
 * @author SINA
 * @since 2013-09-29
 */
public class Constants {

    /**
     * Email地址
     */
    public static final String[] ADDRESS = {"chocolatepie213@gmail.com"};

    /** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
    public static final String APP_KEY      = "972672582";

    /** 
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     * 
     * <p>
     * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响，
     * 但是没有定义将无法使用 SDK 认证登录。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * </p>
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    /**
     * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
     * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
     * 选择赋予应用的功能。
     * 
     * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
     * 使用权限，高级权限需要进行申请。
     * 
     * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
     * 
     * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
     * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
     */
    public static final String SCOPE = 
            "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
    /*
     * ErrorInfo
     */
    public static final int LOGIN_SUCCESS = 0x00000000;           //登陆成功
    public static final int LOGIN_NETWORK_WRONG = 0x00000001;     //网络错误
    public static final int LOGIN_PASSWORD_WRONG = 0x00000002;    //密码错误
    public static final int LOGIN_SYSTEM_WRONG = 0x00000003;      //系统错误
    public static final int LOGIN_NO_SEARCH_BOOKS = 0x00000004;   //没有找到相关书籍
    /*
     * URL
     */
    public static final String LOGIN_URL_OUT= "http://jwgl.hhhxy.cn/_data/home_login.aspx";
    public static final String LOGIN_URL_IN= "http://172.16.100.1/_data/home_login.aspx";
    public static final String STU_GET_SCORE_URL_OUT= "http://jwgl.hhhxy.cn/xscj/Stu_MyScore_rpt.aspx";
    public static final String STU_GET_SCORE_URL_IN= "http://172.16.100.1/xscj/Stu_MyScore_rpt.aspx";
    /*
     * 学年
     */
    public static final String YEAR_2013_2014 = "2013";
    public static final String YEAR_2014_2015 = "2014";
    public static final String YEAR_2015_2016 = "2015";

    /*
     *  主辅修标记：
     */
    public static final String ZFX_FLAG_MAGOR = "0";	//主修
    public static final String ZFX_FLAG_MINOR = "1";	//辅修
    /*
     * 学期：
     */
    public static final String FIRST_SEMESTER = "0";
    public static final String SECOND_SEMESTER = "1";
    /*
     * 原始成绩/有效成绩
     */
    public static final String GRADE_ORIGINAL = "0";
    public static final String GRADE_VALID = "1";

    /*
     * 查询成绩类别：
     */
    public static final String TYPE_ALL = "0";
    public static final String TYPE_YEAR = "1";
    public static final String TYPE_SEMESTER = "2";
}
