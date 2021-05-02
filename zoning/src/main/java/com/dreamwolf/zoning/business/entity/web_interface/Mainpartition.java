package com.dreamwolf.zoning.business.entity.web_interface;

import com.dreamwolf.zoning.business.entity.Zoning;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//主分区
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mainpartition {
    private Integer id;//主分区
    private String name;//子区名

    public Mainpartition(Zoning zoning) {
        this.id = zoning.getzID();
        this.name = zoning.getzName();
    }
}
