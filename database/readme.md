# 数据库说明文档

> ### 注意事项
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

<<<<<<< HEAD
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
| `headImgPath` | varchar(255) |  用户头像路径  |

> 用户名和用户 ID 并不冲突，
> 在用户发送信息时两者起到相互制约的作用
> 用户名格式：bili_ 11位数字
>
> 性别说明：1 为男，2 为女，0 为私密
=======
|     列名     |  数据类型   |      说明      |
| :----------: | :---------: | :------------: |
|    `uID`     |   int(9)    |  用户唯一 ID   |
|  `userName`  | varchar(16) | 用户名（唯一） |
|  `Password`  | varchar(16) |    用户密码    |
|  `NickName`  | varchar(16) |    用户昵称    |
|  `Birthday`  |  timestamp  |    用户生日    |
| `BoundEmail` | varchar(50) |   被绑定邮箱   |
| `BoundPhone` | varchar(11) |  被绑定手机号  |
|  `BoundQQ`   | varchar(15) |   被绑定 qq    |
>>>>>>> parent of 033cb4f (修改了ER图中的若干逻辑错误，修改了readme)

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

> 为什么要去存储 <u>数量类型</u> 的数据？
>
> 从长远角度考虑，如若视频数据丢失或任何与这些数量的列关联的表项被删除，
> 历史的个人数据不会受影响
> 取出数据也会方便一些

### 用户关系表

|    列名     | 数据类型 |    说明     |
| :---------: | :------: | :---------: |
|    `uID`    |  int(9)  | 用户本人 ID |
| `followUID` |  int(9)  |   粉丝 ID   |

> 双主键保证数据不重复

### 用户大会员信息表

|        列名        | 数据类型  |        说明        |
| :----------------: | :-------: | :----------------: |
|       `bmID`       |    int    |     主键无意义     |
|       `uID`        |  int(9)   |      用户 ID       |
|  `ExpirationTime`  | timestamp |   大会员过期时间   |
| `LastBeBigMembers` | timestamp | 上次开通大会员日期 |
|   `MemberPoint`    |    int    |      会员积分      |

> 大会员数据主要通过修改来操作
> 每个用户对应一条大会员信息
>
> 确定大会员类型（年度、月度、季度）则通过时间进行计算

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
|    `bvTags`    | varchar(150) |              视频标签              |
|   `bvIsDel`    |   tinyint    | 是否已删除，0 为未删除，1 为已删除 |

> 标签列格式为[id,id,id,...]
>
> 视频分 P 如何存储没有定论，先搁置。

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

|      列名       |   数据类型   |     说明      |
| :-------------: | :----------: | :-----------: |
|     `bvID`      |   int(10)    |    视频 ID    |
| `OverallRating` | double(10,1) | 视频综合评分. |

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
> 本项目中所有排名均通过 Raiting 表中的分数计算进行排行，
> 可使用 redis 动态修改数据
> 四舍五入总评分再进行排名

### 视频点赞表

|     列名     | 数据类型  |     说明      |
| :----------: | :-------: | :-----------: |
|    `bvID`    |  int(10)  |    视频 ID    |
|    `uID`     |  int(9)   | 点赞的用户 ID |
|   `status`   |  tinyint  |   点赞状态    |
| `createTime` | timestamp |   点赞时间    |

> 点赞状态的区分：未做任何操作为 0,点赞为 1, 默认为 0。
>
> 点赞时间默认为当前时间

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

> 该表存在价值存疑
>
> 番剧为特殊视频，没有收藏选项

---

## 专栏类

### 文章基础信息表

|      列名      |   数据类型   |                说明                |
| :------------: | :----------: | :--------------------------------: |
|     `cvID`     |    int(8)    |       专栏文章 ID，主键自增        |
|     `uID`      |    int(9)    |          专栏文章作者 ID           |
| `cvCoverImgID` |     int      |    专栏标题图片 ID，关联图片表     |
|   `cvTitle`    | varchar(40)  |             专栏标题表             |
|  `cvPostTime`  |  timestamp   |          专栏文章上传时间          |
|    `cvTags`    | varchar(150) |              专栏标签              |
|    `cvText`    |   longtext   |              专栏正文              |
|   `cvIsDel`    |   tinyint    | 是否已删除，0 为未删除，1 为已删除 |

### 文章数据表

