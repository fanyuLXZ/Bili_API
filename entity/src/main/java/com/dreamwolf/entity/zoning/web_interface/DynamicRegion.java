package com.dreamwolf.entity.zoning.web_interface;


import com.dreamwolf.entity.video.web_interface.ArchivesInfo;
import lombok.Data;

import java.util.List;

@Data
public class DynamicRegion {
    private Page page;//分页
    private List<ArchivesInfo> listarchives;//对象集合

    public DynamicRegion(Page page, List<ArchivesInfo> listarchives) {
        this.page = page;
        this.listarchives = listarchives;
    }

    public DynamicRegion() {
    }
}
