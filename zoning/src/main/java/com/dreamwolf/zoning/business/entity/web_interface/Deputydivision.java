package com.dreamwolf.zoning.business.entity.web_interface;


import com.dreamwolf.zoning.business.entity.Zoning;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//子分区
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deputydivision {
    private Integer id;//主分区
    private String name;//子区名

    public Deputydivision(Zoning zoning) {
        this.id = zoning.getzID();
        this.name = zoning.getzName();
    }

}
