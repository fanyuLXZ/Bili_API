# 数据库说明文档 📕

> ### 注意事项 ⚠
>
> 本'仿制'项目的数据库部分使用 MySQL 和 redis
>
> 大部分数据库操作都将在 redis 中，而不是 MySQL 中
>
> 使用 redis 的定时任务来操作 MySQL 保证性能
>
> ER 图使用 MySQL Workbench 打开

## 个人信息类

### 用户表

|     列名      |   数据类型   |      说明      |
| :-----------: | :----------: | :------------: |
|     `uID`     |    int(9)    |  用户唯一 ID   |
|  `userName`   | varchar(16)  | 用户名（唯一） |
|  `password`   | varchar(16)  |    用户密码    |
|  `nickName`   | varchar(16)  |    用户昵称    |
|     `sex`     |    int(1)    |    用户性别    |
|  `birthday`   |  timestamp   |    用户生日    |
| `boundEmail`  | varchar(50)  |   被绑定邮箱   |
| `boundPhone`  | varchar(11)  |  被绑定手机号  |
|   `boundQQ`   | varchar(15)  |   被绑定 qq    |
| `headImgPath` | VARCHAR(255) |  用户头像路径  |

> 性别说明：1 为男，2 为女，0 为私密

### 用户个人数据表

|      列名      |   数据类型   |           说明           | 默认值 |
| :------------: | :----------: | :----------------------: | :----: |
|     `uID`      |     int      |         用户 ID          |        |
|    `Level`     |     int      |         用户等级         |   0    |
|     `Exp`      |    bigint    |       用户现有经验       |   0    |
|   `CoinsNum`   |    double    |     用户现有硬币数量     |   0    |
|  `BCoinsNum`   |    bigint    |    用户现有 B 币数量     |   0    |
|  `tFollowNum`  |    bigint    |   用户总关注 up 主数量   |   0    |
|   `tFansNum`   |    bigint    |      用户总粉丝数量      |   0    |
|   `tLikeNum`   |    bigint    |       用户总获赞数       |   0    |
|  `tPlaysNum`   |    bigint    | 用户上传的视频的总播放数 |   0    |
|   `tReadNum`   |    bigint    | 用户撰写的专栏的总阅读数 |   0    |
| `uDescription` | varchar(200) |         用户简介         |        |

> 为什么要去存储 <u>数量类型</u> 的数据？
>
> 从长远角度考虑，如若视频数据丢失或任何与这些数量的列关联的表项被删除，
> 历史的个人数据不会受影响。
>
> 取出数据也会方便一些

### 用户关系表

|    列名     | 数据类型 |    说明     |
| :---------: | :------: | :---------: |
|    `uID`    |  int(9)  | 用户本人 ID |
| `followUID` |  int(9)  |   粉丝 ID   |

> 双主键保证数据不重复

### 用户大会员信息表

|       列名       | 数据类型  |      说明      | 默认值 |
| :--------------: | :-------: | :------------: | :----: |
|      `bmID`      |    int    |   主键无意义   |        |
|      `uID`       |  int(9)   |    用户 ID     |        |
| `ExpirationTime` | timestamp | 大会员过期时间 |        |
|  `MemberPoint`   |    int    |    会员积分    |   0    |

> 大会员数据主要通过修改来操作
> 每个用户对应一条大会员信息
>
> 确定大会员类型（年度、月度、季度）则通过时间进行计算
> 会员积分来源于承包番剧，会员积分可以兑换大会员，详见:
> https://account.bilibili.com/account/points

---

## 视频主站类

### 视频基础信息表

|       列名       |   数据类型   |     说明      |       默认值        |
| :--------------: | :----------: | :-----------: | :-----------------: |
|      `bvID`      |   int(10)    |     bv 号     |                     |
|      `uID`       |    int(9)    |  视频作者 ID  |                     |
| `bvCoverImgPath` | varchar(255) |  视频封面图   |                     |
|  `bvVideoPath`   | varchar(255) | 视频文件路径  |                     |
|    `bvTitle`     | varchar(40)  |   视频标题    |                     |
|     `bvDesc`     |     text     |   视频简介    |                     |
|   `bvPostTime`   |  timestamp   | 视频上传日期  | CURRENT_TIMESTAMP() |
|   `bvChildTag`   |     int      | 视频子分区 ID |                     |
|    `bvIsDel`     |   tinyint    |  是否已删除   |          0          |

> bvID 使用 0 补足
>
> bvCoverImgID 列直接关联图片表 ID
>
> bvPostTime 列默认为当前时间
>
> bvIsDel 列说明：0 为未删除，1 为已删除
>
> 视频分 P 如何存储没有定论，先搁置。

