package com.dreamwolf.member.business.util;

import java.security.MessageDigest;

public class md5 {
    public String message(String str) throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(str.getBytes());
        byte s[]=m.digest();
        System.out.println(m.getProvider().getInfo());
        String result="";
        for (int i=0; i<s.length;i++){
            result+=Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            /*result+= Integer.toHexString(s[i] & 0xFF);
            if (result.length() == 1) {
                result+= "0"+result;
            }*/
        }
        System.out.print(result);
        return result;
    }
}

