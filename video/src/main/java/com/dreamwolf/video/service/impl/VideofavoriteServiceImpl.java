package com.dreamwolf.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamwolf.video.mapper.VideofavoriteMapper;
import com.dreamwolf.video.pojo.Videofavorite;
import com.dreamwolf.video.service.VideofavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 视频收藏表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class VideofavoriteServiceImpl extends ServiceImpl<VideofavoriteMapper, Videofavorite> implements VideofavoriteService {

    @Resource
    private VideofavoriteMapper videofavoriteMapper;


    @Override
    public Videofavorite selectbvID(Integer bvID) {
        return videofavoriteMapper.selectById(bvID);
    }

    @Override
    public List<Videofavorite> selectlist() {
        return videofavoriteMapper.selectList(null);
    }

    @Override
    public int selectfavListID(Integer favListID) {
        return videofavoriteMapper.selectfavListID(favListID);
    }
}
