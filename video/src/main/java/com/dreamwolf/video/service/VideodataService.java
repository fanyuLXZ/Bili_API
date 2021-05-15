package com.dreamwolf.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.video.Videodata;

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
     * 根据视频id数组查询视频数据并按热度查询
     * @param array
     * @return
     */
    public List<Videodata> videodatabvid(Integer[] array);

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

    /**
     * 修改硬币数 根据原硬币数加上传过来的硬币数
     * @param bvid
     * @param CoinNum
     * @return
     */
    public int updatebvCoinNum(Integer bvid,Integer CoinNum);

    /**
     * 修改视频数据表的点赞数据+1
     * @param bvID
     * @return
     */
    public int updateinse(Integer bvID);
}