### 视频数据表

|      列名       | 数据类型 |      说明      | 默认值 |
| :-------------: | :------: | :------------: | :----: |
|     `bvID`      | int(10)  | 视频对应 BV 号 |        |
|   `bvPlayNum`   |  bigint  |   视频播放数   |   0    |
|  `bvPopupsNum`  |  bigint  |   视频弹幕数   |   0    |
|   `bvLikeNum`   |  bigint  |    视频顶数    |   0    |
|   `bvCoinNum`   |  bigint  |   视频硬币数   |   0    |
| `bvFavoriteNum` |  bigint  |   视频收藏数   |   0    |
| `bvRetweetNum`  |  bigint  |   视频转发数   |   0    |
| `bvCommentNum`  |  bigint  |   视频评论数   |   0    |

### 视频综合评分表

|      列名       |   数据类型   |     说明      | 默认值 |
| :-------------: | :----------: | :-----------: | :----: |
|     `bvID`      |   int(10)    |    视频 ID    |        |
| `OverallRating` | double(10,1) | 视频综合评分. |   0    |

> 简单说明下个人的推荐算法，
>
> |   行为   | 数据类型  |
> | :------: | :-------: |
> | 观看视频 |  +0.1 分  |
> |  发弹幕  |  +0.5 分  |
> |   点赞   |   +1 分   |
> |   硬币   | 每个+1 分 |
> |   评论   |  +1.5 分  |
> |   收藏   |   +2 分   |
> |   转发   |   +3 分   |
>
> 每部视频的初始综合评分都为 0
> 本项目中所有关于排行榜判断均通过 Raiting 表中的分数计算进行排行。
>
> 例子： 如果我想查询时下最热门的前 10 个视频，我就可以遍历这个表，
>
> 找出所有评分，并对他们排序，获取它们的视频 ID 即可
>
> 可使用 redis 动态修改数据，四舍五入总评分再进行排名。

### 视频点赞表

|     列名     | 数据类型  |     说明      |       默认值        |
| :----------: | :-------: | :-----------: | :-----------------: |
|    `bvID`    |  int(10)  |    视频 ID    |                     |
|    `uID`     |  int(9)   | 点赞的用户 ID |                     |
|   `status`   |  tinyint  |   点赞状态    |          0          |
| `createTime` | timestamp |   点赞时间    | CURRENT_TIMESTAMP() |

> 点赞状态的区分：0 为未点赞，1 为已点赞。
>
> 点赞时间默认为当前时间

### 视频收藏表

|    列名     | 数据类型  |      说明       |       默认值        |
| :---------: | :-------: | :-------------: | :-----------------: |
|   `bvID`    |  int(10)  | 被收藏的视频 ID |                     |
| `favListID` |    int    |  视频收藏夹 ID  |                     |
|  `favTime`  | timestamp |    收藏时间     | CURRENT_TIMESTAMP() |

> favTime 默认为当前时间

---

## 分区类

### 分区表

|  列名   |  数据类型   |    说明     |
| :-----: | :---------: | :---------: |
|  `zID`  |     int     | 分区唯一 ID |
| `zName` | varchar(50) |  分区名称   |

### 分区关系表

|  列名  | 数据类型 |     说明     |
| :----: | :------: | :----------: |
| `trID` |   int    | 主键，无意义 |
| `bvID` | int(10)  |   视频 id    |
| `zID`  |   int    |   分区 id    |

---

## 动态类

### 用户动态表

|     列名     | 数据类型  |         说明          |       默认值        |
| :----------: | :-------: | :-------------------: | :-----------------: |
|    `udID`    |    int    | 用户动态 ID，主键自增 |                     |
|    `uID`     |  int(9)   |        用户 ID        |                     |
|  `content`   |   text    |       动态正文        |                     |
| `updateTime` | timestamp |    发表动态的时间     | CURRENT_TIMESTAMP() |
|   `isDel`    |  tinyint  |   动态是否已经删除    |          0          |

### 用户动态数据表

|      列名      | 数据类型 |     说明     | 默认值 |
| :------------: | :------: | :----------: | :----: |
|     `udID`     |   int    |   动态 ID    |        |
|  `udLikeNum`   |  bigint  | 动态点赞总数 |   0    |
| `udRetweetNum` |  bigint  |  动态转发数  |   0    |
| `udCommentNum` |  bigint  |  动态评论数  |   0    |

### 用户动态点赞表

用于区分用户点赞

