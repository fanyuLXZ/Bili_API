package com.dreamwolf.fav.mapper;

import com.dreamwolf.entity.fav.Userfavoritelist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户收藏列表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Mapper
public interface UserfavoritelistMapper extends BaseMapper<Userfavoritelist> {

    /**
     * 收藏夹表对象
     * @return
     */
    public List<Userfavoritelist> selectlistshow();

    /**
     * 根据视频收藏夹id查询创建收藏夹的用户id
     * @param favListID
     * @return
     */
    public Integer selectfavListID(@Param("favListID") Integer favListID);



}
