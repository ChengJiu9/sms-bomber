package cn.zxf.sms_bomber.sms_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class TestLieLuoBoService {

    @Test
    public void test() {
        String mobile = "17120248814";
        SmsService service = SmsServiceEnum.LieLuoBo.service;
        SendResult res = service.send(mobile);
        log.info("domain: {}, mobile: {}, res: {}", service.domain(), mobile, res);
    }

}
