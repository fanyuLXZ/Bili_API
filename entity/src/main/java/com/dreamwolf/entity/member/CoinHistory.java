package com.dreamwolf.entity.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CoinHistory {
    private Integer chID;//主键
    private Integer uID;//投币人
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paytime;//投币时间
    private Integer vID;//被投币的视频

    public CoinHistory(Integer uID, Integer vID) {
        this.uID = uID;
        this.vID = vID;
    }

    public CoinHistory() {
    }

    public CoinHistory(Integer chID, Integer uID, LocalDateTime paytime, Integer vID) {
        this.chID = chID;
        this.uID = uID;
        this.paytime = paytime;
        this.vID = vID;
    }
}
