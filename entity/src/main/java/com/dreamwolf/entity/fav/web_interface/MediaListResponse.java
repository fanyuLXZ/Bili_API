package com.dreamwolf.entity.fav.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaListResponse {

    private Integer count;//数量
    private List list;

}
