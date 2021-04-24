package com.dreamwolf.fav.service;

import com.dreamwolf.fav.pojo.Userfavoritelist;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户收藏列表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
public interface UserfavoritelistService{

    /**
     * 所有收藏夹对象
     * @return
     */
    public List<Userfavoritelist> selectlist();

    /**
     * 根据视频收藏夹id查询创建收藏夹的用户id
     * @param favListID
     * @return
     */
    public Integer selectfavListID(Integer favListID);

}
