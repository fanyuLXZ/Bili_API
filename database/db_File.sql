-- MySQL Script generated by MySQL Workbench
-- Wed Apr 14 09:55:19 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BiliBili_db
-- -----------------------------------------------------
-- 仿bilibili网站数据库
DROP SCHEMA IF EXISTS `BiliBili_db` ;

-- -----------------------------------------------------
-- Schema BiliBili_db
--
-- 仿bilibili网站数据库
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BiliBili_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `BiliBili_db` ;

-- -----------------------------------------------------
-- Table `BiliBili_db`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`User` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`User` (
  `uID` INT(9) NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID',
  `userName` VARCHAR(16) NOT NULL COMMENT '用户名（唯一）',
  `password` VARCHAR(16) NOT NULL COMMENT '用户密码',
  `nickName` VARCHAR(16) NOT NULL COMMENT '用户昵称',
  `sex` INT(1) NULL COMMENT '用户性别，1为男，2为女，0为私密',
  `birthday` TIMESTAMP NULL COMMENT '用户生日',
  `boundEmail` VARCHAR(50) NOT NULL COMMENT '被绑定邮箱',
  `boundPhone` VARCHAR(11) NOT NULL COMMENT '被绑定手机号',
  `boundQQ` VARCHAR(15) NULL COMMENT '被绑定qq',
  `headImgPath` VARCHAR(255) NULL,
  PRIMARY KEY (`uID`),
  UNIQUE INDEX `BoundPhone_UNIQUE` (`boundPhone` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = '用户信息表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`Zoning`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`Zoning` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`Zoning` (
  `zID` INT NOT NULL AUTO_INCREMENT COMMENT '分区ID',
  `zName` VARCHAR(50) NOT NULL COMMENT '分区名称',
  PRIMARY KEY (`zID`))
ENGINE = InnoDB
COMMENT = '分区表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`Video`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`Video` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`Video` (
  `bvID` INT(10) ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'bv号',
  `uID` INT(9) NOT NULL COMMENT '视频作者ID',
  `bvCoverImgPath` VARCHAR(255) NOT NULL COMMENT '视频封面图',
  `bvVideoPath` VARCHAR(255) NOT NULL COMMENT '视频文件路径',
  `bvVideoTime` TIME NOT NULL COMMENT '视频总时长',
  `bvTitle` VARCHAR(40) NOT NULL COMMENT '视频标题',
  `bvDesc` TEXT CHARACTER SET 'utf8mb4' NOT NULL COMMENT '视频简介',
  `bvPostTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '视频上传日期（默认为当前时间）',
  `bvChildZoning` INT NOT NULL DEFAULT 0 COMMENT '视频子分区ID',
  `bvIsDel` TINYINT NOT NULL DEFAULT 0 COMMENT '是否已删除，0为未删除，1为已删除',
  `bvVideoTime` TIME NOT NULL COMMENT '视频总时长',
  PRIMARY KEY (`bvID`),
  INDEX `fk_VideoInfo_User1_idx` (`uID` ASC) VISIBLE,
  INDEX `fk_Video_Tag1_idx` (`bvChildZoning` ASC) VISIBLE,
  CONSTRAINT `fk_VideoInfo_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Video_Tag1`
    FOREIGN KEY (`bvChildZoning`)
    REFERENCES `BiliBili_db`.`Zoning` (`zID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '视频基础信息表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`zoningRelation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`zoningRelation` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`zoningRelation` (
  `zrID` INT NOT NULL AUTO_INCREMENT COMMENT '主键，无意义',
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '视频id',
  `zID` INT NOT NULL COMMENT '分区id',
  PRIMARY KEY (`zrID`, `zID`),
  INDEX `fk_TreePaths_Tag1_idx` (`zID` ASC) VISIBLE,
  INDEX `fk_TagRelation_Video1_idx` (`bvID` ASC) VISIBLE,
  UNIQUE INDEX `bvID_UNIQUE` (`bvID` ASC) VISIBLE,
  CONSTRAINT `fk_TreePaths_Tag1`
    FOREIGN KEY (`zID`)
    REFERENCES `BiliBili_db`.`Zoning` (`zID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TagRelation_Video1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '分区关系表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`Relations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`Relations` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`Relations` (
  `uID` INT(9) NOT NULL COMMENT '用户本人ID\n',
  `followUID` INT(9) NOT NULL COMMENT '粉丝ID\n',
  INDEX `fk_Friendship_User1_idx` (`uID` ASC) VISIBLE,
  INDEX `fk_Friendship_User2_idx` (`followUID` ASC) VISIBLE,
  PRIMARY KEY (`uID`, `followUID`),
  CONSTRAINT `fk_Friendship_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Friendship_User2`
    FOREIGN KEY (`followUID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户关系表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`VIP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`VIP` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`VIP` (
  `vID` INT NOT NULL AUTO_INCREMENT COMMENT '主键无意义',
  `uID` INT(9) NOT NULL COMMENT '用户ID',
  `ExpirationTime` TIMESTAMP NOT NULL COMMENT '大会员过期时间',
  `vPoint` INT NOT NULL DEFAULT 0 COMMENT '会员积分',
  PRIMARY KEY (`vID`),
  INDEX `fk_BigMember_User1_idx` (`uID` ASC) VISIBLE,
  UNIQUE INDEX `uID_UNIQUE` (`uID` ASC) VISIBLE,
  CONSTRAINT `fk_BigMember_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户大会员信息';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`videoData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`videoData` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`videoData` (
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '视频对应BV号',
  `bvPlayNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频播放数',
  `bvPopupsNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频弹幕数',
  `bvLikeNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频顶数',
  `bvCoinNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频硬币数',
  `bvFavoriteNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频收藏数',
  `bvRetweetNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频转发数',
  `bvCommentNum` BIGINT NOT NULL DEFAULT 0 COMMENT '视频评论数',
  INDEX `fk_VideoData_VideoInfo1_idx` (`bvID` ASC) VISIBLE,
  CONSTRAINT `fk_VideoData_VideoInfo1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '视频数据表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`videoRating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`videoRating` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`videoRating` (
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '视频ID',
  `OverallRating` DOUBLE(10,1) NOT NULL DEFAULT 0 COMMENT '视频综合评分，评分规则详见数据库说明书。',
  CONSTRAINT `fk_Rating_Video1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '视频综合评分表，根据该表判断视频排名';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`userData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`userData` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`userData` (
  `uID` INT NOT NULL COMMENT '用户ID',
  `Level` INT NOT NULL DEFAULT 0 COMMENT '用户等级',
  `Exp` BIGINT NOT NULL DEFAULT 0 COMMENT '用户现有经验',
  `CoinsNum` DOUBLE(10,1) NOT NULL DEFAULT 0 COMMENT '用户现有硬币数量',
  `BCoinsNum` BIGINT NOT NULL DEFAULT 0 COMMENT '用户现有B币数量',
  `tFollowNum` BIGINT NOT NULL DEFAULT 0 COMMENT '用户总关注up主数量',
  `tFansNum` BIGINT NOT NULL DEFAULT 0 COMMENT '用户总粉丝数量',
  `tLikeNum` BIGINT NOT NULL DEFAULT 0 COMMENT '用户总获赞数',
  `tPlaysNum` BIGINT NOT NULL DEFAULT 0 COMMENT '用户上传的视频的总播放数',
  `tReadNum` BIGINT NOT NULL DEFAULT 0 COMMENT '用户撰写的专栏的总阅读数',
  `uDescription` VARCHAR(200) NULL COMMENT '用户简介',
  CONSTRAINT `fk_userData_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户个人数据';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`videoHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`videoHistory` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`videoHistory` (
  `uID` INT(9) NOT NULL COMMENT '用户ID',
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '视频ID',
  `CloseTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '关闭媒体时间',
  `TimelinePosition` TIME NULL COMMENT '关闭时，时间线位置',
  UNIQUE INDEX `uID_UNIQUE` (`uID` ASC),
  CONSTRAINT `fk_userHistory_Video1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userHistory_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户观看历史表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`Article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`Article` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`Article` (
  `cvID` INT(8) NOT NULL COMMENT '专栏文章ID，主键自增',
  `uID` INT(9) NOT NULL COMMENT '专栏文章作者ID',
  `cvCoverImgID` INT NOT NULL COMMENT '专栏标题图片ID，关联图片表',
  `cvTitle` VARCHAR(40) NOT NULL COMMENT '专栏标题表',
  `cvPostTime` TIMESTAMP NOT NULL COMMENT '专栏文章上传时间',
  `cvTags` VARCHAR(150) NOT NULL DEFAULT '[]' COMMENT '专栏标签，格式为[id,id,id,...]',
  `cvText` LONGTEXT CHARACTER SET 'utf8mb4' NOT NULL COMMENT '专栏正文',
  `cvIsDel` TINYINT NOT NULL DEFAULT 0 COMMENT '是否已删除，0为未删除，1为已删除',
  PRIMARY KEY (`cvID`),
  INDEX `fk_Articles_User1_idx` (`uID` ASC) VISIBLE,
  CONSTRAINT `fk_Articles_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '专栏基础信息表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`articleData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`articleData` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`articleData` (
  `cvID` INT(8) NOT NULL COMMENT '文章ID',
  `cvReadNum` BIGINT NOT NULL COMMENT '文章总阅读数',
  `cvLikeNum` BIGINT NOT NULL COMMENT '文章总点赞数',
  `cvCoinNum` BIGINT NOT NULL COMMENT '文章总获得硬币数',
  `cvFavoriteNum` BIGINT NOT NULL COMMENT '文章总收藏数',
  `cvCommenNum` BIGINT NOT NULL COMMENT '文章总评论数',
  `cvRetweetNum` BIGINT NOT NULL COMMENT '文章总转发数',
  INDEX `fk_ArticleData_Article1_idx` (`cvID` ASC) VISIBLE,
  CONSTRAINT `fk_ArticleData_Article1`
    FOREIGN KEY (`cvID`)
    REFERENCES `BiliBili_db`.`Article` (`cvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '专栏数据表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`articleRating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`articleRating` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`articleRating` (
  `cvID` INT(8) NOT NULL COMMENT '文章ID',
  `OverallRating` DOUBLE(10,1) NOT NULL DEFAULT 0 COMMENT '文章综合评分,评分规则详见数据库说明书。',
  INDEX `fk_ArticleRating_Article1_idx` (`cvID` ASC) VISIBLE,
  CONSTRAINT `fk_ArticleRating_Article1`
    FOREIGN KEY (`cvID`)
    REFERENCES `BiliBili_db`.`Article` (`cvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '专栏文章综合评分表，根据该表判断文章排名';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`userMsgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`userMsgs` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`userMsgs` (
  `umID` INT NOT NULL AUTO_INCREMENT COMMENT '主键自增ID，无意义',
  `userID` INT(9) NOT NULL COMMENT '发送信息的用户ID',
  `friendID` INT(9) NOT NULL COMMENT '接收信息的用户ID',
  `sender` VARCHAR(16) NOT NULL COMMENT '留言发送者',
  `receiver` VARCHAR(16) NOT NULL COMMENT '留言接收者',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '发送信息时间',
  `content` TEXT NOT NULL COMMENT '留言内容',
  PRIMARY KEY (`umID`),
  INDEX `fk_userMsgs_User2_idx` (`friendID` ASC) VISIBLE,
  INDEX `fk_userMsgs_User1_idx` (`userID` ASC) VISIBLE,
  CONSTRAINT `fk_userMsgs_User1`
    FOREIGN KEY (`userID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userMsgs_User2`
    FOREIGN KEY (`friendID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户消息表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`Comment` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`Comment` (
  `cID` INT NOT NULL AUTO_INCREMENT COMMENT '用户评论ID，主键自增',
  `uID` INT(9) NOT NULL COMMENT '撰写评论的用户ID',
  `cID_reply` INT NULL COMMENT '回复的评论ID',
  `createTime` TIMESTAMP NOT NULL COMMENT '评论时间\n',
  `cText` TEXT NOT NULL COMMENT '评论正文',
  `isDel` TINYINT NOT NULL DEFAULT 0 COMMENT '是否已删除，0为未删除，1为已删除',
  PRIMARY KEY (`cID`),
  INDEX `fk_userComment_User1_idx` (`uID` ASC) VISIBLE,
  CONSTRAINT `fk_userComment_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户评论表\n';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`userDynamic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`userDynamic` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`userDynamic` (
  `udID` INT NOT NULL COMMENT '单条动态ID，主键自增',
  `uID` INT(9) NOT NULL COMMENT '用户ID',
  `content` TEXT CHARACTER SET 'utf8mb4' NOT NULL COMMENT '动态正文',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '发表动态的时间',
  `isDel` TINYINT NOT NULL DEFAULT 0 COMMENT '动态是否已经删除',
  PRIMARY KEY (`udID`),
  INDEX `fk_userDynamic_User1_idx` (`uID` ASC) VISIBLE,
  CONSTRAINT `fk_userDynamic_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户动态表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`dynamicData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`dynamicData` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`dynamicData` (
  `udID` INT NOT NULL,
  `udLikeNum` BIGINT NOT NULL DEFAULT 0 COMMENT '动态点赞总数',
  `udRetweetNum` BIGINT NOT NULL DEFAULT 0 COMMENT '动态转发数',
  `udCommentNum` BIGINT NOT NULL DEFAULT 0 COMMENT '动态评论数',
  INDEX `fk_DynamicData_userDynamic1_idx` (`udID` ASC) VISIBLE,
  CONSTRAINT `fk_DynamicData_userDynamic1`
    FOREIGN KEY (`udID`)
    REFERENCES `BiliBili_db`.`userDynamic` (`udID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '动态数据表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`videoLike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`videoLike` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`videoLike` (
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '视频ID',
  `uID` INT(9) NOT NULL COMMENT '点赞的用户ID',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '点赞状态，0为未作任何操作，1为点赞\n默认为0',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '点赞时间',
  INDEX `fk_videoLike_Video1_idx` (`bvID` ASC) VISIBLE,
  INDEX `fk_videoLike_User1_idx` (`uID` ASC) VISIBLE,
  PRIMARY KEY (`bvID`, `uID`),
  CONSTRAINT `fk_videoLike_Video1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_videoLike_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '视频点赞表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`articleLike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`articleLike` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`articleLike` (
  `cvID` INT(8) NOT NULL COMMENT '专栏文章ID',
  `uID` INT(9) NOT NULL COMMENT '点赞用户ID',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '点赞状态，0为未作任何操作，1为点赞\n默认为0',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '点赞时间，默认为当前时间',
  INDEX `fk_articleLike_Article1_idx` (`cvID` ASC) VISIBLE,
  INDEX `fk_articleLike_User1_idx` (`uID` ASC) VISIBLE,
  PRIMARY KEY (`cvID`, `uID`),
  CONSTRAINT `fk_articleLike_Article1`
    FOREIGN KEY (`cvID`)
    REFERENCES `BiliBili_db`.`Article` (`cvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articleLike_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '专栏文章点赞表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`commentData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`commentData` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`commentData` (
  `cID` INT NOT NULL COMMENT '评论ID',
  `cLikeNum` BIGINT NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `cUnLikeNum` BIGINT NOT NULL DEFAULT 0 COMMENT '点踩数量',
  INDEX `fk_commentData_userComment1_idx` (`cID` ASC) VISIBLE,
  CONSTRAINT `fk_commentData_userComment1`
    FOREIGN KEY (`cID`)
    REFERENCES `BiliBili_db`.`Comment` (`cID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '评论数据表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`commentLike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`commentLike` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`commentLike` (
  `cID` INT NOT NULL COMMENT '被点赞的评论ID',
  `uID` INT(9) NOT NULL COMMENT '点赞的用户ID',
  `status` INT(1) NOT NULL DEFAULT 0 COMMENT '点赞状态，未做任何操作为0，点赞为1，点踩为2\n默认为0',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '点赞时间，默认为当前时间',
  INDEX `fk_commentLike_userComment1_idx` (`cID` ASC) VISIBLE,
  INDEX `fk_commentLike_User1_idx` (`uID` ASC) VISIBLE,
  PRIMARY KEY (`cID`, `uID`),
  CONSTRAINT `fk_commentLike_userComment1`
    FOREIGN KEY (`cID`)
    REFERENCES `BiliBili_db`.`Comment` (`cID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentLike_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '评论点赞表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`dynamicLike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`dynamicLike` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`dynamicLike` (
  `udID` INT NOT NULL COMMENT '被点赞的动态ID',
  `uID` INT(9) NOT NULL COMMENT '点赞用户ID',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '点赞状态，点赞状态，未做任何操作为0，点赞为1\n默认为0',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '点赞时间，默认为当前时间',
  INDEX `fk_dynamicLike_userDynamic1_idx` (`udID` ASC) VISIBLE,
  INDEX `fk_dynamicLike_User1_idx` (`uID` ASC) VISIBLE,
  PRIMARY KEY (`udID`, `uID`),
  CONSTRAINT `fk_dynamicLike_userDynamic1`
    FOREIGN KEY (`udID`)
    REFERENCES `BiliBili_db`.`userDynamic` (`udID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dynamicLike_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '动态点赞表，用于区分用户点赞';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`userFavoriteList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`userFavoriteList` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`userFavoriteList` (
  `favListID` INT NOT NULL AUTO_INCREMENT COMMENT '视频收藏夹ID，主键唯一',
  `uID` INT(9) NOT NULL COMMENT '创建收藏夹的用户ID',
  `name` VARCHAR(20) NOT NULL COMMENT '收藏夹名称，可以重复',
  `isSecret` TINYINT NOT NULL DEFAULT 1 COMMENT '是否为私密收藏夹，私密为1，公开为0',
  `tLikeNum` INT NOT NULL DEFAULT 0 COMMENT '收藏夹的总点赞数',
  `desc` VARCHAR(200) NULL COMMENT '收藏夹描述',
  PRIMARY KEY (`favListID`))
ENGINE = InnoDB
COMMENT = '用户收藏列表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`videoFavorite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`videoFavorite` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`videoFavorite` (
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '被收藏的视频ID',
  `favListID` INT NOT NULL COMMENT '视频收藏夹ID',
  `favTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '收藏时间，默认为当前时间，不支持设置系统时间',
  INDEX `fk_videoFavorite_Video1_idx` (`bvID` ASC) VISIBLE,
  INDEX `fk_videoFavorite_userFavoriteList1_idx` (`favListID` ASC) VISIBLE,
  PRIMARY KEY (`bvID`, `favListID`),
  CONSTRAINT `fk_videoFavorite_Video1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_videoFavorite_userFavoriteList1`
    FOREIGN KEY (`favListID`)
    REFERENCES `BiliBili_db`.`userFavoriteList` (`favListID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '视频收藏表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`articleFavortie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`articleFavortie` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`articleFavortie` (
  `cvID` INT(8) NOT NULL COMMENT '文章ID',
  `uID` INT(9) NOT NULL COMMENT '收藏的用户ID',
  `favTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '收藏时间',
  INDEX `fk_articleFavortie_Article1_idx` (`cvID` ASC) VISIBLE,
  INDEX `fk_articleFavortie_User1_idx` (`uID` ASC) VISIBLE,
  PRIMARY KEY (`cvID`, `uID`),
  CONSTRAINT `fk_articleFavortie_Article1`
    FOREIGN KEY (`cvID`)
    REFERENCES `BiliBili_db`.`Article` (`cvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articleFavortie_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '专栏文章收藏表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`videoComment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`videoComment` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`videoComment` (
  `bvID` INT(10) ZEROFILL NOT NULL COMMENT '视频ID',
  `cID` INT NOT NULL COMMENT '评论ID',
  PRIMARY KEY (`bvID`, `cID`),
  INDEX `fk_videoComment_Comment1_idx` (`cID` ASC) VISIBLE,
  CONSTRAINT `fk_videoComment_Comment1`
    FOREIGN KEY (`cID`)
    REFERENCES `BiliBili_db`.`Comment` (`cID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_videoComment_Video1`
    FOREIGN KEY (`bvID`)
    REFERENCES `BiliBili_db`.`Video` (`bvID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '视频评论关系表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`dynamicComment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`dynamicComment` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`dynamicComment` (
  `udID` INT NOT NULL COMMENT '动态ID',
  `cID` INT NOT NULL COMMENT '评论ID',
  PRIMARY KEY (`udID`, `cID`),
  INDEX `fk_dynamicComment_Comment1_idx` (`cID` ASC) VISIBLE,
  CONSTRAINT `fk_dynamicComment_userDynamic1`
    FOREIGN KEY (`udID`)
    REFERENCES `BiliBili_db`.`userDynamic` (`udID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dynamicComment_Comment1`
    FOREIGN KEY (`cID`)
    REFERENCES `BiliBili_db`.`Comment` (`cID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '动态评论关系表';


-- -----------------------------------------------------
-- Table `BiliBili_db`.`favoriteLike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BiliBili_db`.`favoriteLike` ;

CREATE TABLE IF NOT EXISTS `BiliBili_db`.`favoriteLike` (
  `favListID` INT NOT NULL COMMENT '被点赞的收藏夹ID',
  `uID` INT(9) NOT NULL COMMENT '给予点赞的用户ID',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '点赞状态，0为未点赞，1为已点赞',
  PRIMARY KEY (`favListID`, `uID`),
  INDEX `fk_FavoriteLike_User1_idx` (`uID` ASC) VISIBLE,
  CONSTRAINT `fk_FavoriteLike_userFavoriteList1`
    FOREIGN KEY (`favListID`)
    REFERENCES `BiliBili_db`.`userFavoriteList` (`favListID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FavoriteLike_User1`
    FOREIGN KEY (`uID`)
    REFERENCES `BiliBili_db`.`User` (`uID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
