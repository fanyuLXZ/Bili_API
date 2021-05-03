package com.dreamwolf.message.mapper;

import com.dreamwolf.entity.message.Usermsgs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户消息表 Mapper 接口
 * </p>
 *
 * @author 老徐
 * @since 2021-04-23
 */
@Mapper
public interface UsermsgsMapper extends BaseMapper<Usermsgs> {

    /**
     * 根据当前用户id查询消息表数据 按最新时间排序
     * @param uid
     * @return
     */
    public List<Usermsgs> selectusermsgs(Integer uid);


    /**
     * 查询聊天记录 根据传过来的当前用户id和对话人的id查询两个人的聊天记录
     * @param userID
     * @param friendID
     * @return
     */
    public List<Usermsgs> usermsgslistuidfid(Integer userID,Integer friendID);

}
