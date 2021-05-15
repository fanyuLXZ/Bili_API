package com.dreamwolf.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.video.Videodata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 视频数据表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface VideodataMapper extends BaseMapper<Videodata> {

    /**
     * 根据视频id数组查询视频数据并按热度查询
     * @param array
     * @return
     */
    public List<Videodata> videodatabvid(Integer[] array);


    /**
     * 根据视频id查询视频数据
     * @param bvID 视频id
     * @return
     */
    public Videodata selectbvid(Integer bvID);

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
