package com.dreamwolf.video.service;

import com.dreamwolf.video.pojo.Videocomment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideocommentService extends IService<Videocomment> {

    /**
     * 根据bvID 视频id查询视频下面的评论id
     * @param bvID
     * @return
     */
    public List<Videocomment> selectbvID(Integer bvID);

    /**
     * 查询全部数据
     * @return
     */
    public List<Videocomment> selectlist();


}
