package com.dreamwolf.video.service;

import com.dreamwolf.video.pojo.Videodata;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 视频数据表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideodataService extends IService<Videodata> {

    /**
     * 根据视频id bvID 查询bvID视频的基本数据
     * @param bvID
     * @return
     */
    public Videodata selectbvID(Integer bvID);

    /**
     * 查询视频数据表所有数据
     * @return
     */
    public List<Videodata> selectlist();

}
