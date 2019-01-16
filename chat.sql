/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 8.0.12 : Database - chat
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `chat`;

/*Table structure for table `chat_msg` */

DROP TABLE IF EXISTS `chat_msg`;

CREATE TABLE `chat_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `msg_type` enum('friend','group') DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat_msg` */

insert  into `chat_msg`(`id`,`created_at`,`updated_at`,`content`,`msg_type`,`receiver`,`sender`) values 

(1,'2018-09-14 08:21:02','2018-09-14 08:21:02','face[哼] ','group','1','admin'),

(2,'2018-09-14 08:21:58','2018-09-14 08:21:58','小萌小萌 在不在','friend','anne','admin'),

(3,'2018-09-14 08:22:13','2018-09-14 08:22:13','在的 有什么事？','friend','admin','anne'),

(4,'2018-09-14 08:22:23','2018-09-14 08:22:23','没啥事','friend','anne','admin'),

(5,'2018-09-14 08:22:28','2018-09-14 08:22:28','哦哦','friend','admin','anne'),

(6,'2018-09-14 08:48:18','2018-09-14 08:48:18','face[太开心] ','group','1','anne'),

(7,'2018-09-14 09:40:56','2018-09-14 09:40:56','face[熊猫] ','friend','admin','hehe'),

(8,'2018-09-14 09:41:06','2018-09-14 09:41:06','妈的智障','friend','hehe','admin'),

(9,'2018-09-14 09:41:12','2018-09-14 09:41:12','我了个去','friend','admin','hehe'),

(10,'2018-09-14 09:41:16','2018-09-14 09:41:16','你个逗逼','friend','admin','hehe'),

(11,'2018-09-14 09:41:22','2018-09-14 09:41:22','发可','friend','hehe','admin'),

(12,'2018-09-17 02:11:44','2018-09-17 02:11:44','渣渣','friend','hehe','admin'),

(13,'2018-09-17 02:11:46','2018-09-17 02:11:46','说话啊','friend','hehe','admin'),

(14,'2018-09-17 02:11:48','2018-09-17 02:11:48','在不在啊','friend','hehe','admin'),

(15,'2018-09-17 02:11:50','2018-09-17 02:11:50','擦','friend','hehe','admin'),

(16,'2018-09-17 02:24:26','2018-09-17 02:24:26','垃圾咯','friend','admin','hehe'),

(17,'2018-09-17 02:24:28','2018-09-17 02:24:28','真是的','friend','admin','hehe'),

(18,'2018-09-17 02:24:42','2018-09-17 02:24:42','啥玩意','friend','hehe','admin');

/*Table structure for table `friend` */

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `subgroup_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `friend` */

insert  into `friend`(`id`,`created_at`,`updated_at`,`nick_name`,`subgroup_id`,`username`) values 

(1,'2018-09-14 16:17:31','2018-09-14 16:17:29','宁静',1,'vip'),

(2,'2018-09-14 16:17:39','2018-09-14 16:17:41','呵呵',2,'hehe'),

(3,'2018-09-14 16:18:09','2018-09-14 16:18:07','安妮',3,'anne'),

(4,'2018-09-14 16:18:26','2018-09-14 16:18:24','无恙',4,'admin'),

(5,'2018-09-14 16:18:48','2018-09-14 16:18:50','无恙',5,'admin'),

(6,'2018-09-14 16:19:01','2018-09-14 16:18:59','无恙',6,'admin');

/*Table structure for table `group_crowd` */

DROP TABLE IF EXISTS `group_crowd`;

CREATE TABLE `group_crowd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  `approval` bit(1) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `group_dsc` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `group_crowd` */

insert  into `group_crowd`(`id`,`created_at`,`updated_at`,`created_by`,`updated_by`,`approval`,`avatar`,`group_dsc`,`group_name`,`number`,`status`) values 

