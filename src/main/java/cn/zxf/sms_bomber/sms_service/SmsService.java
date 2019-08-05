package cn.zxf.sms_bomber.sms_service;

/**
 * Created by zengxf on 2019/8/5.
 */
public interface SmsService {

    String domain();

    SendResult send(String mobile);

}
