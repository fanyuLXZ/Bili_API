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
   1. /info  
      返回值：
      * isLogin 是否登陆
      * email_verified 邮箱是否验证
      * mobile_verified 手机是否验证
      * face 头像
      * level_info 等级信息
        - current_level 当前 li等级
        - current_min 当前等级所需最小经验
        - current_exp 当前经验值
        - next_exp 下等级需要的经验值
      * mid uid
      * money 硬币数
      * uname 用户名
      * vipDueDate 大会员到期时间
      * vipStatus 大会员状态 1为有 0为没有
      * vipType 大会员类型 1为大会员 0为小会员
      * vip_label 大会员标签 固定值`{  
        "path": "",  
        "text": "大会员",  
        "label_theme": "vip",  
        "text_color": "#FFFFFF",  
        "bg_style": 1,  
        "bg_color": "#FB7299",  
        "border_color": ""  
        }`
      * vip 大会员对象
        - type 同上大会员类型
        - status 同上大会员状态
        - due_date 同上大会员到期时间
        - label 同上大会员标签
        - avatar_subscript_url 大会员图片路径
      * wallet 钱包对象
        mid uid
        bcoin_balance b币余额
   3. /
* ### 动态模块 Dynamic
   1./entrance
      返回值：  
      * entrance
* ### 分区模块 Zoning

* ### 视频观看记录模块 Watch-History

* ### 收藏模块 Favorite


* ### 前端接口 web-interface
   1. /nav/stat
      返回值：
      * following 关注数
      * follower 粉丝数
      * dynamic_count 动态数