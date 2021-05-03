package com.dreamwolf.member.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamwolf.entity.member.Relations;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户关系表 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Mapper
public interface RelationsMapper extends BaseMapper<Relations> {

}