|      列名       | 数据类型 |       说明       |
| :-------------: | :------: | :--------------: |
|     `cvID`      |  int(8)  |     文章 ID      |
|   `cvReadNum`   |  bigint  |   文章总阅读数   |
|   `cvLikeNum`   |  bigint  |   文章总点赞数   |
|   `cvCoinNum`   |  bigint  | 文章总获得硬币数 |
| `cvFavoriteNum` |  bigint  |   文章总收藏数   |
|  `cvCommenNum`  |  bigint  |   文章总评论数   |
| `cvRetweetNum`  |  bigint  |   文章总转发数   |

### 文章综合评分表

|      列名       |   数据类型   |     说明     |
| :-------------: | :----------: | :----------: |
|     `cvID`      |    int(8)    |   文章 ID    |
| `OverallRating` | double(10,1) | 文章综合评分 |

### 文章收藏表

|   列名    | 数据类型  |     说明      |
| :-------: | :-------: | :-----------: |
|  `cvID`   |  int(8)   |    文章 ID    |
|   `uID`   |  int(9)   | 收藏的用户 ID |
| `favTime` | timestamp |   收藏时间    |

### 文章点赞表

|     列名     | 数据类型  |           说明           |
| :----------: | :-------: | :----------------------: |
|    `cvID`    |  int(8)   |       专栏文章 ID        |
|    `uID`     |  int(9)   |       点赞用户 ID        |
|   `status`   |  tinyint  |         点赞状态         |
| `createTime` | timestamp | 点赞时间，默认为当前时间 |

---

## 文件类

### 图片文件信息表

|   列名    |   数据类型   |    说明     |
| :-------: | :----------: | :---------: |
|  `imgID`  |     int      | 图片唯一 ID |
| `imgPath` | varchar(255) |  图片路径   |
| `imgName` | varchar(100) |  图片名称   |
| `imgType` |    int(1)    |  图片类型   |

> 图片类型的区分：1 为用户头像，2 为动态图像，3 为视频标题，4 为专栏标题，5 为其他
>
> 动态图片和其他图片 以路径的形式 统一存放在图片表中
>
> 在引用时可以使用 json 格式进行操作

### 视频文件信息表

|   列名   |   数据类型   |         说明          |
| :------: | :----------: | :-------------------: |
|  `vfID`  |     int      | 视频文件 ID，主键自增 |
| `vfPath` | varchar(255) |     视频文件路径      |
| `vfName` | varchar(200) |     视频文件名称      |

---

## 标签类

### 标签表

|   列名    |  数据类型   |    说明     |
| :-------: | :---------: | :---------: |
|  `TagID`  |     int     | 标签唯一 ID |
| `TagName` | varchar(50) |  标签名称   |

### 标签路径表

> 通过该表描述标签关系

|     列名     | 数据类型 |     说明     |
| :----------: | :------: | :----------: |
|    `tpID`    |   int    | 主键，无意义 |
|  `Ancestor`  |   int    |   祖先 id    |
| `Descendant` |   int    |   后代 id    |
|    `Dept`    |   int    |     深度     |

