package cn.jxzhang.campushelper.util;

/**
 * Created on 2016-12-19 09:53
 * <p>Title:       PageUtils</p>
 * <p>Description: Page Utils</p>
 *
 * @author <a href=zhangjiaxing@ultrapower.com.cn>J.X.Zhang</a>
 * @version 1.0
 */
public class PageUtils {

    private PageUtils() { /* cannot be instantiated */ }

    /**
     * MySql 分页语句
     *
     * @param targetSql 目标SQL
     * @param startPage 起始页码
     * @param pageSize  分页大小
     * @return 添加分页语句后的SQL
     *
     */
    public static String generateSQLQueryForPageWithMySQL(String targetSql, int startPage, int pageSize){
        return targetSql + " limit " + startPage + "," + pageSize;
    }

    /**
     * Oracle 分页语句
     *
     * @param targetSql 目标SQL
     * @param startPage 起始页码
     * @param pageSize  分页大小
     * @return 添加分页语句后的SQL
     */
    public static String generateSQLQueryForPageWithOracle(String targetSql, int startPage, int pageSize){
        return "SELECT * FROM ( SELECT row_.*, rownum rownum_ FROM (" + targetSql + ") row_ WHERE rownum <= " + (startPage + pageSize) + " ) WHERE rownum_ > " + startPage;
    }
}
