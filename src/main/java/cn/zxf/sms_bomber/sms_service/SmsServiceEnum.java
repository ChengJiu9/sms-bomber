package cn.zxf.sms_bomber.sms_service;

import cn.zxf.sms_bomber.sms_service.impl.HunterPlusService;
import cn.zxf.sms_bomber.sms_service.impl.LieLuoBoService;
import lombok.AllArgsConstructor;

/**
 * Created by zengxf on 2019/8/5.
 */
@AllArgsConstructor
public enum SmsServiceEnum {

    LieLuoBo(new LieLuoBoService()),
    HunterPlus(new HunterPlusService()),
    ;

    public final SmsService service;

}
