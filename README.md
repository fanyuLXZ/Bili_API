# Bili_API
仿Bilibili后台仓库


## 项目人员分配
* 徐文建：
    - 视频模块 Video
    - 用户消息模块 Message
* 赵霖：
    - 用户模块 Member
    - 动态模块 Dynamic
    - 分区模块 Zoning
* 王子轩：
    - 视频观看记录模块 watch-history
    - 收藏模块 userFavoriteList
---
## api文档
* ###视频模块 Video
  
* ### 用户消息模块 Message
  
* ### 用户模块 Member
    1. 用户信息 /info  
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
            - type 会员类型 int 1大会员  0小会员 
            - status 是否是会员 int 1是 0否
            - due_date 会员有效时间 date
            - label string 固定返回"{"path": "", "text": "大会员", "label_theme": "vip", "text_color": "#FFFFFF", "bg_style": 1, "bg_color": "#FB7299", "border_color": ""}"
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
        * vip_type 会员类型 int 1大会员  0小会员
        * due_date 会员有效时间
    4. 基本信息 /account/info
        参考链接：https://api.bilibili.com/x/member/web/account
        返回值：
        * uname 昵称 string
        * userid 用户名 string 返回"bili_"+uid
        * birthday 出生年月 date
        * sex 性别 string
    4. 详细信息(全部信息) /info-all
       参考链接：https://api.bilibili.com/x/web-interface/nav  
       返回值：
        * isLogin 是否登陆 boolean 暂时固定值为：true
        
* ### 动态模块 Dynamic
    1. 动态的最新信息 /entrance  
        参考链接：//api.bilibili.com/x/web-interface/dynamic/entrance?video_offset=0&article_offset=0&alltype_offset=0   
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

* ### 视频观看记录模块 Watch-History
    1. /list   
       参考链接：https://api.bilibili.com/x/web-interface/history/cursor   
       参数：
        * ps 数量 
        
       返回值：  
        * list 视频对象列表 list<object>   
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
    1. /folder    
        参考链接：//api.bilibili.com/x/v3/fav/folder/list4navigate   
        返回值：
        返回list 固定返回两个值 第一个值为普通收藏夹("我创建的收藏夹") 第二个为稍后再看   
        * id id int 固定值：普通收藏夹为1，稍后再看为2
        * name 文件夹名 string 固定值：普通收藏夹为"我创建的收藏夹"，稍后再看为"稍后再看"
        * mediaListResponse 收藏夹对象 object
            - count 数量 int
            - list 收藏夹对象集合 list<object>
                收藏夹对象：
                - id 收藏夹id int
                - mid uid int
                - title 收藏夹名 string
                - media_count 收藏夹内视频(媒体)数 int
    2. /resource
      

  
* ### 前端接口 web-interface
   1. /nav/stat
      返回值：
      * following 关注数
      * follower 粉丝数
      * dynamic_count 动态数