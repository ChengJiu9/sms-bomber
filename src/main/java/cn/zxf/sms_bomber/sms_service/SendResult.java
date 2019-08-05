package cn.zxf.sms_bomber.sms_service;

import lombok.Data;

/**
 * Created by zengxf on 2019/8/5.
 */
@Data
public class SendResult {

    Sign sign;
    String errorMsg;
    int httpStatus;
    ApiResult res;

    public enum Sign {ERROR, SUCCESS}

    public interface ApiResult {
    }

}
