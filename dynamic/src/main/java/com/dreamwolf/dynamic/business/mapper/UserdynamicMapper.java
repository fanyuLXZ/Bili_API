package com.dreamwolf.dynamic.business.mapper;

import com.dreamwolf.entity.dynamic.Userdynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户动态表 Mapper 接口
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Mapper
public interface UserdynamicMapper extends BaseMapper<Userdynamic> {

    List<Map<String,Object>> listmap(@Param("udID")Integer udID,Integer[] shuzu);
}
