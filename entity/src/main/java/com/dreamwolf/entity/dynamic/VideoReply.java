package com.dreamwolf.entity.dynamic;

import com.dreamwolf.entity.comment.web_interface.CommListMap;
import lombok.Data;

import java.util.List;

//评论信息
@Data
public class VideoReply {
    private Page page;//评论分页
    private List<CommListMap> replies;//评论对象集合

    public VideoReply(Page page, List<CommListMap> commListMaps) {
        this.page = page;
        this.replies = commListMaps;
    }
}
