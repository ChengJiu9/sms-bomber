package cn.zxf.sms_bomber.sms_service.impl;

import cn.zxf.sms_bomber.sms_service.SendResult;
import cn.zxf.sms_bomber.sms_service.SmsService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by zengxf on 2019/8/5.
 */
@Slf4j
public abstract class AbstractSmsService implements SmsService {

    private final String domain;
    private final String baseUrl;

    public AbstractSmsService(String domain, String baseUrl) {
        this.domain = domain;
        this.baseUrl = baseUrl;
    }

    @Override
    public String domain() {
        return this.domain;
    }

    @Override
    public SendResult send(String mobile) {
        log.info("利用[{}]发送短信到[{}]", this.domain, mobile);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor((msg) -> log.info(msg))
                                .setLevel(HttpLoggingInterceptor.Level.BASIC)
                );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        SendResult result = new SendResult();
        try {
            this.send(mobile, retrofit, result);
            result.setSign(SendResult.Sign.SUCCESS);
        } catch (IOException e) {
            log.error("猎萝卜发送短信失败，手机号[{}]", mobile, e);
            result.setSign(SendResult.Sign.ERROR);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    public abstract void send(String mobile, Retrofit retrofit, SendResult result) throws IOException;

}
