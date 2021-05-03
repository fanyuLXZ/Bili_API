package com.dreamwolf.fav.service.impl;

import com.dreamwolf.entity.fav.Videofavorite;
import com.dreamwolf.fav.mapper.VideofavoriteMapper;
import com.dreamwolf.fav.service.VideofavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideofavoriteServiceImpl implements VideofavoriteService {

    @Resource
    private VideofavoriteMapper videofavoriteMapper;


    @Override
    public List<Videofavorite> selectlist(Integer favListID) {
        return videofavoriteMapper.selectlist(favListID);
    }


}
