package com.dreamwolf.entity.member.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class VipInfo {
    private boolean vip_status;//是否是会员
    private Integer vip_type;//会员类型
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime due_date;//会员有效时间

    public VipInfo(boolean vip_status, Integer vip_type, LocalDateTime due_date){
        this.vip_status = vip_status;
        this.vip_type = vip_type;
        this.due_date = due_date;
    }
}
