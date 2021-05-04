package com.dreamwolf.entity.member.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountInfo {
    private String uname;//昵称
    private String userid;//用户名
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;//出生年月
    private String sex;//性别

    public AccountInfo(String uname, String userid, LocalDateTime birthday, String sex) {
        this.uname = uname;
        this.userid = userid;
        this.birthday = birthday;
        this.sex = sex;
    }
}
