package com.gdutelc.recruit.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdutelc.recruit.entities.wx.LoginInfo;
import com.gdutelc.recruit.service.Code2Session_Wx;
import com.gdutelc.recruit.utils.GenericUtils;
import com.gdutelc.recruit.utils.SomeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.gdutelc.recruit.utils.ResultStatusCode.SUCCESS;


@Service
public class Code2SessionImpl implements Code2Session_Wx {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value(value = "${wx.url.code2Session}")
    private String url;

    @Override
    public LoginInfo code2Session(String appid, String secret, String js_code, String grant_type) throws JsonProcessingException {
        // String url = SomeUtils.getValueFromFile("code2Session");
        Map<String,String> params = new HashMap<>();
        params.put("appid",appid);
        params.put("secret",secret);
        params.put("js_code",js_code);
        params.put("grant_type",grant_type);

        url = GenericUtils.splicingUrlStr(url,params);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if(entity.getStatusCodeValue() != SUCCESS){
            return null;
        }
        String body = entity.getBody();
        LoginInfo loginInfo = objectMapper.readValue(body, LoginInfo.class);
        return loginInfo;
    }
}
