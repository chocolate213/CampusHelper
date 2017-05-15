package cn.jxzhang.campushelper;

import org.junit.Test;

import cn.jxzhang.campushelper.util.DigestUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMd5() throws Exception {
        String s = DigestUtils.md5DigestAsHex("123456");
        System.out.println(s);
    }
}