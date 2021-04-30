package com.dreamwolf.message.service;

import com.dreamwolf.message.pojo.Usermsgs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户消息表 服务类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-23
 */
public interface UsermsgsService extends IService<Usermsgs> {

    /**
     * 根据当前用户id查询消息表数据 按最新时间排序
     * @param uid
     * @return
     */
    public List<Usermsgs> selectusermsgs(Integer uid);

    /**
     * 根据接收信息的用户friendID 查询数据
     * @param friendID
     * @return
     */
    public List<Usermsgs> selectusmsgsfid(Integer friendID);

    /**
     * 根据发送信息的用户userID 查询数据
     * @param userID
     * @return
     */
    public List<Usermsgs> selectusmsgsuid(Integer userID);

    /**
     * 查询聊天记录 根据传过来的当前用户id和对话人的id查询两个人的聊天记录
     * @param userID
     * @param friendID
     * @return
     */
    public List<Usermsgs> usermsgslistuidfid(Integer userID,Integer friendID);

}
