# 数据库说明文档

> ### 注意事项
>
> 本'仿制'项目的数据库部分使用 MySQL 和 redis
>
> 大部分数据库操作都将在 redis 中，而不是 MySQL 中
>
> 使用 redis 的定时任务来操作 MySQL 保证性能

## 个人信息类

### 用户表

|     列名     |  数据类型   |       说明       |
| :----------: | :---------: | :--------------: |
|    `uID`     |   int(9)    |  '用户唯一 ID'   |
|  `userName`  | varchar(16) | '用户名（唯一）' |
|  `Password`  | varchar(16) |    '用户密码'    |
|  `NickName`  | varchar(16) |    '用户昵称'    |
|  `Birthday`  |  timestamp  |    '用户生日'    |
| `BoundEmail` | varchar(50) |   '被绑定邮箱'   |
| `BoundPhone` | varchar(11) |  '被绑定手机号'  |
|  `BoundQQ`   | varchar(15) |   '被绑定 qq'    |

### 用户个人数据表

|      列名      |   数据类型   |           说明           |
| :------------: | :----------: | :----------------------: |
|     `uID`      |     int      |         用户 ID          |
|    `Level`     |     int      |         用户等级         |
|     `Exp`      |    bigint    |       用户现有经验       |
|   `CoinsNum`   |    double    |     用户现有硬币数量     |
|  `BCoinsNum`   |    bigint    |    用户现有 B 币数量     |
|  `tFollowNum`  |    bigint    |   用户总关注 up 主数量   |
|   `tFansNum`   |    bigint    |      用户总粉丝数量      |
|   `tLikeNum`   |    bigint    |       用户总获赞数       |
|  `tPlaysNum`   |    bigint    | 用户上传的视频的总播放数 |
|   `tReadNum`   |    bigint    | 用户撰写的专栏的总阅读数 |
| `uDescription` | varchar(200) |         用户简介         |

### 用户关系表

|    列名     | 数据类型 |    说明     |
| :---------: | :------: | :---------: |
|    `uID`    |  int(9)  | 用户本人 ID |
| `followUID` |  int(9)  |   粉丝 ID   |

### 用户大会员信息表

|        列名        | 数据类型  |        说明        |
| :----------------: | :-------: | :----------------: |
|       `bmID`       |    int    |     主键无意义     |
|       `uID`        |  int(9)   |      用户 ID       |
|  `ExpirationTime`  | timestamp |   大会员过期时间   |
| `LastBeBigMembers` | timestamp | 上次开通大会员日期 |
|   `MemberPoint`    |    int    |      会员积分      |

---

## 视频主站类

### 视频基础信息表

|      列名      |   数据类型   |                说明                |
| :------------: | :----------: | :--------------------------------: |
|     `bvID`     |   int(10)    |               bv 号                |
|     `uID`      |    int(9)    |            视频作者 ID             |
|     `vfID`     |     int      |          视频文件路径 ID           |
| `bvCoverImgID` |     int      |    视频封面图 ID，关联图片表 ID    |
|   `bvTitle`    | varchar(40)  |              视频标题              |
|    `bvDesc`    |     text     |              视频简介              |
|  `bvPostTime`  |  timestamp   |   视频上传日期（默认为当前时间）   |
|    `bvTags`    | varchar(150) |   视频标签，格式为[id,id,id,...]   |
|   `bvIsDel`    |   tinyint    | 是否已删除，0 为未删除，1 为已删除 |

### 视频数据表

|      列名       | 数据类型 |      说明      |
| :-------------: | :------: | :------------: |
|     `bvID`      | int(10)  | 视频对应 BV 号 |
|   `bvPlayNum`   |  bigint  |   视频播放数   |
|  `bvPopupsNum`  |  bigint  |   视频弹幕数   |
|   `bvLikeNum`   |  bigint  |    视频顶数    |
|   `bvCoinNum`   |  bigint  |   视频硬币数   |
| `bvFavoriteNum` |  bigint  |   视频收藏数   |
| `bvRetweetNum`  |  bigint  |   视频转发数   |
| `bvCommentNum`  |  bigint  |   视频评论数   |

