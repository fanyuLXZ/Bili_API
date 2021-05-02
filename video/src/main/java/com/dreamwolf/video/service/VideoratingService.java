package com.dreamwolf.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.video.pojo.Videorating;

import java.util.List;

/**
 * <p>
 * 视频综合评分表，根据该表判断视频排名 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideoratingService extends IService<Videorating> {

    /**
     * 根据视频id(bvID)查询视频评分
     * @param bvID
     * @return
     */
    public Videorating selectbvid(Integer bvID);

    /**
     * 查询视频评分表所有数据
     * @return
     */
    public List<Videorating> selectlist();

}
