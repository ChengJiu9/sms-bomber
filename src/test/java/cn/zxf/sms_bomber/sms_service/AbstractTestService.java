package cn.zxf.sms_bomber.sms_service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zengxf on 2019/8/6.
 */
@Slf4j
public class AbstractTestService {

    public void test(String mobile, SmsServiceEnum serviceEnum) {
        SmsService service = serviceEnum.service;
        SendResult res = service.send(mobile);
        log.info(
                "domain: {}, mobile: {}, 是否成功：{}, res: {}",
                service.domain(), mobile, res.sendOk(), res
        );
    }

}
