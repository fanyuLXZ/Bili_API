package com.dreamwolf.message.service;

import com.dreamwolf.entity.video.Video;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.Videolike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "video-service")
public interface VideoService {


    /**
     * 通过bvid查询对象
     * @return
     */
    @GetMapping("/videobvidlists")
    public Map<String, Video> videobvidlists(@RequestParam Integer bvid);

    //根据视频id数组查询视频评论id 返回list
    @GetMapping("/videocommmarr")
    public List<Integer> selectarr(@RequestParam Integer[] arr);

    /**
     * 根据视频id数组批量查询视频数据
     * @param array
     * @return
     */
    @GetMapping("/videobvidli")
    public List<Video> selectbvidlist(@RequestParam Integer[] array);


    //通过作者id查用户下面所有视频，返回list
    @GetMapping("/videouID")
    public List<Integer> videouID(@RequestParam Integer uID);

    /**
     * 根据用户下面的视频拿到用户下所有的点赞了的视频
     * @param array
     * @return
     */
    @GetMapping("/videolikeuid")
    public List<Videolike> sellist(@RequestParam Integer[] array);

    //根据视频id数组查询视频评论表数据
    @GetMapping("/viderbvidcommlist")
    public List<Videocomment> videocombvidlist(@RequestParam Integer[] array);


    @GetMapping("/videobvID")
    public Map videobvID(@RequestParam Integer bvID);

    /**
     * 根据bvid视频id去查找评论id
     * @param bvid
     * @return
     */
    @GetMapping(value = "/videocommbvid")
    public  List<Videocomment> selectbvids(@RequestParam Integer bvid);




}
