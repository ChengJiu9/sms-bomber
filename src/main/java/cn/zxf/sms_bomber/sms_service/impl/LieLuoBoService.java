package cn.zxf.sms_bomber.sms_service.impl;

import cn.zxf.sms_bomber.sms_service.SendResult;
import cn.zxf.sms_bomber.sms_service.SmsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.IOException;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class LieLuoBoService implements SmsService {

    @Override
    public String domain() {
        return "猎萝卜";
    }

    @Override
    public SendResult send(String mobile) {
        Retrofit retrofit = new Retrofit.Builder() //
                .baseUrl("https://www.lieluobo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        SendResult result = new SendResult();

        LieLuoBoRequest request = new LieLuoBoRequest(mobile);
        Call<LieLuoBoResult> call = service.send(request, System.currentTimeMillis());
        try {
            Response<LieLuoBoResult> res = call.execute();
            result.setSign(SendResult.Sign.SUCCESS);
            result.setHttpStatus(res.code());
            result.setRes(res.body());
        } catch (IOException e) {
            log.error("猎萝卜发送短信失败，手机号[{}]", mobile, e);
            result.setSign(SendResult.Sign.ERROR);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    interface ApiService {
        @POST("/api/biz/sms")
        Call<LieLuoBoResult> send(@Body LieLuoBoRequest request, @Query("_") long ts);
    }

    @Data
    static class LieLuoBoRequest {
        String imgVerifyCode = "";
        String mobile;
        String uid = "";

        LieLuoBoRequest(String mobile) {
            this.mobile = mobile;
        }
    }

    @Data
    static class LieLuoBoResult implements SendResult.ApiResult {
        Boolean body;
        Integer code;
        String msg;
    }

}