(1,'2018-09-14 16:09:13','2018-09-14 16:09:15','admin','admin',NULL,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6JxTj3yScXJLwkZspHqvFbx86q-ENGGaHP2I-ZEZdvtkv8DAK5A','地下私密组织','地下党',10,'1'),

(2,'2018-09-17 14:50:30','2018-09-17 14:50:32','admin','admin',NULL,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQp8A5U95tB0SvafYwL_RjlJ1-TOxjIREi-4jzP4JJf9t9dbbti_Q','脑残部门','二五八万',8,'1');

/*Table structure for table `notice_msg` */

DROP TABLE IF EXISTS `notice_msg`;

CREATE TABLE `notice_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `accepter` varchar(255) DEFAULT NULL,
  `handle` bigint(20) DEFAULT NULL,
  `msg_content` varchar(255) DEFAULT NULL,
  `msg_type` enum('1','2','3','4','5') DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `state` enum('1','2','3','4','5','6') DEFAULT NULL,
  `subgroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notice_msg` */

insert  into `notice_msg`(`id`,`created_at`,`updated_at`,`accepter`,`handle`,`msg_content`,`msg_type`,`receiver`,`remark`,`sender`,`state`,`subgroup_id`) values 

(1,'2018-09-20 16:32:38','2018-09-20 16:32:40',NULL,NULL,'测试',NULL,'admin','你好','sys','2',NULL);

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_cron_triggers` */

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_job_details` */

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values 

('quartzScheduler','STATE_ACCESS'),

('quartzScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values 

('quartzScheduler','DESKTOP-HPF84ME1544406344007',1544406497102,10000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qrtz_triggers` */

/*Table structure for table `subgroup` */

DROP TABLE IF EXISTS `subgroup`;

CREATE TABLE `subgroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `subgroup` */

insert  into `subgroup`(`id`,`group_name`,`order_num`,`username`) values 

(1,'我的好友',1,'admin'),

(2,'高中同学',2,'admin'),

(3,'同村伙伴',3,'admin'),

(4,'酒肉朋友',1,'hehe'),

(5,'装逼同事',1,'vip'),

(6,'大学室友',1,'anne');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(15) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `gender` enum('男','女') DEFAULT NULL,
  `line_state` varchar(255) DEFAULT NULL,
  `nick_name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`username`,`created_at`,`updated_at`,`avatar`,`email`,`gender`,`line_state`,`nick_name`,`password`,`real_name`,`signature`,`tel_phone`) values 

('admin','2018-09-14 15:39:28','2018-09-14 15:39:30','https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1402472998,1033961727&fm=58','123456@qq.com','男','online','无恙','$2a$04$a90t7tmpIjJl6Ic9PZTHgeJDqN4iRwk45s8AVmN10v9cZ3jYl0qk6','斯大林','随风飘扬去远方',NULL),

('anne','2018-09-14 15:46:44','2018-09-14 15:46:42','https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2957348567,3650956819&fm=27&gp=0.jpg','531228@qq.com','女','hide','安妮小萌','$2a$04$9KSovkBDzr7HvBjH7Monm.nor.x/mjgo9c9/eG8IeZ2Uh8gMhykoC','安以轩','世间小萌物，可欺不可凌',NULL),

('hehe','2018-09-14 15:41:56','2018-09-14 15:41:59','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3198678185,878755003&fm=27&gp=0.jpg','456789@qq.com','男','hide','呵呵','$2a$04$9KSovkBDzr7HvBjH7Monm.nor.x/mjgo9c9/eG8IeZ2Uh8gMhykoC','何大侠','面对世间所有只有一句呵呵',NULL),

('vip','2018-09-14 15:43:37','2018-09-14 15:43:39','https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3303741086,3211617265&fm=27&gp=0.jpg','987456@qq.com','女','online','宁静','$2a$04$9KSovkBDzr7HvBjH7Monm.nor.x/mjgo9c9/eG8IeZ2Uh8gMhykoC','宁阮静','非宁静无以致远',NULL);

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`created_at`,`updated_at`,`email`,`name`,`password`,`username`) values
(1,'2019-01-16 15:18:21','2019-01-16 15:18:23','53123@qq.com','杰克','$2a$04$9KSovkBDzr7HvBjH7Monm.nor.x/mjgo9c9/eG8IeZ2Uh8gMhykoC','Jack'),
(2,'2019-01-16 17:35:25','2019-01-16 17:35:27','12345@qq.com','克莱','$2a$04$9KSovkBDzr7HvBjH7Monm.nor.x/mjgo9c9/eG8IeZ2Uh8gMhykoC','Cly'),
(3,'2019-01-16 17:36:20','2019-01-16 17:36:22','4568799@qq.com','汤姆','$2a$04$9KSovkBDzr7HvBjH7Monm.nor.x/mjgo9c9/eG8IeZ2Uh8gMhykoC','Tom');

CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  `target_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_group` */

insert  into `user_group`(`id`,`group_id`,`identity`,`nick_name`,`status`,`target_time`,`username`) values 

(1,1,'群主','无恙','1',NULL,'admin'),

(2,1,'管理员','呵呵','1',NULL,'hehe'),

(3,1,'群员','宁静','1',NULL,'vip'),

(4,1,'群员','小萌','1',NULL,'anne'),

(5,2,'管理员','宁静','1',NULL,'vip');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
