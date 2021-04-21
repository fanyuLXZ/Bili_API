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

* ### 视频模块 Vide 
	1. 查看子分区下视频  /videobvldZoning  
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
		 	
	2. 根据视频id查看视频信息 /videobvID   
	    参数:  
    	* bvID 视频id Integer   
    	
		返回值：根据视频id(bvID)查询视频信息，返回对象   
		* code：0
		* message：0
		* data: video对象
		 	- bvID： bv号
		 	- uID： 视频作者ID
		 	- bvCoverImgPath： 视频封面图
		 	- bvVideoPath： 视频文件路径
		 	- bvTitle： 视频标题
		 	- bvDesc： 视频简介
		 	- bvPostTime： 创建时间
		 	- bvChildZoning： 视频子分区ID
		 	- bvIsDel： 是否已删除，0为未删除，1为已删除
		 	
	3. 查看作者下面发布的视频  /videouID  
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
		 	
	4. 查询所有视频的基础信息  /videolist   
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
		 	
	5. 根据视频id查看下面的评论    /videocommbvid   
	    参数:   
        * bvid 视频id Integer   
                	 
		返回值：根据视频id(bvid)查找所属视频的评论id(cID) 返回lsit   
		* code：0
		* message：0
		* data : list集合
		 	- cID：评论集合
		 		- bvID：视频id
		 		- ID: 评论id
		 		
	6. 查询视频评论表所有数据  /videocommlist      
		返回值： 返回lsit 查询所有数据   
		* code：0
		* message：0
		* data : list集合
			- bvID：视频ID
		 	- cID：评论ID

	7. 查看视频的基础信息(播放数弹幕等)    /videodatabvID   
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
			
	8. 查询视频数据表所有数据  /videodatalist   
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

	9. 查询被收藏的视频在那个收藏夹下  /videofavbvID   
	    参数:   
        * bvID 被收藏的视频id Integer   
                 
		返回值：根据被收藏的视频id(bvID)查询该视频被收藏在那个收藏夹下(favListID) 返回对象   
		* code：0
		* message：0
		* data: 对象
		 - bvID： 被收藏的视频ID
		 - favListID： 视频收藏夹ID
		 - favTime： 收藏时间，默认为当前时间，不支持设置系统时间
		 
	10. 查询视频收藏表所有数据 /videofavlist   
		返回值：查询视频收藏表所有数据 返回list   
		* code：0
		* message：0
		* data: list集合
		 - bvID： 被收藏的视频ID
		 - favListID： 视频收藏夹ID
		 - favTime： 收藏时间，默认为当前时间，不支持设置系统时间

	11. 查看该视频的视频评分  /videoratbvID  
	    参数:   
        * bvID 视频id Integer   
                  
		返回值：根据视频id(bvID)查询视频评分 返回对象   
		* code：0
		* message：0
		* data: 对象
		 - bvID： 视频ID
		 - OverallRating： 视频综合评分，评分规则详见数据库说明书。
		 
	12. 查看所有的视频评分   /videoratlist   
		返回值：查询视频评分表所有数据 返回lsit      
		* code：0
		* message：0
		* data: list集合对象
		 - bvID： 视频ID
		 - OverallRating： 视频综合评分，评分规则详见数据库说明书。

	13. 查看该视频下面的点赞用户    /videolikebvid   
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
		 
	14. 查询视频点赞表所有数据 /videolikelist   
		返回值：查询视频点赞表所有数据 返回集合   
		* code：0
		* message：0
		* data: list集合
		 - bvID： 视频ID
		 - uID： 点赞的用户ID
		 - status： 点赞状态，0为未作任何操作，1为点赞，默认为0
		 - createTime： 点赞时间


* ### 用户消息模块 Message

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
    7. 大会员积分 /vip/point   
        参考链接：https://big.bilibili.com/web/point?jsonp=jsonp   
        返回值：
        * mid uid int
        * pointBalance 积分余额 int 
* ### 动态模块 Dynamic
    1. 动态的最新信息 /entrance  
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
* ### 分区模块 Zoning
    1. 全部分区当日新投稿数量 /online/all   
       参考链接：http://api.bilibili.com/x/web-interface/online      
       返回值：
        * region_count 数量键值对 map 键为分区id 值为数量
    2. 分区楼层视频卡片数据 /region/dynamic   
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
            - duration 时长 date
            - bvid bv号（为"bv"+bvid）
    3. 指定分区排行榜（前12个）/region/ranking   
       参考链接：   
       https://api.bilibili.com/x/web-interface/ranking/region?rid=1&day=3&original=0   
       参数：   
       rid 分区id day 几天之内的排行 (只需要返回此参数为3的)   
       返回值(视频信息对象集合list&lt;object&gt; )：  
       视频信息对象：
        * aid bvid
        * bvid bv号
        * pic 封面图片 string
        * title 标题 string
        * typename 子标签名 string
        * pts 评分 int
* ### 视频观看记录模块 Watch-History
    1. /list   
       参考链接：https://api.bilibili.com/x/web-interface/history/cursor   
       参数：
        * ps 数量

       返回值：
        * list 视频对象列表 list&lt;object&gt; 视频对象：
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
    1. /folder    
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
    2. /resource 参考链接：https://api.bilibili.com/x/v3/fav/resource/list4navigate?platform=web   
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