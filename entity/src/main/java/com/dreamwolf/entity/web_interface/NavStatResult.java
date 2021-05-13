package com.dreamwolf.entity.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavStatResult {
    private String following="0"; // 关注数
    private String follower="0"; // 粉丝数
    private String dynamic_count="0"; // 动态数

}