|     列名     | 数据类型  |      说明       |       默认值        |
| :----------: | :-------: | :-------------: | :-----------------: |
|    `udID`    |    int    | 被点赞的动态 ID |                     |
|    `uID`     |  int(9)   |   点赞用户 ID   |                     |
|   `status`   |  tinyint  |    点赞状态     |          0          |
| `createTime` | timestamp |    点赞时间     | CURRENT_TIMESTAMP() |

---

## 评论类

### 评论表

|     列名     | 数据类型  |         说明          | 默认值 |
| :----------: | :-------: | :-------------------: | :----: |
|    `cID`     |    int    | 用户评论 ID，主键自增 |        |
|    `uID`     |  int(9)   |   撰写评论的用户 ID   |        |
| `cID_reply`  |    int    |     回复的评论 ID     |        |
| `createTime` | timestamp |       评论时间        |        |
|   `cText`    |   text    |       评论正文        |        |
|   `isDel`    |  tinyint  |      是否已删除       |   0    |

> isDel 列说明：0 为未删除，1 为已删除

### 视频评论关系表

| 列名 | 数据类型  |  说明   |
| :--: | :-------: | :-----: |
| bvID | int（10） | 视频 ID |
| cID  |    int    | 评论 ID |

### 动态评论关系表

| 列名 | 数据类型  |  说明   |
| :--: | :-------: | :-----: |
| bvID | int（10） | 动态 ID |
| cID  |    int    | 评论 ID |

### 用户评论数据表

|     列名     | 数据类型 |   说明   | 默认值 |
| :----------: | :------: | :------: | :----: |
|    `cID`     |   int    | 评论 ID  |        |
|  `cLikeNum`  |  bigint  | 点赞数量 |   0    |
| `cunLikeNum` |  bigint  | 点踩数量 |   0    |

### 用户评论点赞表

|     列名     | 数据类型  |      说明       |       默认值        |
| :----------: | :-------: | :-------------: | :-----------------: |
|    `cID`     |    int    | 被点赞的评论 ID |                     |
|    `uID`     |  int(9)   |  点赞的用户 ID  |                     |
|   `status`   |  int(1)   |    点赞状态     |          0          |
| `createTime` | timestamp |    点赞时间     | CURRENT_TIMESTAMP() |

---

## 用户其他类

### 用户观看历史表

|        列名        | 数据类型  |           说明           |       默认值        |
| :----------------: | :-------: | :----------------------: | :-----------------: |
|       `uID`        |  int(9)   |         用户 ID          |                     |
|       `cvID`       |  int(8)   |         文章 ID          |                     |
|       `bvID`       |  int(10)  |         视频 ID          |                     |
|    `CloseTime`     | timestamp |       关闭媒体时间       | CURRENT_TIMESTAMP() |
| `TimelinePosition` |   time    | 视频关闭时，时间线的位置 |                     |

> 视频历史记录方面，遇到的问题是视频记录的时间参数问题，

### 用户消息表

|     列名     |  数据类型   |        说明         |       默认值        |
| :----------: | :---------: | :-----------------: | :-----------------: |
|    `umID`    |     int     | 主键自增 ID，无意义 |                     |
|   `userID`   |   int(9)    |  发送信息的用户 ID  |                     |
|  `friendID`  |   int(9)    |  接收信息的用户 ID  |                     |
|   `sender`   | varchar(16) |     留言发送者      |                     |
|  `receiver`  | varchar(16) |     留言接收者      |                     |
| `updateTime` |  timestamp  |    发送信息时间     | CURRENT_TIMESTAMP() |
|  `content`   |    text     |      留言内容       |                     |

> 另见：消息表的数据库模型
>
> https://www.oschina.net/question/12_70252?sort=default&p=1

### 用户收藏夹表

|    列名     |   数据类型   |          说明           | 默认值 |
| :---------: | :----------: | :---------------------: | :----: |
| `favListID` |     int      | 视频收藏夹 ID，主键唯一 |        |
|    `uID`    |    int(9)    |   创建收藏夹的用户 ID   |        |
|   `name`    | varchar(20)  |  收藏夹名称，可以重复   |        |
| `isSecret`  |   tinyint    |    是否为私密收藏夹     |   1    |
| `tLikeNum`  |     int      |    收藏夹的总点赞数     |   0    |
|   `desc`    | varchar(200) |       收藏夹描述        |        |

> isSecret 列说明: 私密为 1，公开为 0

### 用户收藏夹表

|    列名     | 数据类型 |       说明        | 默认值 |
| :---------: | :------: | :---------------: | :----: |
| `favListID` |   int    | 被点赞的收藏夹 ID |        |
|    `uID`    |  int(9)  | 给予点赞的用户 ID |        |
|  `status`   | tinyint  |     点赞状态      |   0    |
