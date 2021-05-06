package com.dreamwolf.safety.util;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import sun.misc.BASE64Encoder;

import javax.annotation.Nullable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class TokenUtil {
    /**
     * 创建token方法
     * @return 创建的token
     */
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

    /**
     * 根据请求获取token工具方法
     * @param request 当前请求
     * @return token 若无token 返回null
     */
    @Nullable
    public static String getToken(HttpServletRequest request){
        if (request != null) {
            Cookie[] cks = request.getCookies();
            if (cks != null) {
                for (Cookie cookie : cks) {
                    if ("token".equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return null;
    }
}
