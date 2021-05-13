# Bili_API

仿Bilibili后台仓库

## 项目人员分配

* 徐文建：
    - 收藏模块 userFavoriteList
    - 视频模块 Video
* 赵霖：
    - 用户模块 Member
    - 动态模块 Dynamic
    - 分区模块 Zoning
* 王子轩：
    - 视频观看记录模块 watch-history
    - 用户消息模块 Message

---

## api文档

* ### 视频模块 Video 
	1. #### 查看子分区下视频  /videobvldZoning  
        参数：

        * bvChildZoning 子分区id Integer  
         
        返回值：根据子分区id(bvChildZoning)查询视频，返回list   
        * code：0   
        * message：0   
        * data: list集合   
            - bvID：bv号   
            - uID ：视频作者ID
            - bvCoverImgPath： 视频封面图
            - bvVideoPath： 视频文件路径
            - bvTitle： 视频标题
            - bvDesc： 视频简介
            - bvPostTime： 创建时间
            - bvChildZoning： 视频子分区ID
            - bvIsDel： 是否已删除，0为未删除，1为已删除
            
    2. #### 根据视频id查看视频信息 /videobvID   
        参数:  

        * bvID 视频id Integer   
        
        返回值：根据视频id(bvID)查询视频信息，返回对象   
        * code：0
        * message：0
        * data: video对象
            - bvID： bv号
            - type : 类型
            - uID： 视频作者ID
            - bvCoverImgPath： 视频封面图
            - bvVideoPath： 视频文件路径
            - bvTitle： 视频标题
            - bvDesc： 视频简介
            - bvPostTime： 创建时间
            - bvChildZoning： 视频子分区ID
            - bvIsDel： 是否已删除，0为未删除，1为已删除
            
        依赖：
        * type &gt; [`Zoning/elementby`](#zong通过组件id返回组件名称-elementby)
            
    3. #### 查看作者下面发布的视频 /videouID  
        参数:  
        
        * uID 作者id Integer   
             
        返回值：根据作者id(uID)查询作者下面视频，返回list   
        * code：0
        * message：0
        * data: list集合
            - bvID： bv号
            - uID： 视频作者ID
            - bvCoverImgPath： 视频封面图
            - bvVideoPath： 视频文件路径
            - bvTitle： 视频标题
            - bvDesc： 视频简介
            - bvPostTime： 创建时间
            - bvChildZoning： 视频子分区ID
            - bvIsDel： 是否已删除，0为未删除，1为已删除
            
    4. #### 查询所有视频的基础信息  /videolist   
        返回值：查询视频基础信息表所有数据，返回list   
        * code：0
        * message：0
        * data: list集合
            - bvID： bv号
            - uID： 视频作者ID
            - bvCoverImgPath： 视频封面图
            - bvVideoPath： 视频文件路径
            - bvTitle： 视频标题
            - bvDesc： 视频简介
            - bvPostTime： 创建时间
            - bvChildZoning： 视频子分区ID
            - bvIsDel： 是否已删除，0为未删除，1为已删除
            
    5. #### 根据视频id查看下面的评论 /videocommbvid   
        参数:   
        
        * bvid 视频id Integer   
                     
        返回值：根据视频id(bvid)查找所属视频的评论id(cID) 返回lsit   
        * code：0
        * message：0
        * data : list集合
            - cID：评论集合
                - bvID：视频id
                - ID: 评论id
                
    6. #### 查询视频评论表所有数据  /videocommlist      
        返回值： 返回lsit 查询所有数据   
        * code：0
        * message：0
        * data : list集合
            - bvID：视频ID
            - cID：评论ID

    7. #### 查看视频的基础信息(播放数弹幕等) /videodatabvID   
        参数:   
        
        * bvID 视频id Integer   
                         
        返回值：根据视频id(bvID)查询该视频的基本数据 返回对象  
        * code：0
        * message：0
        * data: 对象
            - bvID :	视频对应BV号
            - bvPlayNum : 视频播放数
            - bvPopupsNum : 视频弹幕数
            - bvLikeNum : 视频顶数
            - bvCoinNum : 视频硬币数
            - bvFavoriteNum : 视频收藏数
            - bvRetweetNum : 视频转发数
            - bvCommentNum : 视频评论数
            
    8. #### 查询视频数据表所有数据  /videodatalist   
        返回值：查询视频数据表所有信息 返回list   
        * code：0
        * message：0
        * data: list集合
            - bvID :	视频对应BV号
            - bvPlayNum : 视频播放数
            - bvPopupsNum : 视频弹幕数
            - bvLikeNum : 视频顶数
            - bvCoinNum : 视频硬币数
            - bvFavoriteNum : 视频收藏数
            - bvRetweetNum : 视频转发数
            - bvCommentNum : 视频评论数

    9. #### 查询被收藏的视频在那个收藏夹下  /videofavbvID   
        参数:   
        
        * bvID 被收藏的视频id Integer   
                 
        返回值：根据被收藏的视频id(bvID)查询该视频被收藏在那个收藏夹下(favListID) 返回对象   
        * code：0
        * message：0
        * data: 对象
         - bvID： 被收藏的视频ID
         - favListID： 视频收藏夹ID
         - favTime： 收藏时间，默认为当前时间，不支持设置系统时间
         
    10. #### 查询视频收藏表所有数据 /videofavlist   
        返回值：查询视频收藏表所有数据 返回list   
        * code：0
        * message：0
        * data: list集合
         - bvID： 被收藏的视频ID
         - favListID： 视频收藏夹ID
         - favTime： 收藏时间，默认为当前时间，不支持设置系统时间

    11. #### 查看该视频的视频评分 /videoratbvID  
        参数:   
        * bvID 视频id Integer   
                  
        返回值：根据视频id(bvID)查询视频评分 返回对象   
        * code：0
        * message：0
        * data: 对象
         - bvID： 视频ID
         - OverallRating： 视频综合评分，评分规则详见数据库说明书。
         
    12. #### 查看所有的视频评分 /videoratlist   
        返回值：查询视频评分表所有数据 返回lsit      
        * code：0
        * message：0
        * data: list集合对象
         - bvID： 视频ID
         - OverallRating： 视频综合评分，评分规则详见数据库说明书。

    13. #### 查看该视频下面的点赞用户 /videolikebvid   
        参数:   
        
        * bvid 视频id Integer   
              
        返回值：根据视频id(bvid)查询视频下面的点赞用户id 返回集合   
        * code：0
        * message：0
        * data: list集合
         - bvID： 视频ID
         - uID： 点赞的用户ID
         - status： 点赞状态，0为未作任何操作，1为点赞，默认为0
         - createTime： 点赞时间
         
    14. #### 查询视频点赞表所有数据 /videolikelist   
        返回值：查询视频点赞表所有数据 返回集合   
        * code：0
        * message：0
        * data: list集合
         - bvID： 视频ID
         - uID： 点赞的用户ID
         - status： 点赞状态，0为未作任何操作，1为点赞，默认为0
         - createTime： 点赞时间
    
    15. #### 根据子分区集合查询视频数据并分页处理 /videopage   
        参数:  
        
         * bvChildZoninglist： 子分区集合 Integer[] list
         * pageSize： 从第几页开始
         * pagecount： 一页显示多少条
        
        返回值 ： 查询的数据返回list集合 list   
        * code：0   
        * message：0   
        * data: list集合   
            - aid：bvid   
            - pic ：视频封面图
            - title： 标题
            - owner： 用户对象
                - uID : 用户id
                - userName : 用户名
            - stat： 视频数据对象
            - duration： 视频时长
            - bvid： bv号
            
        依赖 ：
        * owner &gt; [`Member/useruid`](#通过id返回user表所有对应id信息-useruid)
    
    16. #### 根据子分区查找的视频总数 /videocount   
        参数：
        
        * bvChildZoninglist： 子分区集合  Integer[] list
        
        返回值：返回根据子分区查找的视频总数 返回一个int
        * code：0   
        * message：0  
        * data: 返回一个int
            - count : 返回的总数 int类型

    17. #### 根据子分区id查询视频信息集合 /videodeorating   
        参数:
        
        * bvChildZoninglist : 子分区id	int
        * datetime : 时间
        
        返回值 ： 查询视频信息 返回集合
        * data:
            - aid : bvid
            - bvid : bv号
            - pic ：封面
            - title ：标题
            - typename ： 分区名称	
            - pts ：视频评分
         
        依赖：
        * typename &gt; [`Zoning/elementby`](#zong通过组件id返回组件名称-elementby)

    18. #### 根据时间查找子分区id和子分区id的总数	/videoseldate   
        参数：
        
        * str ： 时间类型
        
        返回值 : 根据时间查找子分区id和子分区id的总数 返回list
        * code:0
        * message : 0
        * data : 
            - countbv : 总数
            - bvChild : 子分区id

    19. #### 根据视频id数组查询视频评论表数据	/viderbvidcommlist   
         参数 ：
        
           * array : 视频id数组 int[]
           
         返回值 ： 返回list 集合
         * data :  list
            - bvid ： 视频id int
            - cid : 评论id

    20. #### 根据视频id数组查询视频评论id 返回list /videocommmarr   
        参数 ：
        
          * array : 视频id数组 int[]
          
        返回值 ： 返回list 集合
        * data :  list
            - cid : 评论id

    21. #### 根据bvid视频id去查找评论id	/videocommbvid   
        参数 ： 
        
           * bvid : 视频id int
           
        返回值 ： 返回list
        * data : 
            - cid : 评论id

    22. #### 根据视频id数组查询视频数据 /videoBvidlist   
        参数 ： 
        
           * bvidlist 视频id数组 int[]
           
        返回值 ： 返回一个list 集合
        * data :
            - title : 标题 string
            - long_title: null 空
            - bvid ： 视频id int
            - cover : 视频封面 
            - uid ： 作者id int
            - uri : 视频路径 
            - duration ： 视频总时长 int

    23. #### 通过bvid查询对象 /videobvidlists   
        参数 ： 
        
           * bvid ： 视频id int
           
        返回值 ： 返回对象 object
        * data :  object
            - bvID：bv号   
            - uID ：视频作者ID
            - bvCoverImgPath： 视频封面图
            - bvVideoPath： 视频文件路径
            - bvTitle： 视频标题
            - bvDesc： 视频简介
            - bvPostTime： 创建时间
            - bvChildZoning： 视频子分区ID
            - bvIsDel： 是否已删除，0为未删除，1为已删除

    24. #### 根据视频id数组拿到用户下所有的点赞了的视频 /videolikeuid   
        参数 ： 
        
          * array : 视频id数组 int[]
          
        返回值 ： 返回一个list 集合
        * data : list
            - bvID : 视频id int
            - uID : 用户id int
            - status : 点赞状态，0为未作任何操作，1为点赞 默认为0
            - createTime :点赞时间

    25. #### 根据视频id批量查询视频数据 /videobvidli  
        参数： 

         * array : 视频id数组 Integer[]

        返回值 ： 返回一个list
        * data: 
            - bvID：bv号  
            - uID ：视频作者ID
            - bvCoverImgPath： 视频封面图
            - bvVideoPath： 视频文件路径
            - bvTitle： 视频标题
            - bvDesc： 视频简介
            - bvPostTime： 创建时间
            - bvChildZoning： 视频子分区ID
            - bvIsDel： 是否已删除，0为未删除，1为已删除
            - duration : 时长

    26. #### 根据子分区id查询最新的4条数据 /videoridlists    
        参数 ：
        
          * rid ： 子分区id int
          * pn : 页码 int
          * ps : 每页显示数量 int
          
        返回值 ： 返回一个list
        * data : 
            - aid 视频id int
            - bvid 视频id string
            - ctime 发表时间 string
            - desc 视频文章 string
            - duration 视频时长 int  
            - owner 作者信息 object
                - mid 作者id int
                - name 姓名 string
                - face 头像 string 
            - pic 预览图 string
            - title 视频标题 string
            - tname 视频分区 string
            - stat 视频数据 object 
                - aid 视频id int
                - coin 投币数 int
                - favorite 收藏数 int
                - like 点赞数 int
                - dislike 点踩数 int
                - reply 评论数 int
                - share 转发数 int
                - view 观看数 int
                - his_rank 排名 int
                
        依赖 ：
        * owner &gt; [`Member/ownerinfo`](#查询简略用户信息-ownerinfo)
        * tname &gt; [`Zoning/elementby`](#zong通过组件id返回组件名称-elementby)

    27. #### 根据子分区id查询最新的数据并分页处理 按时间排序 /selectlistvieopage   
        参数 ：
        
          * rid ： 子分区id int
          * pn : 页码 int
          * ps : 每页显示数量 int
          
        返回值： 返回一个list
        * data : 
            - aid 视频id int
            - bvid 视频id string 
            - ctime 发布时间 string
            - desc 视频文章 string
            - duration 视频时长 int  
            - owner 作者信息 object
                - face 头像 string
                - mid 作者id int
                - name 作者名称 string
            - pic 视频预览图 string
            - stat 数据 object
                - aid 视频id int
                - coin 投币数 int
                - favorite 收藏数 int
                - like 点赞数 int
                - dislike 点踩数 int
                - reply 评论数 int
                - share 转发数 int
                - view 观看数 int
                - his_rank 排名 int
            - title 视频标题 string 
            - tname 视频分区 string
            
        依赖：
        * owner &gt; [`Member/ownerinfo`](#查询简略用户信息-ownerinfo)
        * tname &gt; [`Zoning/elementby`](#zong通过组件id返回组件名称-elementby)

    28. #### 根据子分区id按视频热度查询 /selectbvidlistpagerid   
        参数 ：
        
          * rid ： 子分区id int
          * pn : 页码 int
          * ps : 每页显示数量 int
          
        返回值 ： 返回一个list
        * data :
            - result : 对象集合
                - author 作者 strirng
                - bvid 视频id int
                - description 视频文章 string
                - duration 视频时长 int
                - favorites 收藏数 int
                - mid 作者id int
                - pic 预览图 string
                - play 观看数 int
                - pubdate 视频发表时间 string
                - review 评论数 int
                - title 视频标题 string
                - type 视频类型 string 
        依赖：
        * author &gt; [`Member/ownerinfo`](#查询简略用户信息-ownerinfo)
        * type &gt; [`Zoning/elementby`](#zong通过组件id返回组件名称-elementby)
        
    29. #### 根据子分区id按热度查询视频排行榜前10个 /selectbvidlistpagelist   
        参数 ： 
        
         * rid ： 子分区id int
         
        返回值： 返回一个list
        *data :
            - aid 视频id int  
            - author 作者名称 string  
            - bvid 视频id string  
            - coins 投币数 int  
            - create 发布时间 string  
            - description 视频文章 string  
            - duration 视频时长 string  
            - favorites 收藏数 int  
            - mid 作者id int  
            - pic 预览图 string  
            - play 播放数 int  
            - pts 综合评分 int  
            - review 评论数 int  
            - title 视频标题 string  
            - typename 视频分区 string 
         
        依赖：
        * author &gt; [`Member/ownerinfo`](#查询简略用户信息-ownerinfo)
        * typename &gt; [`Zoning/elementby`](#zong通过组件id返回组件名称-elementby)

    30. #### 根据子分区id查询视频总数 /selectidcoutn   
        参数 ：
        
          * rid ： 子分区id int
          
        返回值 ： 返回一个Integer总数
        * data : 
            - count : 总数 int

    31. #### 视频显示 /info
        参数：
        bvid bvid int   
        返回值：
        * videoData 视频显示对象 object 
            - aid 视频id int
            - video 视频对象 object
                - id 视频id int
                - title 标题 string
                - desc 副标题 string
                - ctime 发布时间 Date
                - rank 排名 int
            - owner up主对象id
                - mid up主id int
                - upname up主名字 String
                - fans 关注数 int
                - attention 是否关注 boolean
                - sign 个人简历 String
                - face 头像图片  String
                -
            - stat 对象集合
                - view 播放量 int
                - favorite 收藏数 int
                - coin 投币数 int
                - like 点赞数 int
            - related 视频推荐对象数组 &lt;object&gt;
                - aid 视频id int  
                - pic 图片 String
                - title 标题 String
                - owner up对象 object
                    - name up名字 String
                    - mid  upid int
                - stat 播放量对象 object
                    - view 播放量 in
            - mainpartition 主分区
                - id 主分区 int
                - name 分区名 string
            - deputydivision 副分区
                - id 主分区 int
                - name 分区名 string
        
        依赖 ：
        * owner &gt; video_info [`Member/videoinfo`](#据用户id查询对象-videoinfo)
        * related/owner &gt; [`Member/ownerinfo`](#查询简略用户信息-ownerinfo)
        * mainpartition &gt; [`Zoning/mainpartition`](#按子分区id查找对应父分区信息-mainpartition)
        * deputydivision &gt; [`Zoning/deputydivision`](#id查找对应子分区和父分区信息-deputydivision)
                
    32. #### 根据子分区id数组查询当天子分区下的视频总数 /videoridcountselec   
        参数 ：
        
         * rid  父分区id int
         * array  子分区id集合 int
         * datetime  时间 date
         
        返回值 ： 返回一个对象
        * data : 
            - countbv : 父分区id int
            - bvChild ： 子分区id集合下的视频总数量 int
        
* ### 用户消息模块 Message
    1. #### 收到的点赞 /like   
    参考链接：https://api.bilibili.com/x/msgfeed/like?csrf=4c7784a8355557a9595ccefc268e2f28&platform=web&build=0&mobi_app=web   
    返回值:
    * total 点赞集合 &lt;object&gt;
        - items 点赞数组 list&lt;object&gt;
            - id 个人id int
            - users 用户信息对象数组 list&lt;object&gt;  
              用户信息对象：
                - mid 点赞对象的id int
                - nickname 点赞对象的名字 String
                - avatar  对象头像img String
                - follow   是否关注 boolean
                - native_uri 个人中心的地址 string
            - item 被点赞的对象 object
                - item_id 被点赞的对象id int
                - type  video代表视频，dynamic代表动态 reply代表文字 String
                - title 视频标题,如果传text时，这里可传文字 String
                - desc 视频描述 String
                - `image` 视频封面图 String
                - uri   视频链接 String
                - ctime 点赞时间 String
                  -counts 此评论的总人数 int
                  -like_time 最新点赞的时间 Date
    
    依赖：
    * users &gt; [`赵霖漏了没写`](#)
    * items &gt; [`Dynamic/likesitems`](#收到的点赞-likesitems)
             
    2. #### 回复我的 /reply   
       参考链接：https://api.bilibili.com/x/msgfeed/reply?csrf=ed03730a1cd49540995b9fa002c1cf1e&build=0&mobi_app=web       
       返回值:
        - reply 回复集合数组 object
            - user 回复我的用户对象 object
                用户对象
                - mid 评论对象的id int
                - nickname 评论对象的名字 String
                - avatar  对象头像img String
                - follow   是否关注 boolean
            - item 回复我的评论对象 object  
                - source_content 被回复的评论内容 String
                - type ideo代表视频，dynamic代表动态 reply代表文字 String,
                - business  视频，动态，文字三个参数 string
                - title 被回复的评论或视频 string,
                - reply_time 当前时间 date
                - uri 当前评论，视频，动态的地址 string
                - image 当前 视频，动态的封面 string
                - native_uri 个人中心的地址 string
                
        依赖：
        * user &gt; [`Member/replyuserb`](#回复我的用户对象-replyuserb)
        * reply &gt; [`Member/data`](#回复我的对象-data)
         
    3. #### 我的消息 /get_sessions
       参考链接：https://api.vc.bilibili.com/session_svr/v1/session_svr/get_sessions?csrf=ed03730a1cd49540995b9fa002c1cf1e&session_type=1&group_fold=1&unfollow_fold=0&sort_rule=2&build=0&mobi_app=web
        * session_list 会话集合数组 list&lt;object&gt;
            - talker_id 对话id int
            - ack_seqno 此对话id的长度 int
            - last_msg 最新聊天对象 object
              聊天对象:
                - sender_uid 最新聊天数据的发送者,类似出轨的狼的id int
                - receiver_id 最新聊天数据的接收这者 int
                - content 最新聊天的内容 String
    
    4. #### 聊天记录 /fetch_session_msgs   
       参考链接：https://api.vc.bilibili.com/svr_sync/v1/svr_sync/fetch_session_msgs?csrf=ed03730a1cd49540995b9fa002c1cf1e&sender_device_id=1&talker_id=402923390&session_type=1&size=20&build=0&mobi_app=web   
       * messages 对象数组   
        - sender_uid 最后一条数据的发送者,类似出轨的狼的id int
        - receiver_id 最后一条数据的接受这者  int
        - content 传一条数据的  String
        - timestamp 发送这一条数据的时间 Date
        
     5. #### 根据发送信息的用户userID查询数据 /usermsguid   
        参数：
        
        * userID : 发送信息的用户ID int
        
        返回值 ： 返回一个集合 list
        * data :
            - umID : 消息表的主键，自增，无意义
            - userID ： 发送信息的用户ID
            - friendID ： 接收信息的用户ID
            - sender ： 留言发送者(用户名)
            - receiver ： 留言接收者(用户名)
            - updateTime ： 发送信息时间
            - content ： 留言内容
    
    6. #### 根据接收信息的用户friendID 查询数据 /usermsgsfid   
        参数：
        
        * friendID : 接收信息的用户ID int
        
        返回值 ： 返回一个集合 list
        * data :
            - umID : 消息表的主键，自增，无意义
            - userID ： 发送信息的用户ID
            - friendID ： 接收信息的用户ID
            - sender ： 留言发送者(用户名)
            - receiver ： 留言接收者(用户名)
            - updateTime ： 发送信息时间
            - content ： 留言内容
            
* ### 用户模块 Member
    1. 用户信息 /all-info  
       参考链接：https://api.bilibili.com/x/web-interface/nav  
       返回值：
        * isLogin 是否登陆 boolean 暂时固定值为：true
        * mid uid
        * uname 用户名 string
        * face 头像路径 string
        * level_info 等级信息 object
            - current_level 当前等级 int
            - current_min 当前等级所需最小经验 int
            - current_exp 当前经验值 int
            - next_exp 下等级需要的经验值 int
        * vip 大会员对象 object
            - type 会员类型 int 1大会员 0小会员(4月1愚人节那天为小会员)
            - status 是否是会员 int 1是 0否
            - due_date 会员有效时间 date
            - label string 固定返回"{"path": "", "text": "大会员", "label_theme": "vip", "text_color": "#FFFFFF", "bg_style":
              1, "bg_color": "#FB7299", "border_color": ""}"
            - avatar_subscript_url 固定返回 "http://i0.hdslb.com/bfs/vip/icon_Certification_big_member_22_3x.png"
        * money 硬币数 int
        * bcoin_balance b币余额 int
        * email_verified 是否绑定邮箱 boolean
        * mobile_verified 是否绑定手机 boolean

    2. 每日奖励 /exp/reward   
       参考链接：https://api.bilibili.com/x/member/web/exp/reward   
       返回值：
        * login 是否登录 boolean 暂时返回ture
        * watch 是否观看视频 boolean 暂时返回ture
        * coins 今日投币数 int 暂时返回5
        * share 是否分享过视频 boolean 暂时返回ture
        * email 是否绑定了邮箱 boolean
        * tel 是否绑定了手机号 boolean
    3. 大会员信息 /vip/info   
       参考链接：https://api.bilibili.com/x/vip/web/user/info?jsonp=jsonp   
       返回值：
        * vip_status 是否是会员 boolean
        * vip_type 会员类型 int 1大会员 0小会员
        * due_date 会员有效时间
    4. 账号基本信息 /account/info   
       参考链接：https://api.bilibili.com/x/member/web/account   
       返回值：
        * uname 昵称 string
        * userid 用户名 string 返回"bili_"+uid
        * birthday 出生年月 date
        * sex 性别 string
    5. 用户基本信息 /user/info   
       参考链接：https://passport.bilibili.com/web/site/user/info   
       返回值：
        * account_info
            - hide_tel 隐藏过的手机号 如："153*****270"
            - hide_mail 隐藏过的邮箱 如："155****@qq.com"
            - bind_tel 是否绑定手机号
            - bind_mail 是否绑定邮箱
    6. 查找账号对应的账号 /user/verify 
        参数：   
        * username 账号(手机号和邮箱) string
        * password 密码(md5加密) string
        返回值：   
        * succeed 是否成功 boolean
        * uid uid int
        * message 信息 string
    7. 大会员积分 /vip/point   
        参考链接：https://big.bilibili.com/web/point?jsonp=jsonp   
        返回值：
        * mid uid int
        * pointBalance 积分余额 int 
    8. 用户基本信息 /basic-info-by-uid   
        参数：   
        * mid uid int
        
        返回值：
        * mid uid int
        * name 名称 string
        * face 头像 string
        * sex 性别 string
        * fans 粉丝数 int
        * friend 关注数 int
        * level_info 等级对象 Object
            - current_level 当前等级 int
        * vip vip对象 Object
            - type 会员类型 1大会员  0小会员
            - status 是否是会员
        * following 当前登陆人是否关注查询的用户 boolean
    9. 当前登陆的用户基本信息 /card/info  
       参考链接：https://api.bilibili.com/x/web-interface/card?csrf=7f16078c913c8540075a3d85b52b36e0&mid=433899970&photo=true   
       返回值：
        * mid uid int
        * name 用户姓名 string
        * face 头像 string
        * fans 粉丝数 int
        * friend 关注数 int
    10. 查询指定uid的基本信息   /user/infos   
        参考链接：https://api.vc.bilibili.com/account/v1/user/infos?csrf=ed03730a1cd49540995b9fa002c1cf1e&uids=402923390,77023684,37090048,37090048,412135935,412135935,32708657,32708657,412466388,412466388,268990278,268990278,429301126,429301126,256246039,256246039,361471422,361471422,14328316,14328316,235555226,235555226,233121654,233121654,144900177,144900177,260556632,260556632,37390043,77023684,6139562,77023684,66025025,66025025268990278&build=0&mobi_app=web   
        参数：
        * uids uid集合 list&lt;int&gt;
        返回值：用户对象集合list&lt;object&gt;   
        用户对象：
        * mid uid int
        * uname 昵称 String
        * face 头像id String
    11. 查询UP主的顶导数字 /space/navnum
        参考链接：https://api.bilibili.com/x/space/navnum?mid=26119890&jsonp=jsonp&callback=__jp5   
        参数：
        * mid uid int
        * callback 返回方式 暂定jp5(传入值为__jp5)   
        
        返回值：
        * video 投稿数
        * favourite 收藏夹对象(有主人视角和客人视角的原因是有私密的文件夹, 不过因为本项目并无此功能,所以二者均为当前用户所以收藏夹内视频总数)
            - master 主人视角
            - guest 客人视角
    
    12. ####  通过用户id查看对应id的粉丝id /relations
        参数: uid 用户本人id
        返回值:
        Relations 对象集合
        *uID 用户本人 id
        *followUID 粉丝id
        
    13. #### 通过粉丝id查看对应关注的up主id /intuid
        参数: followUID 粉丝id
        返回值:
        Relations 对象集合
        *uID 用户本人 id
        *followUID 粉丝id
    
    14. #### 通过id返回User表所有对应id信息 /useruid
        参数: uid 用户id
        返回值:
        User对象 返回整个User对象
        
    15. #### 通过用户id 获得用户如下信息 /membe
        参数: uID 用户id
        返回值:
        *mid 用户唯一id
        *uname 用户昵称
        *sex 性别
        *face 头像
        *current_lecel 等级对象
            -level_info 用户等级
        *vip vip对象
            -status 是否拥有vip

    16. #### 回复我的用户对象List /replyuser
        参数:
        uid 数组回复人
        id 原评论
        返回值:
        对象集合
        *mid 点赞对象的id
        *nickname 昵称
        *avatar 头像
        *follow 是否关注
        *native_uri "个人中心地址"
        
    17. #### 回复我的用户对象 /replyuserb
        参数:
        uid 回复人
        id 原评论
        返回值:
        对象
        *mid 点赞对象的id
        *nickname 昵称
        *avatar 头像
        *follow 是否关注
        *native_uri "个人中心地址"
        
    18. #### 回复我的对象 /data
        参数: id 用户id
        返回值:
      - user 回复我的用户对象 object
          - mid 评论对象的id int
          - nickname 评论对象的名字 String
          - avatar  对象头像img String
          - follow   是否关注 boolean
      - item 回复我的评论对象 object  
          - source_content 被回复的评论内容 String
          - type ideo代表视频，dynamic代表动态 reply代表文字 String,
          - business  视频，动态，文字三个参数 string
          - title 被回复的评论或视频 string,
          - reply_time 当前时间 date
          - uri 当前评论，视频，动态的地址 string
          - image 当前 视频，动态的封面 string
          - native_uri 个人中心的地址 string
    
    19. #### 查询简略用户信息 /ownerinfo
        参数:
        uID 用户id
        返回值:
        *mid 用户id
        *name 用户名
        *face 头像地址
        
    20. #### 通过用户id查询对应大会员信息 /Vip
        参数: uID 用户id
        返回值:
        vip对象数据
        
    


* ### 动态模块 Dynamic
    1. #### 动态的最新信息 /entrance  
       参考链接：https://api.bilibili.com/x/web-interface/dynamic/entrance?video_offset=0&article_offset=0&alltype_offset=0   
       返回值：
        * entrance 最新动态的up主对象
            - icon 头像路径 string
            - mid uid int
            - type 类型 string 暂时固定为："up" 无为："none"    
              枚举类型如下:
                * none-无红点;
                * live-直播维度的更新提醒 展示 头像+红点;
                * up-up主维度的更新提醒 展示 头像+红点;
                * dyn-动态维度的图标提醒 展示 头像+红点;
                * dot-动态维度的红点提醒 展示 红点

        依赖：
        * entrance &gt; [`dynamic /bang`](#通过用户id查看user头像id等-bang)   

    2. #### 查询指定多个uid的动态数 /dynamic_num
        参考链接：https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_num_ex?csrf=7f16078c913c8540075a3d85b52b36e0&uids=433899970   
        参数：
        * uids uid集合
        
        返回值：
        * items 对应多个uid的动态数 list&lt;object&gt;  
        动态数对象：
            - uid uid int
            - num 动态数 int
      
    3. #### 最新的20条动态信息 /dynamic_new 
        参考链接：https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/dynamic_new?csrf=4c7784a8355557a9595ccefc268e2f28&uid=26119890&type_list=268435455&from=weball&platform=web   
        返回值：
        * cards 动态信息对象集合 list&lt;object&gt;   
        动态信息对象：
            - desc 基础信息对象 object
                - uid 发布人uid int 
                - type 动态类型 int 1为直播 2为文本 3为番剧 暂时返回2
                - comment 评论数量 int
                - like 点赞数量 int
                - is_liked 是否点赞 int 0否 1是
                - timestamp 发表时间
                - dynamic_id 动态id int
                - user_profile 用户信息 object
                    - info 用户基本信息 object
                        - uid 用户id int
                        - uname 用户姓名 string
                        - face 用户头像 string
                    - vip 用户会员 object
                        - status 是否是会员 Boolean
                        - type 会员类型 int
                        - due_date 会员有效时间 date
                    - level_info 等级对象 object
                        - current_level 当前等级 int
            - card 内容 string
    4. #### 根据偏移动态id获取后面20条动态信息 /dynamic_history
        参考链接：https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/dynamic_history?csrf=4c7784a8355557a9595ccefc268e2f28&uid=26119890&offset_dynamic_id=516351783052848953&type=268435455&from=weball&platform=web
        参数：
        * offset_dynamic_id 偏移动态id
        
        返回值：
        * cards 动态卡片对象集合 list&lt;object&gt;   
          动态信息对象：
            - desc 基础信息对象 object
                - uid 发布人uid int
                - type 动态类型 int 1为直播 2为文本 3为番剧 暂时返回2
                - comment 评论数量 int
                - like 点赞数量 int
                - is_liked 是否点赞 int 0否 1是
                - timestamp 发表时间
                - dynamic_id 动态id int
                - user_profile 用户信息 object
                    - info 用户基本信息 object
                        - uid 用户id int
                        - uname 用户姓名 string
                        - face 用户头像 string
                    - vip 用户会员 object
                        - status 是否是会员 Boolean
                        - type 会员类型 int
                        - due_date 会员有效时间 date
                    - level_info 等级对象 object
                        - current_level 当前等级 int
            - card 内容 string
    5. #### 动态详细信息 /dynamic_detail  
       https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/get_dynamic_detail?csrf=4c7784a8355557a9595ccefc268e2f28&dynamic_id=517512923173368485   
        参数：  
        * dynamic_id 动态id
        返回：
        * card 动态卡片对象集合 object
            - desc 基础信息对象 object
                - uid 发布人uid int
                - type 动态类型 int 1为直播 2为文本 3为番剧 暂时返回2
                - comment 评论数量 int
                - like 点赞数量 int
                - is_liked 是否点赞 int 0否 1是
                - timestamp 发表时间
                - dynamic_id 动态id int
                - user_profile 用户信息 object
                    - info 用户基本信息 object
                        - uid 用户id int
                        - uname 用户姓名 string
                        - face 用户头像 string
                    - vip 用户会员 object
                        - status 是否是会员 Boolean
                        - type 会员类型 int
                        - due_date 会员有效时间 date
                    - level_info 等级对象 object
                        - current_level 当前等级 int
            - card 内容 string
    6. #### 动态简略评论信息 /reply   
        参数：
        * dynamic_id 动态id
        * sort 排序方法 0为热度 1为时间
        
        返回值：  
        - page 评论分页 object
            - acount 总评论（父评论+子评论） int
            - count 评论数 int 
            - num 当前页码 int
            - size 每页评论数 int
        - replies 评论对象集合 object
            - rpid 评论id int
            - action 状态 0为无状态 2为踩了他
            - content 评论内容对象 object
                - message 评论内容
            - count 子评论数量 int
            - ctime 评论时间 date
            - like 点赞数 int  
            - member 发表评论人对象 object
                - mid 用户id int
                - sex 性别 string
                - uname 用户名称 string
                - avatar 用户头像 string  
                - level_info 用户等级对象 object
                    - current_level 用户等级 int
                - vip 会员对象 object
                    - status 是否是会员 Boolean
            - replies 子评论对象集合 object （仅三条）
                - content 评论回复对象 object 
                    - message 回复内容 string 
                - ctime 回复评论时间 date
                - like 点赞数量 int
                - member 回复评论人对象 object
                    - mid 用户id int 
                    - sex 用户性别 string
                    - uname 用户姓名 string 
                    - avatar 用户头像 string 
                    - level_info 回复评论人等级对象 object
                        - current_level 用户等级 int
                    - vip 回复评论人会员对象 object
                        - status 是否是会员 Boolean
            
          依赖：
        * replies &gt; [`comment/commselectcarrlist`](#根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态-commselectcarrlist)
    
    7. #### 动态详细评论信息 /reply/main
        参数：
        * dynamic_id 动态id
        * next 页码 int
        * mode 查询模式 1为热度 2为时间
        
        返回值：
        - replies 评论对象集合 object
            - action 状态 0为无状态 2为踩了他
            - content 评论内容对象 object
                - message 评论内容
            - rcount 子评论数量 int
            - ctime 评论时间 date
            - like 点赞数 int  
            - member 发表评论人对象 object
                - mid 用户id int
                - sex 性别 string
                - uname 用户名称 string
                - avatar 用户头像 string
                - level_info 用户等级对象 object
                    - current_level 用户等级 int
                - vip 会员对象 object
                    - status 是否是会员 Boolean
            - replies 子评论对象集合 object
                - content 评论回复对象 object （仅三条）
                    - message 回复内容 string
                - ctime 回复评论时间 date
                - like 点赞数量 int
                - member 回复评论人对象 object
                    - mid 用户id int
                    - sex 用户性别 string
                    - uname 用户姓名 string
                    - avatar 用户头像 string
                    - level_info 回复评论人等级对象 object
                        - current_level 用户等级 int
                    - vip 回复评论人会员对象 object
                        - status 是否是会员 Boolean
    8. #### 通过用户id查看user头像id等 /bang
       参数 
       *id 用户id
       返回值：
       *entrance 对象 object
            -icon 头像
            -mid 唯一id
            -type "up"

  9. #### 收到的点赞 /likesitems
        参数:
        * id 动态id
        返回值
        * id 个人id int
        * users 用户信息对象数组
            - mid 点赞对象的id int
            - nickname 点赞对象的名字 String
            - avatar  对象头像img String
            - follow   是否关注 boolean
            - native_uri 个人中心的地址 string
        * item 被点赞的对象 object
            - item_id 被点赞的对象id int
            - type  video代表视频，dynamic代表动态 reply代表文字 String
            - title 视频标题,如果传text时，这里可传文字 String
            - desc 视频描述 String
            - image 视频封面图 String
            - uri   视频链接 String
            - ctime 点赞时间 String
        *counts 此评论的总人数 int
        *like_time 最新点赞的时间 Date
              
    10. #### dynamicComment表所有信息 /dynamicComment
        返回值:
        *dynamicComment 对象
            -udID 动态id
            -cID 评论id
        
    11. #### 发表评论人对象 /memberid
        参数:
        id 用户id
        返回值:
        *mid 用户id
        *sex 性别
        *uname 昵称
        *avatar 头像
        *level_info 对象
            -current_level 用户等级
        *vip 对象
            -status 是否是vip
        
    12. #### 通过动态idIDdynamicdata表所有信息点赞数转发数评论数 /dynamicdata
        参数:
        id 动态id
        返回值:
        *udLikeNum 动态点赞总数
        *udRetweetNum 动态转发数
        *udCommentNum 动态评论数
        
    13. #### 通过动态udID查看点赞的用户以及点赞时间等/dynamiclike
        参数:
        id 动态id
        返回值:
        *dynamiclike 对象集合
            -udID  动态id
            -uID 点赞的用户id
            -status 点赞状态
            -createTime 点赞时间
        
    14.  #### 获取对应用户uid的所有动态 /userdynamicList
        参数:
        uid 用户id
        返回:
        *userdynamicsList 对象集合
            -udID 单条动态id
            -uID 用户id
            -content	动态正文
            -updateTime 发表动态的时间
            -isDel 是否删除
    
    15. #### 通过动态udID查看该动态信息 /userdynamic
        参数:
        udID 动态id
        返回:
        *userdynamic: 对象
            -udID 单条动态id
            -uID 用户id
            -content 动态正文
            -updateTime 发表动态的时间
            -isDel 是否删除

* ### 分区模块 Zoning
    1. #### 全部分区当日新投稿数量 /online/all   
       参考链接：http://api.bilibili.com/x/web-interface/online      
       返回值：
        * region_count 数量键值对 map 键为分区id 值为数量
    2. #### 分区楼层视频卡片数据 /dynamic   
       参考链接：http://api.bilibili.com/x/web-interface/dynamic/region?ps=12&rid=1   
       参数：
        * ps 数量
        * rid 分区id

       返回值：
        * page 分页对象 object
            - num 不知道 int 固定为1
            - size 分页大小 int
            - count 分页下标 int
        * archives 视频对象集合 list
            - aid bvid int
            - pic 封面图片 string
            - title 标题 string
            - owner 作者对象 object
                - mid uid int
                - name 作者名字 string
            - stat 详细信息对象
                - aid bvid int
                - coin 硬币数 int
                - view 播放量 int
                - like 点赞数 int
            - duration 时长 int 单位秒
            - bvid bv号（为"bv"+bvid）
    3. #### 指定分区排行榜（前12个）/ranking   
       参考链接：   
       https://api.bilibili.com/x/web-interface/ranking/region?rid=1&day=3&original=0   
       参数： 
        * rid 分区id   
        * day 几天之内的排行 (只需要返回此参数为3的)   
       返回值(视频信息对象集合list&lt;object&gt;)：  
       视频信息对象：
        * aid bvid
        * bvid bv号
        * pic 封面图片 string
        * title 标题 string
        * typename 子分区名 string
        * pts 评分 int
    4. #### 子分区最新动态 /dynamic/child
        参考链接：
       https://api.bilibili.com/x/web-interface/dynamic/region?jsonp=jsonp&pn=1&ps=5&rid=24&callback=jsonCallback_bili_5019176969914881  
       参数：  
        * rid 子分区id int  
        * pn 页码 int  
        * ps 每页数据 int
        
       返回值：  
        * page
            - count 总数 int
            - num 页码 int
            - size 每页条数  
        * archives 对象集合 object
            - aid 视频id int
            - bvid 视频id string
            - ctime 发表时间 string
            - desc 视频文章 string
            - duration 视频时长 int  
            - owner 作者信息 object
                - mid 作者id int
                - name 姓名 string
                - face 头像 string 
            - pic 预览图 string
            - title 视频标题 string
            - tname 视频分区 string
            - stat 视频数据 object 
                - aid 视频id int
                - coin 投币数 int
                - favorite 收藏数 int
                - like 点赞数 int
                - dislike 点踩数 int
                - reply 评论数 int
                - share 转发数 int
                - view 观看数 int
                - his_rank 排名 int
      
    5. #### 子分区视频按投稿时间排序（二十个） /cate/search
       参考链接：  
       https://s.search.bilibili.com/cate/search?main_ver=v3&search_type=video&view_type=hot_rank&order=click&copy_right=-1&cate_id=24&page=2&pagesize=20&jsonp=jsonp&time_from=20210422&time_to=20210429&callback=jsonCallback_bili_38590292299397416     
       参数：
        * rid 子分区id int
        * pn 页码数 int
        * ps 每页数据 int

       返回值： 对象集合 list&lt;object&gt;
        * page 分页对象
            - count 总条数 int
            - num 页码数 int
            - size 每页数据 int
        * archives 视频对象集合 object
            - aid 视频id int
            - bvid 视频id string 
            - ctime 发布时间 string
            - desc 视频文章 string
            - duration 视频时长 int  
            - owner 作者信息 object
                - face 头像 string
                - mid 作者id int
                - name 作者名称 string
            - pic 视频预览图 string
            - stat 数据 object
                - aid 视频id int
                - coin 投币数 int
                - favorite 收藏数 int
                - like 点赞数 int
                - dislike 点踩数 int
                - reply 评论数 int
                - share 转发数 int
                - view 观看数 int
                - his_rank 排名 int
            - title 视频标题 string 
            - tname 视频分区 string
    6. #### 子分区视频按视频热度排序（二十个） /newlist  
        参考链接：  
        https://api.bilibili.com/x/web-interface/newlist?rid=24&type=0&pn=1&ps=20&jsonp=jsonp&callback=jsonCallback_bili_480916365086748953   
        参数：
        * rid 子分区id int
        * pn 页码数 int  
        * ps 每页数据 int  
        返回值  对象集合 list&lt;object&gt;
        - numPages 总页数 int
        - numResults 总条数 int
        - page 页码 int
        - pagesize 每页数据 int
            - result 对象集合object
            - author 作者 strirng
            - bvid 视频id int
            - description 视频文章 string
            - duration 视频时长 int
            - favorites 收藏数 int
            - mid 作者id int
            - pic 预览图 string
            - play 观看数 int
            - pubdate 视频发表时间 string
            - review 评论数 int
            - title 视频标题 string
            - type 视频类型 string 
    
    7. #### 排行榜（十个） /ranking/child  
       参考链接：
       https://api.bilibili.com/x/web-interface/ranking/region?jsonp=jsonp&rid=210&day=7&original=0&callback=jsonCallback_bili_0849387551523184111  
       参数： 
        * rid 子分区id int
        
        返回值 对象集合 object  
        - aid 视频id int  
        - author 作者名称 string  
        - bvid 视频id string  
        - coins 投币数 int  
        - create 发布时间 string  
        - description 视频文章 string  
        - duration 视频时长 string  
        - favorites 收藏数 int  
        - mid 作者id int  
        - pic 预览图 string  
        - play 播放数 int  
        - pts 综合评分 int  
        - review 评论数 int  
        - title 视频标题 string  
        - typename 视频分区 string  
    
    8. #### 返回父分区 /zoning
       返回值:
         对象集合
       *zID 父分区id
       *zFatherID 为null
       *zName 父分区名
       
    9. #### 返回对应父分区的子组件 /zoning/id
        参数:
        id 父分区id
        返回值:
        对象集合
       *zID 子分区id
       *zFatherID 父分区id
       *zName 子分区名
       
    10. #### Zong通过组件id返回分区名称 /elementby
        参数:
        id 分区id
        返回值
        *String 分区名
        
    11. #### 按子分区id查找对应父分区信息 /mainpartition
        参数:
        bvChildZoning 子分区id
        返回值:
        父对象
        *id 父组件id
        *name 父组件名
        
    12. #### id查找对应子分区和父分区信息 /deputydivision
        参数:
        bvChildZoning 分区id
        返回值:
        对象
        id 组件id
        name 组件名


            
* ### 视频观看记录模块 Watch-History
    1. #### /cursor   
       参考链接：https://api.bilibili.com/x/web-interface/history/cursor   
       参数：
        * ps 数量

       返回值：
        * list 视频对象列表 list&lt;object&gt;   
          视频对象：
            - title 视频标题 string
            - long_title 分p标题 string 暂且固定返回""
            - cover 封面图片路径 string
            - author_name 作者名称 string
            - uri 视频链接 string 暂且固定返回""
            - history 历史对象 object
                - oid hid
                - business 类型 string 暂时"archive"
                - page p数 int 暂时1
            - duration 总时长 int 单位秒
            - progress 观看时长 int 观看完为-1
            - show_title pgc卡用 暂且为""
            - view_at 最后观看的时间 date

* ### 收藏模块 Favorite
    1. #### /folder    
       参考链接：https://api.bilibili.com/x/v3/fav/folder/list4navigate   
       返回值： 返回list 固定返回两个值 第一个值为普通收藏夹("我创建的收藏夹") 第二个为稍后再看
        * id id int 固定值：普通收藏夹为1，稍后再看为2
        * name 文件夹名 string 固定值：普通收藏夹为"我创建的收藏夹"，稍后再看为"稍后再看"
        * mediaListResponse 收藏夹对象 object
            - count 数量 int
            - list 收藏夹对象集合 list&lt;object&gt;  
              收藏夹对象：
                - id 收藏夹id int
                - mid uid int
                - title 收藏夹名 string
                - media_count 收藏夹内视频(媒体)数 int
                
        依赖：
        * media_count &gt; [`Video/videocount`](#根据子分区查找的视频总数-videocount)
                
    2. #### /resource    
       参考链接：https://api.bilibili.com/x/v3/fav/resource/list4navigate?platform=web   
       参数：
            * media_id 收藏夹id

       返回值：list
        * id 收藏夹id int
        * title 标题 string
        * cover 封面 string
        * page p数 暂时为1 int
        * type 类型 暂时为2 int  
          2: 'archive',  
          12: 'audio',  
          21: 'ugcSeason'
        * upper up主 object
            - mid uid int
            - name 名字 string
            - face 头像图片路径 string
        * bvid bv号 string
        * duration 时长 int
        
        依赖：
        * upper &gt; [`Member/ownerinfo`](#查询简略用户信息-ownerinfo)
        
* ### 前端接口 web-interface
    1. /nav/stat   
       返回值：
        * following 关注数
        * follower 粉丝数
        * dynamic_count 动态数
    2. 首页推荐位数据 /index/top/rcmd  
       参考链接 https://api.bilibili.com/x/web-interface/index/top/rcmd  
       返回值:
        * item 推荐视频数组 list&lt;object&gt;
            - id bvid int
            - bvid bv号 string
            - uri 链接 string
            - pic 封面图片 string
            - title 标题 string
            - duration 总时长 int 单位秒
            - owner 作者对象 object
                - mid uid int
                - name 作者名字 string
                - face 头像 string
            - stat 详细信息对象
                - view 播放量 int
* ### 安全模块 safety
    1. 手机号登陆 /login   
        参数：  
        * tel 手机号
        * code 验证码
    2. 账号密码登陆 /login   
        参数：  
        * username 用户名
        * password 密码
    3. 根据token获取uid /logon-uid (提供给内部接口用于接口调接口) (参数不适用HttpServletRequest或Cookie类的原因是考虑到接口掉接口会产生不必要的网络资源消耗)
        参数：
        * token token string
        返回值：uid int(data直接为uid)
* ### 评论模块 comment
    1. #### 评论回复信息 /commentlist   有点离谱我找不到这个
       参数：
       
        * rpid 评论id int
        
       返回值： 返回一个list
        * replies 评论对象集合 &lt;object&gt;
            - rpid 评论id int
            - member 用户对象 object
                - mid uid int
                - uname 姓名 string
                - sex 性别 string
                - face 头像 string
                - level_info 等级对象 object
                    - current_level 当前等级 int
                - vip vip对象 object
                    - status 是否是会员 boolean
            - like 评论点赞数 int
            - dislike 评论点踩数 int
            - ctime 评论时间 date
            - content 内容对象 object
                - message 评论内容 string 
    
    2.  #### 根据评论id查询数据 /commdatacid     
        参数：
        
         * cID 评论id int
            
        返回值： 返回对象
        * data :
            - cID : 评论id
            - cLikeNum : 点赞数量
            - cUnLikeNum ： 点踩数量

    3. #### 根据被点赞评论id查询评论点赞表的数据 /selectlikearr   
       参数：
          * arr : 被点赞的评论id数组	Integer[]
           
       返回值 ： 返回list list集合
        * data :
            - cid : 被点赞的评论ID int
            - uid : 点赞的用户ID int
            - status : 点赞状态，未做任何操作为0，点赞为1，点踩为2 默认为0   int
            - createTime: 点赞时间，默认为当前时间 Date

    4. #### 根据评论id数组查询评论总数 /commcidcount   
       参数 ：
       
        * arr ： 评论id数组 Integer[]
           
       返回值 ： Integer 
        * Integer : int

    5. #### 根据当前用户id查询用户下的评论id /commuidlist   
       参数 ：
       
         * uid : 用户id
         
       返回值： 返回lsit list集合
        * data : 
            - cid : 评论id 

    6. #### 根据点赞评论id数组查询评论id基本数据 /commcidarray   
       参数 ：
       
          * array : 点赞评论id数组	Integer[]
          
       返回值 ： 返回list 集合
        * data : 
            - cid : 评论id 	int
            - uid : 用户id	int
            - createTime : 评论时间 date
            - ctext : 评论正文 回复的评论

    7. #### 根据评论id查询评论数据 /commcidlistmap   
       参数 ： 
       
          * cid : 评论id
          
       返回值 ： 返回一个对象
        * data :
            - cid : 评论id 	int
            - uid : 用户id int
            - cidreply : 回复的id 0就为回复视频或动态
            - createTime ： 评论时间 date
            - cText : 评论正文 ： text
            - isDel : 是否删除 0 未删除，1已删除

    8. #### 根据用户uid查询用户下评论的数据cidreply！=0 则是评论的 /selecomuid   
       参数:
       
         * uid : 用户id	int
         
       返回值 ： 返回list 集合
        * data : 
            - cid : 评论id int
            - uid : 用户id int
            - cidreply : 回复的评论id int
            - ctext : 评论正文 text
            - createTime :评论时间 date
            - isDel : 是否删除 0未删除，1 已删除

    9. #### 根据评论cid主键查询评论数据 /selecomcid   
        参数 ：
        
         * cid : 评论id int
           
        返回值 ： 返回Object 
        * data : 
            - cid : 评论id int
            - uid : 用户id int
            - cidreply : 回复的评论id int
            - ctext : 评论正文 text
            - createTime :评论时间 date
            - isDel : 是否删除 0未删除，1 已删除

    10. #### 根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态 /commselectcarrlist   
         参数 ： 
         
           * id : 按id排序 1为热度排序 2 为时间排序 int   
           * array : 评论id数组 int[] 
                
         返回值 ： 返回一个list 集合
        * data : 
            - replies ： 评论对象集合 object
                - action : 状态 0为无状态 1为点赞了他 2为踩了他 
                - content : 评论内容对象 object
                    - message : 评论内容
                - count : 子评论数量 int
                - ctime : 评论时间 date
                - like : 点赞数 int
                - member ： 发表人评论人对象 object
                - replies ： 子评论对象集合 obejtct (仅前三条)
                    - content ： 评论回复对象 object
                        - message 回复内容 string
                    - ctime : 回复评论时间 date
                    - like : 点赞数量  int
                    - member : 回复评论人对象 obejct
                    
        依赖：
        * member &gt; [`Dynamic/memberid`](#发表评论人对象-memberid)
        * replies/member &gt; [`Dynamic/memberid`](#发表评论人对象-memberid)

    11. #### 根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态 id ：1 为热度排序  2 为时间排序 并分页处理 /commselectcarrlistpage   
         参数 ： 
         
           * id ： 1 为热度排序  2 为时间排序 int
           * array : 评论id数组 Integer[]
           * next : 页码
            
         返回值 ： 返回一个list 集合多个数据
        * data : 
            - replies ： 评论对象集合 object
                - action : 状态 0为无状态 1为点赞了他 2为踩了他 
                - content : 评论内容对象 object
                    - message : 评论内容
                - count : 子评论数量 int
                - ctime : 评论时间 date
                - like : 点赞数 int
                - member ： 发表人评论人对象 object
                - replies ： 子评论对象集合 obejtct (仅前三条)
                    - content ： 评论回复对象 object
                        - message 回复内容 string
                    - ctime : 回复评论时间 date
                    - like : 点赞数量  int
                    - member : 回复评论人对象 obejct
                    
        依赖：
        * member &gt; [`Dynamic/memberid`](#发表评论人对象-memberid)
        * replies/member &gt; [`Dynamic/memberid`](#发表评论人对象-memberid)
                
    12. #### 评论回复信息 /reply
        参数：  
        * rpid 评论id int
        * pn 页码 int
        * ps 每页总条数 int
        返回值：  
        * replies 评论对象集合 &lt;object&gt;
            - rpid 评论id int
            - page 分页对象 object
              - count 总数 int
              - num 页码 int
              - size 每页总条数 int
            - member 用户对象 object
                - mid uid int
                - uname 姓名 string
                - sex 性别 string
                - face 头像 string
                - level_info 等级对象 object
                    - current_level 当前等级 int
                - vip vip对象 object
                    - status 是否是会员 boolean
            - like 评论点赞数 int
            - dislike 评论点踩数 int
            - ctime 评论时间 date
            - content 内容对象 object
                - message 评论内容 int
        
        依赖：
        * member &gt; [`Member/membe`](#通过用户id-获得用户如下信息-membe)

* ### 图片资源模块 image-resource
    1. 上传图片 /upload   
    参数：
    * image 二进制图片
    * type 类型 如：头像 视频封面等 将作为图片保存的分组
    返回值：
    * filePath 文件路径(相对于资源服务器 没有使用绝对url的原因是不容易修改)
    * fileName 文件名
