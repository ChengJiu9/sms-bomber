package cn.zxf.sms_bomber.sms_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class TestHunterPlusService {

    @Test
    public void test() {
        String mobile = "17120248813";
        SmsService service = SmsServiceEnum.HunterPlus.service;
        SendResult res = service.send(mobile);
        log.info("domain: {}, mobile: {}, res: {}", service.domain(), mobile, res);
    }

}
