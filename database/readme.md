# 数据库说明文档

## 用户表

|       列名       |   数据类型   |              说明              |
| :--------------: | :----------: | :----------------------------: |
|       UID        |    int(9)    |   用户唯一 ID，显示长度为 9    |
|     UserName     | varchar(16)  |      用户名称，长度为 16       |
|     NickName     | varchar(16)  |      用户昵称，长度为 16       |
|     Birthday     |     date     |        用户生日，可为空        |
|      Level       |     int      |            用户等级            |
|       Exp        |    bigint    |            用户经验            |
|     CoinsNum     |    double    |        用户现有硬币数量        |
|    BCoinsNum     |     int      |       用户现有 B 币数量        |
|    BoundEmail    | varchar(50)  |           被绑定邮箱           |
|    BoundPhone    | varchar(11)  |          被绑定手机号          |
|     BoundQQ      | varchar(15)  |           被绑定 QQ            |
| LastBeBigMembers |     date     |       上次开通大会员日期       |
|   Description    | varchar(200) |            用户简介            |
|   MemberPoint    |     int      | 大会员点数，用于兑换大会员[^1] |

     以下属性通过计算得出

|       列名       | 数据类型 |          说明          |
| :--------------: | :------: | :--------------------: |
|    ConcemsNum    |   int    |    关注 用户 总数量    |
|     FansNum      |   int    |        粉丝数量        |
|   isBigMembers   | tinyint  |      是否为大会员      |
| isBigMembersYear | tinyint  |    是否为年度大会员    |
|   isTodayLogin   | tinyint  |     今日是否已登录     |
|   isTodayPlay    | tinyint  |   今日是否已播放视频   |
|   todayCoinNum   |   int    |    今日投币数量[^2]    |
|     PlayNum      |   int    | 所有发布视频的总播放数 |
|     ReadsNum     |   int    | 所有发布专栏的总阅读数 |
|     LikesNum     |   int    | 所有发布作品的总点赞数 |
|    DynamicNum    |   int    |        动态数量        |

根据该表衍生出以下表

番剧为特殊视频，没有收藏选项

增删改使用mysql，查询使用redis保证性能。

大会员数据主要通过修改来操作，每个用户对应一条大会员信息
确定是否为年度大会员则通过时间进行计算

用户关系表仅使用两列数据记录

所有排名均通过Raiting表中的分数计算进行排行，可使用redis动态修改数据

标签表的第一层，也就是树根层代表分区，第二层细分

简单说明下个人的推荐算法，
每部视频的初始综合评分都为0
|       行为       |   数据类型   |
| :--------------: | :----------: |
|观看视频|+0.1分|
|发弹幕|+0.5分|
|点赞|+1分| 
|硬币|每个+1分|
|评论|+1.5分|
|收藏|+2分|
|转发|+3分|

四舍五入总评分

至于排序，结合tag条件和分数高低进行无脑排序即可


视频历史记录方面，遇到的问题是视频记录的时间参数问题，
视频分P如何存储没有定论，先搁置。

![img](Ceylan.png)


关于个人消息表:
https://www.oschina.net/question/12_70252?sort=default&p=1

动态图片和其他图片 以路径的形式 统一存放在图片表中

在引用时可以使用json格式进行操作





[^1]: 大会员点数来源于承包番剧
[^2]: 前五个币每个币+10Exp



select sum(bvlikeNum) from videoData
where bvID in (
     select bvID from video 
     where uID = uID
) 

create table user_like(
     id bigint(20) unsigned not null auto_increment comment 'id',
     
     user_id bigint(20) not null default 0 comment '用户id',

     liked_id varchar(21) not null default '' comment '被点赞动态的id',

     liked_status int(11) not null default 0 comment '点赞状态，0未点赞，1已点赞',

     liked_time timestamp not null default '0000-00-00 00:00:00.000000' comment '点赞时间',

     is_delete tinyint not null default '0' comment '是否逻辑删除',

     create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',

     update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
     
)ROW_FORMAT=COMPACT COMMENT='用户点赞表';


create table user_like_stat(
     id bigint(20) unsigned not null auto_increment comment 'id',

     liked_id varchar(21) not null default '' comment '被点赞动态id',

     liked_count int(11) not null default 0 comment '点赞总数量',

     is_delete tinyint not null default '0' comment '是否逻辑删除',

     create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
     update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
)ROW_FORMAT=COMPACT COMMENT='点赞统计表';


对于评论的存储方法，使用一个列（artworkID）
统一存储三个表中的不同数据类型和数据精度的ID，

在此之前需要通过Type列的值判断评论或点赞的作品对应的表