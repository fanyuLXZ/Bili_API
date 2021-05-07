package com.dreamwolf.fav.controller;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.fav.Userfavoritelist;
import com.dreamwolf.entity.fav.Videofavorite;
import com.dreamwolf.entity.fav.web_interface.*;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.web_interface.OwnerInfo;
import com.dreamwolf.entity.video.web_interface.VideoCount;
import com.dreamwolf.fav.service.*;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏列表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class UserfavoritelistController {

    @Resource
    private UserfavoritelistService userfavoritelistService; //收藏夹

    @Resource
    private VideofavoriteService videofavoriteService;

    @Resource
    private Videofav videofav;

    @Resource
    private UserlistService selectuser;

    @GetMapping("/folder")
    public ResponseData<FavData> userfavlist(){

        List data = new ArrayList<>();
        int[] array = new int[]{1, 2};
        List<Userfavoritelist> userfavoritelists = userfavoritelistService.selectlist();
        List<FavList> new_userfavoritelists = new ArrayList<>();
        for (Userfavoritelist userfavorite : userfavoritelists) {
            ResponseData<VideoCount> videocount = videofav.selectint(userfavorite.getFavListID());
            FavList favList = new FavList(userfavorite.getFavListID(),userfavorite.getUID(),
                    userfavorite.getName(),videocount.getData().getCount());
            new_userfavoritelists.add(favList);
        }
        MediaListResponse mediaListResponse = new MediaListResponse(userfavoritelists.size(),new_userfavoritelists);   //收藏夹对象
        FavMapOne favMapOne = new FavMapOne(array[0],"我创建的收藏夹",mediaListResponse);//固定值：普通收藏夹为1，稍后再看为2  maplist1
        //                -------------------------------
        MediaListResponse mediaListResponse2 = new MediaListResponse(1,null);
        FavMaoTwo favMaoTwo = new FavMaoTwo(array[1],mediaListResponse2);//固定值：普通收藏夹为1，稍后再看为2  maplist2
        List list = new ArrayList();
        list.add(favMapOne);
        list.add(favMaoTwo);
        FavData favData = new FavData(list);
        return new ResponseData(0,"",1,favData);
    }


    @GetMapping("/resource")
    public ResponseData<FavData> fovresource(Integer media_id){
        //根据收藏夹id(media_id)查询视频
        List<Videofavorite> list= videofavoriteService.selectlist(media_id);
        List<Favvideofav> map = new ArrayList<>();
        for (Videofavorite videofavorite : list) {
            Integer uid = userfavoritelistService.selectfavListID(media_id);//返回用户id
            ResponseData<OwnerInfo> user = selectuser.OwnerInfo(uid);//根据用户id查询的用户对象  //bv号
            Favvideofav data = new Favvideofav(videofavorite.getFavListID(),videofavorite.getVideo().getBvTitle(),
                    videofavorite.getVideo().getBvCoverImgPath(),1,2,"bv"+videofavorite.getVideo().getBvID(),user.getData());

            map.add(data);
        }
        FavData favData = new FavData(map);

        return new ResponseData(0,"",1,favData);
    }





}

