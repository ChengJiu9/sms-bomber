package cn.zxf.sms_bomber.sms_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
@Deprecated
public class TestChSiService extends AbstractTestService {

    @Test
    public void test() {
        super.test("13288995566", SmsServiceEnum.ChSi);
    }

}
