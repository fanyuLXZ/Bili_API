package com.dreamwolf.member.business.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hide {
    /**
     * 隐藏部分手机号码
     * @param phone
     * @return
     */
    public  String hidePhoneNum(String phone) {
        String result = "";
        if (phone != null && !"".equals(phone)) {
            result = phone.substring(0, 3) + "****"+ phone.substring(7);
        }
        return result;
    }

    /**
     * 检查是否是电话号码
     *
     * @return
     */
    public  boolean isMobileNum(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
