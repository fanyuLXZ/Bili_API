package com.dreamwolf.dynamic.business.service;

import com.dreamwolf.dynamic.business.entity.Userdynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户动态表 服务类
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
public interface UserdynamicService extends IService<Userdynamic> {
    List<Map<String,Object>> listmap(Integer udID,Integer[] shuzu);
}
