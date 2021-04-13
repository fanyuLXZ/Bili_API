package com.dreamwolf.safety.util;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class TokenUtil {
    public static String creat(){
        String token = (System.currentTimeMillis() + new Random().nextInt(233666)) + "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] md5 =  md.digest(token.getBytes());
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(md5);
    }


}
