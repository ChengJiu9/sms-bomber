package cn.zxf.sms_bomber.read_file;

import org.junit.Test;

import java.util.List;

/**
 * Created by zengxf on 2019/8/5.
 */
public class TestReadFile {

    @Test
    public void test() {
        List<String> mobiles = ReadFile.mobiles();
        System.out.println(mobiles);
    }

    @Test
    public void testVos() {
        List<MobileVo> mobiles = ReadFile.mobileVos();
        mobiles.forEach(System.out::println);
    }

}