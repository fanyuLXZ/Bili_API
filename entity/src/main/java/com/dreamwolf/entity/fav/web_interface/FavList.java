package com.dreamwolf.entity.fav.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavList {

    private Integer id;
    private Integer mid;
    private String title;
    private Integer media_count;

}
