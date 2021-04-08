package com.dreamwolf.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 用户信息表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(value = "uID",type = IdType.AUTO)
    private Integer uID; //用户唯一ID
    @TableField(value = "userName")
    private String userName; //用户名（唯一）
    @TableField(value = "password")
    private String password;  //用户密码
    @TableField(value = "nickName")
    private String nickName;    //用户昵称
    @TableField(value = "sex")
    private Integer sex;    //用户性别，1为男，2为女，0为私密
    @TableField(value = "birthday")
    private Date birthday;  //用户生日
    @TableField(value = "boundEmail")
    private String boundEmail;  //被绑定邮箱
    @TableField(value = "boundPhone")
    private String boundPhone;  //被绑定手机号
    @TableField(value = "boundQQ")
    private String boundQQ;     //被绑定qq
    @TableField(value = "headImgPath")
    private String headImgPath;


}
