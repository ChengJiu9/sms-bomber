package cn.zxf.sms_bomber.sms_service;

import cn.zxf.sms_bomber.sms_service.impl.HunterPlusService;
import cn.zxf.sms_bomber.sms_service.impl.LieLuoBoService;
import cn.zxf.sms_bomber.sms_service.impl.SinaCnMailService;
import lombok.AllArgsConstructor;

/**
 * 发送服务类枚举总成
 * <p>
 * Created by zengxf on 2019/8/5.
 */
@AllArgsConstructor
public enum SmsServiceEnum {

    LieLuoBo(new LieLuoBoService()),
    HunterPlus(new HunterPlusService()),
    SinaCnMail(new SinaCnMailService()),
    ;

    public final SmsService service;

}