> 标签表的第一层，也就是树根层，代表分区（动漫区，音乐区...）
> 第二层细分，依此类推
>
> 至于排序，结合 tag 条件和分数高低进行无脑排序即可
>
> 如何使用 sql 具体操作，参见[mysql 文件](File://totorial.sql)

---

## 动态类

### 用户动态表

|     列名     | 数据类型  |         说明          |
| :----------: | :-------: | :-------------------: |
|    `udID`    |    int    | 用户动态 ID，主键自增 |
|    `uID`     |  int(9)   |        用户 ID        |
|  `content`   |   text    |       动态正文        |
| `updateTime` | timestamp |    发表动态的时间     |
|   `isDel`    |  tinyint  |   动态是否已经删除    |

### 用户动态数据表

|      列名      | 数据类型 |     说明     |
| :------------: | :------: | :----------: |
|     `udID`     |   int    |   动态 ID    |
|  `udLikeNum`   |  bigint  | 动态点赞总数 |
| `udRetweetNum` |  bigint  |  动态转发数  |
| `udCommentNum` |  bigint  |  动态评论数  |

### 用户动态点赞表

用于区分用户点赞

|     列名     | 数据类型  |      说明       |
| :----------: | :-------: | :-------------: |
|    `udID`    |    int    | 被点赞的动态 ID |
|    `uID`     |  int(9)   |   点赞用户 ID   |
|   `status`   |  tinyint  |    点赞状态     |
| `createTime` | timestamp |    点赞时间     |

---

## 评论类

### 用户评论表

|     列名     | 数据类型  |         说明          |
| :----------: | :-------: | :-------------------: |
|    `ucID`    |    int    | 用户评论 ID，主键自增 |
|    `uID`     |  int(9)   |   撰写评论的用户 ID   |
| `artworkID`  |  bigint   |     评论的作品 ID     |
|   `ucType`   |    int    |   用户评论对应区域    |
| `ucID_reply` |    int    |     回复的评论 ID     |
| `createTime` | timestamp |       评论时间        |
|   `ucText`   |   text    |       评论正文        |
|   `isDel`    |  tinyint  |      是否已删除       |

> artworkID 列说明：1 为视频，2 为专栏，3 为动态
>
> isDel 列说明：0 为未删除，1 为已删除
>
> > 使用一个评论表去统一存储三个表
> >
> > (视频表，专栏表，动态表)中的
> >
> > <u>不同数据类型和数据精度的 ID</u>
> >
> > 在此之前需要通过 Type 列的值判断评论或点赞的作品对应的表

### 用户评论数据表

|     列名      | 数据类型 |   说明   |
| :-----------: | :------: | :------: |
|    `ucID`     |   int    | 评论 ID  |
|  `ucLikeNum`  |  bigint  | 点赞数量 |
| `ucunLikeNum` |  bigint  | 点踩数量 |

### 用户评论点赞表

|     列名     | 数据类型  |      说明       |
| :----------: | :-------: | :-------------: |
|    `ucID`    |    int    | 被点赞的评论 ID |
|    `uID`     |  int(9)   |  点赞的用户 ID  |
|   `status`   |  int(1)   |    点赞状态     |
| `createTime` | timestamp |    点赞时间     |

---

## 用户其他类

### 用户观看历史表

<<<<<<< HEAD
|        列名        | 数据类型  |           说明           |
| :----------------: | :-------: | :----------------------: |
|       `uID`        |  int(9)   |         用户 ID          |
|       `cvID`       |  int(8)   |         文章 ID          |
|       `bvID`       |  int(10)  |         视频 ID          |
|    `CloseTime`     | timestamp |       关闭媒体时间       |
=======
|        列名        | 数据类型  |          说明          |
| :----------------: | :-------: | :--------------------: |
|       `uID`        |  int(9)   |        用户 ID         |
|       `cvID`       |  int(8)   |        文章 ID         |
|       `bvID`       |  int(10)  |        视频 ID         |
|    `CloseTime`     | timestamp |      关闭媒体时间      |
>>>>>>> parent of 033cb4f (修改了ER图中的若干逻辑错误，修改了readme)
| `TimelinePosition` |   time    | 视频关闭时，时间线的位置 |

> 视频历史记录方面，遇到的问题是视频记录的时间参数问题，

### 用户消息表

|     列名     |  数据类型   |        说明         |
| :----------: | :---------: | :-----------------: |
|    `umID`    |     int     | 主键自增 ID，无意义 |
|   `userID`   |   int(9)    |  发送信息的用户 ID  |
|  `friendID`  |   int(9)    |  接收信息的用户 ID  |
|   `sender`   | varchar(16) |     留言发送者      |
|  `receiver`  | varchar(16) |     留言接收者      |
| `updateTime` |  timestamp  |    发送信息时间     |
|  `content`   |    text     |      留言内容       |
|    `type`    |   tinyint   |      留言类型       |

> type 列说明：1 为普通消息、2 为系统消息
>
> 另见：消息表的数据库模型
>
> https://www.oschina.net/question/12_70252?sort=default&p=1

### 用户收藏夹表

|    列名     |   数据类型   |          说明           |
| :---------: | :----------: | :---------------------: |
| `favListID` |     int      | 视频收藏夹 ID，主键唯一 |
|    `uID`    |    int(9)    |   创建收藏夹的用户 ID   |
|   `name`    | varchar(20)  |  收藏夹名称，可以重复   |
| `isSecret`  |   tinyint    |    是否为私密收藏夹     |
| `tLikeNum`  |     int      |    收藏夹的总点赞数     |
|   `desc`    | varchar(200) |       收藏夹描述        |

> isSecret 列说明: 私密为 1，公开为 0
