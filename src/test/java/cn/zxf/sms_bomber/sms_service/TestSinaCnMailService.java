package cn.zxf.sms_bomber.sms_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class TestSinaCnMailService {

    @Test
    public void test() {
        String mobile = "17120248813";
        SmsService service = SmsServiceEnum.SinaCnMail.service;
        SendResult res = service.send(mobile);
        log.info("domain: {}, mobile: {}, 是否成功：{}, res: {}", service.domain(), mobile, res.sendOk(), res);
    }

}
