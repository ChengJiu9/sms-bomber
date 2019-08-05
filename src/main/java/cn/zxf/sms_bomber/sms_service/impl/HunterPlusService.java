package cn.zxf.sms_bomber.sms_service.impl;

import cn.zxf.sms_bomber.sms_service.SendResult;
import cn.zxf.sms_bomber.sms_service.SmsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public class HunterPlusService implements SmsService {

    @Override
    public String domain() {
        return "猎萌";
    }

    @Override
    public SendResult send(String mobile) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BASIC)
                );
        Retrofit retrofit = new Retrofit.Builder() //
                .baseUrl("https://www.hunterplus.net")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        SendResult result = new SendResult();

        Request request = new Request(mobile);
        Call<String> call = service.send(request);
        try {
            Response<String> res = call.execute();
            result.setSign(SendResult.Sign.SUCCESS);
            result.setHttpStatus(res.code());
            result.setRes(new Result(res.body()));
        } catch (IOException e) {
            log.error("猎萝卜发送短信失败，手机号[{}]", mobile, e);
            result.setSign(SendResult.Sign.ERROR);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    interface ApiService {
        @POST("/api/code/send")
        Call<String> send(@Body Request request);
    }

    @Data
    static class Request {
        String mobile;

        Request(String mobile) {
            this.mobile = mobile;
        }
    }

    @Data
    static class Result implements SendResult.ApiResult {
        String msg;

        Result(String msg) {
            this.msg = msg;
        }
    }

}
