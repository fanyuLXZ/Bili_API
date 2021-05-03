package com.dreamwolf.entity.dynamic;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 动态数据表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dynamicdata implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("udID")
    private Integer udID;

    /**
     * 动态点赞总数
     */
    @TableField("udLikeNum")
    private Long udLikeNum;

    /**
     * 动态转发数
     */
    @TableField("udRetweetNum")
    private Long udRetweetNum;

    /**
     * 动态评论数
     */
    @TableField("udCommentNum")
    private Long udCommentNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUdID() {
        return udID;
    }

    public void setUdID(Integer udID) {
        this.udID = udID;
    }

    public Long getUdLikeNum() {
        return udLikeNum;
    }

    public void setUdLikeNum(Long udLikeNum) {
        this.udLikeNum = udLikeNum;
    }

    public Long getUdRetweetNum() {
        return udRetweetNum;
    }

    public void setUdRetweetNum(Long udRetweetNum) {
        this.udRetweetNum = udRetweetNum;
    }

    public Long getUdCommentNum() {
        return udCommentNum;
    }

    public void setUdCommentNum(Long udCommentNum) {
        this.udCommentNum = udCommentNum;
    }
}
