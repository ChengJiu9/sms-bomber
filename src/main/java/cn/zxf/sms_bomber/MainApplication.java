package cn.zxf.sms_bomber;

import cn.zxf.sms_bomber.read_file.MobileVo;
import cn.zxf.sms_bomber.read_file.ReadFile;
import cn.zxf.sms_bomber.sms_service.SendResult;
import cn.zxf.sms_bomber.sms_service.SmsService;
import cn.zxf.sms_bomber.sms_service.SmsServiceEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class MainApplication {

    public static void main(String[] args) {
        Variable.logLevel = HttpLoggingInterceptor.Level.BASIC;
        List<MobileVo> mobiles = ReadFile.mobileVos();
        LocalDate sign = LocalDate.now().minusDays(180);
        mobiles.stream()
                .filter(vo -> vo.getDate().isAfter(sign))
                .forEach(vo -> {
                    for (int i = 0; i < vo.getTimes(); i++)
                        send(vo.getMobile().replace("-", ""));
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        log.error("出错", e);
                    }
                });
    }

    static void send(String mobile) {
        Stream.of(SmsServiceEnum.values())
                .filter(serviceEnum -> !serviceEnum.deprecated())
                .parallel()
                .forEach(serviceEnum -> {
                    SmsService service = serviceEnum.service;
                    SendResult res = service.send(mobile);
                    log.info(
                            "domain: {}, mobile: {}, 是否成功：【{}】, res: {}",
                            service.domain(), mobile, res.sendOk(), res
                    );
                });
    }

}
