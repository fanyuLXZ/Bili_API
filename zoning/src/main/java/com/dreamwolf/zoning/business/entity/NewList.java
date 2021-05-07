package com.dreamwolf.zoning.business.entity;

import com.dreamwolf.entity.video.web_interface.Result;
import lombok.Data;

import java.util.List;

@Data
public class NewList {
    private Integer numPages;//总页面
    private Integer numResults;//总条数
    private Integer page;//页码
    private Integer pagesize;//每页数据
    private List<Result>  result;//对象集合

    public NewList() {
    }

    public NewList(Integer numPages, Integer numResults, Integer page, Integer pagesize, List<Result> result) {
        this.numPages = numPages;
        this.numResults = numResults;
        this.page = page;
        this.pagesize = pagesize;
        this.result = result;
    }
}
