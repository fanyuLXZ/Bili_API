package com.dreamwolf.safety.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeUtil {
    public static int getTomorrowSeconds(){
        Date currentTime = new Date();
        //从一个 Instant和区域ID获得 LocalDateTime实例
        LocalDateTime localDateTime=LocalDateTime.ofInstant(currentTime.toInstant(), ZoneId.systemDefault());
        //获取第第二天零点时刻的实例
        LocalDateTime toromorrowTime=LocalDateTime.ofInstant(currentTime.toInstant(), ZoneId.systemDefault())
                .plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        //ChronoUnit日期枚举类,between方法计算两个时间对象之间的时间量
        long seconds = ChronoUnit.SECONDS.between(localDateTime, toromorrowTime);
        return (int)seconds;
    }
}