### 视频综合评分表

|     列名      |   数据类型   |     说明      |
| :-----------: | :----------: | :-----------: |
|     bvID      |   int(10)    |    视频 ID    |
| OverallRating | double(10,1) | 视频综合评分. |

简单说明下个人的推荐算法，

|   行为   | 数据类型  |
| :------: | :-------: |
| 观看视频 |  +0.1 分  |
|  发弹幕  |  +0.5 分  |
|   点赞   |   +1 分   |
|   硬币   | 每个+1 分 |
|   评论   |  +1.5 分  |
|   收藏   |   +2 分   |
|   转发   |   +3 分   |

每部视频的初始综合评分都为 0

### 视频点赞表

|     列名     | 数据类型  |      说明       |
| :----------: | :-------: | :-------------: |
|    `bvID`    |  int(10)  |     视频 ID     |
|    `uID`     |  int(9)   |  点赞的用户 ID  |
|   `status`   |  tinyint  | 点赞状态[^tips] |
| `createTime` | timestamp |    点赞时间     |

[^tips]: 0 为未作任何操作,1 为点赞,默认为 0

### 视频收藏表

|    列名     | 数据类型  |       说明        |
| :---------: | :-------: | :---------------: |
|   `bvID`    |  int(10)  |  被收藏的视频 ID  |
| `favListID` |    int    |   视频收藏夹 ID   |
|  `favTime`  | timestamp | 收藏时间[^tips-1] |

[^tips-1]: 默认为当前时间

### 番剧表

|   列名   |  数据类型   |         说明          |
| :------: | :---------: | :-------------------: |
|  `bID`   | VARCHAR(7)  |     番剧 ID，唯一     |
|  `bvID`  |   INT(10)   |      对应视频 ID      |
| `bScore` | DOUBLE(1,1) | 番剧评分，最大 9.9 分 |

> 该表 存在价值 存疑

---

### 专栏基础信息表

|      列名      |   数据类型   |                说明                |
| :------------: | :----------: | :--------------------------------: |
|     `cvID`     |    INT(8)    |       专栏文章 ID，主键自增        |
|     `uID`      |    INT(9)    |          专栏文章作者 ID           |
| `cvCoverImgID` |     INT      |    专栏标题图片 ID，关联图片表     |
|   `cvTitle`    | VARCHAR(40)  |             专栏标题表             |
|  `cvPostTime`  |  TIMESTAMP   |          专栏文章上传时间          |
|    `cvTags`    | VARCHAR(150) |    专栏标签，格式为[id,id,...]     |
|    `cvText`    |   LONGTEXT   |              专栏正文              |
|   `cvIsDel`    |   TINYINT    | 是否已删除，0 为未删除，1 为已删除 |


番剧为特殊视频，没有收藏选项

大会员数据主要通过修改来操作，每个用户对应一条大会员信息
确定是否为年度大会员则通过时间进行计算

用户关系表仅使用两列数据记录

所有排名均通过 Raiting 表中的分数计算进行排行，可使用 redis 动态修改数据

标签表的第一层，也就是树根层代表分区，第二层细分

四舍五入总评分

至于排序，结合 tag 条件和分数高低进行无脑排序即可

视频历史记录方面，遇到的问题是视频记录的时间参数问题，
视频分 P 如何存储没有定论，先搁置。

![img](Ceylan.png)

关于个人消息表:
https://www.oschina.net/question/12_70252?sort=default&p=1

动态图片和其他图片 以路径的形式 统一存放在图片表中

在引用时可以使用 json 格式进行操作

[^1]: 大会员点数来源于承包番剧
[^2]: 前五个币每个币+10Exp

select sum(bvlikeNum) from videoData
where bvID in (
select bvID from video
where uID = uID
)

对于评论的存储方法，使用一个列（artworkID）
统一存储三个表中的不同数据类型和数据精度的 ID，

在此之前需要通过 Type 列的值判断评论或点赞的作品对应的表


这是一些更改