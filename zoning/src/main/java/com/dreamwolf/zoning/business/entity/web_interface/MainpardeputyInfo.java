package com.dreamwolf.zoning.business.entity.web_interface;

import lombok.Data;

/*根据分区id 或者主分区 子分区*/
@Data
public class MainpardeputyInfo {
    private Mainpartition mainpartition;//主分区
    private Deputydivision deputydivision;//子区名

    public MainpardeputyInfo(Mainpartition mainpartition, Deputydivision deputydivision) {
        this.mainpartition = mainpartition;
        this.deputydivision = deputydivision;
    }
}
