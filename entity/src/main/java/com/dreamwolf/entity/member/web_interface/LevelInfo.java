package com.dreamwolf.entity.member.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 等级信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelInfo {
    private Integer current_level;//当前等级
    private Integer current_min;//最小经验
    private Long current_exp;//当前经验值
    private Integer next_exp;//需要的经验值
}
