package com.dreamwolf.entity.member;

import lombok.Data;
import lombok.NoArgsConstructor;

//等级对象
@Data
@NoArgsConstructor
public class Level_info {
    private Integer current_level=1;//当前等级
    private Integer current_min=0;//最小经验
    private Long current_exp= 0L;//当前经验值
    private Integer next_exp=200;//需要的经验值

    public Level_info(Integer current_level, Integer current_min, Long current_exp, Integer next_exp) {
        this.current_level = current_level;
        this.current_min = current_min;
        this.current_exp = current_exp;
        this.next_exp = next_exp;
    }

    public Level_info(Integer current_level) {
        this.current_level = current_level;
    }
}
