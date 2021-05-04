package com.dreamwolf.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamwolf.entity.video.Video;

import java.util.List;

/**
 * <p>
 * 视频基础信息表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface VideoService extends IService<Video> {

    /**
     * 根据子分区id查询视频总数
     * @param rid
     * @return
     */
    public Integer selectridcount(Integer rid);

    /**
     * 按热度查询的id查询视频数据并显示前10条
     * @param array
     * @return
     */
    public List<Video> selectlispagelsit(Integer[] array);


    /**
     * 按热度查询的id查询视频并分页
     * @param array
     * @param pn
     * @param ps
     * @return
     */
    public List<Video> selectvideovlidlsit(Integer[] array,int pn,int ps);

    /**
     * 根据子分区id查询所有视频id集合
     * @param rid
     * @return
     */
    public List<Integer> videoselezoingid(Integer rid);

    /**
     * 根据子分区id查询最新的视频数据集合
     * @param rid
     * @param pn
     * @param ps
     * @return
     */
    public List<Video> videolistselectpage(Integer rid,Integer pn,Integer ps);

    /**
     * 根据子分区id查询最新的4条数据
     * @param rid
     * @return
     */
    public List<Video> videoliselectridlist(Integer rid,Integer pn,Integer ps);

    /**
     * 查询不等于当前视频id的随机20条数据
     * @param bvid
     * @param pagesum
     * @return
     */
    public List<Video> selecvideolistpa(Integer bvid,int pagesum);

    /**
     * 根据bvid数组查询对象 返回集合
     * @param bvidlist bvid数组
     * @return
     */
    public List<Video> selectlistBvid(Integer[] bvidlist);


    /**
     * 根据日期查询并返回子分区和子分区id的数量
     * @param bvPostTime
     * @return
     */
    public List<Video> selectcoutbvid(String bvPostTime);

    /**
     * 根据子分区id查找视频并分页处理
     * @param bvChildZoninglist 子分区集合
     * @param pageSize 从第几页开始
     * @param pagecount 一页显示多少条
     * @return
     */
    public List<Video> videoPagebvzoing(Integer[] bvChildZoninglist,Integer pageSize,Integer pagecount);


    /**
     * 根据子分区id查找视频返回视频总数
     * @param bvChildZoninglist 子分区集合
     * @return
     */
    public Integer videocount(Integer[] bvChildZoninglist);

    /**
     * 查video的所有数据
     * @return
     */
    public List<Video> selectlist();

    /**
     * 通过子分区id查视频,返回list
     * @param bvChildZoning
     * @return
     */
    public List<Video> videoZoningIdlist(Integer[] bvChildZoning,String date, String datetime);

    /**
     * 通过bv号查视频，返回List
     * @param bvID
     * @return
     */
    public Video videobvIDlist(Integer bvID);

    /**
     * 通过作者id查视频，返回list
     * @param uID
     * @return
     */
    public List<Integer> videouIDlist(Integer uID);



}
