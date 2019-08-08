package cn.zxf.sms_bomber.sms_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by zengxf on 2019/8/8.
 */
@Slf4j
public class TestSmsServiceEnum {

    @Test
    public void test() {
        Stream.of(SmsServiceEnum.values())
                .forEach(e -> log.info("name: {}, deprecated: {}", e, e.deprecated()));
    }

}
