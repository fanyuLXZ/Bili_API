package com.dreamwolf.entity.fav.web_interface;

import com.dreamwolf.entity.member.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favvideofav {
    private Integer id;//收藏夹id
    private String title;//标题
    private String cover;//封面
    private Integer page;
    private Integer type;
//    private usermap;//用户对象
    private String bvid;//bv号


}
