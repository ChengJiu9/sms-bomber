package cn.zxf.sms_bomber.sms_service;

import cn.zxf.sms_bomber.sms_service.impl.*;
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
    PinDuoDuo(new PinDuoDuoService()),
    @Deprecated JD(new JDService(), true),
    @Deprecated QQ(new QQService(), true),
    @Deprecated ChSi(new ChSiService(), true),
    @Deprecated Fang(new FangService(), true),
    ;

    public final SmsService service;
    public final boolean deprecated;

    SmsServiceEnum(SmsService service) {
        this(service, false);
    }

}
