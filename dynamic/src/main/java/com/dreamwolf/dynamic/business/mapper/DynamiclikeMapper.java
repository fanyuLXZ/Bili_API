package com.dreamwolf.dynamic.business.mapper;

import com.dreamwolf.entity.dynamic.Dynamiclike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 动态点赞表，用于区分用户点赞 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Mapper
public interface DynamiclikeMapper extends BaseMapper<Dynamiclike> {

}
