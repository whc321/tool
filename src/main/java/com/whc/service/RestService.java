package com.whc.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhang_jian3 on 2017/7/19.
 */
@Component
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(RestService.class);

    protected HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    /**
     *
     * @param url 请求URL
     * @param param  请求参数
     * @return
     */
    public String post(String url, String param) {
        String json = "";
        HttpHeaders headers = this.createHeaders();
        logger.info("开始调用接口,url=" + url + ",param=" + param);
        HttpEntity<String> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(url, requestEntity, String.class);
            json = response.getBody();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.info("成功调用,返回" + json);
        return json;
    }

    /**
     *
     * @param url  请求地址
     * @param param  请求参数
     * @param number  发送次数  至少1次
     * @param seconds  间隔时间
     * @param typeReference  返回类型
     * @param <T>
     * @return
     */
    public <T extends Serializable> T forPost(String url, String param, int number, int seconds, TypeReference<T> typeReference) {
        Assert.state(number != 0, "至少接口请求1次");
        String[] restUrl = url.split(",");
        String json = "";
        boolean isBreak = false;
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response;
        for (String var : restUrl) {
            int count = 1;
            logger.info("开始调用接口.url=" + var + ",param=" + param);
            while (count <= number) {
                try {
                    if (count > 1 && seconds > 0) {
                        logger.info("接口将在" + seconds + "秒后调用");
                        Thread.sleep(seconds * 1000L);
                    }
                    response = restTemplate.postForEntity(var, requestEntity, String.class);
                    json = response.getBody();
                    isBreak = true;
                    break;
                } catch (Exception ex) {
                    count++;
                    logger.error(ex.getMessage());
                }
            }
            if (isBreak){
                break;
            }
        }
        logger.info("接口调用成功,返回数据=" + json);
        if(!StringUtils.isEmpty(json)){
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(json, typeReference);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }


    /**
     *
     * @param url
     * @param param
     * @return
     */
    public String get(String url, Map<String,Object> param) {
        String json = "";
        logger.info("开始调用接口,url=" + url);
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url,String.class,param);
            json = response.getBody();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.info("成功调用,返回" + json);
        return json;
    }


    /**
     *
     * @param url
     * @return
     */
    public String get(String url) {
        String json = "";
        logger.info("开始调用接口,url=" + url);
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url,String.class);
            json = response.getBody();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.info("成功调用,返回" + json);
        return json;
    }
}
