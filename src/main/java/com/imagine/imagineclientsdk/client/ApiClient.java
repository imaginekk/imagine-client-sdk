package com.imagine.imagineclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

import static com.imagine.imagineclientsdk.utils.SignUtils.getSign;


/**
 * @author imagine
 * @date 2023/5/15/0015 - 9:29
 */
//https://hutool.cn/docs/#/http/Http%E8%AF%B7%E6%B1%82-HttpRequest
public class ApiClient {

    private static final String GATEWAY_HOST = "http://localhost:8090";
    private String accessKey;
    private String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getUsernameByPost(String RequestParams) {
//        String json = JSONUtil.toJsonStr(object);
        //调用接口项目中的接口
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/interface/user")//请求地址
                .addHeaders(getHeaderMap(RequestParams))//设置请求头
                .body(RequestParams)//请求参数
                .execute();
        String result = response.body();//调用
        return result;
    }

    /**
     * 发送邮箱
     *
     * @param RequestParams
     * @return
     */
    public String SendMail(String RequestParams) {
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/interface/mail")
                .addHeaders(getHeaderMap(RequestParams))
                .body(RequestParams)
                .execute();
        String result = response.body();
        return result;
    }


    /**
     * 封装请求头
     *
     * @param json
     * @return
     */
    private Map<String, String> getHeaderMap(String json) {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);//标识
//        map.put("secretKey",secretKey);//密钥不可以直接放进请求头
        map.put("nonce", RandomUtil.randomNumbers(4));//加 nonce 随机数 ， 只能用一次
        map.put("body", json);//请求参数
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));//校验时间戳是否过期 。

        map.put("sign", getSign(json, secretKey));//签名


        return map;
    }


}
