-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema shareit_db
--

CREATE DATABASE IF NOT EXISTS shareit_db;
USE shareit_db;

--
-- Definition of table `shareit_addfriends`
--

DROP TABLE IF EXISTS `shareit_addfriends`;
CREATE TABLE `shareit_addfriends` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `initiator` int(10) unsigned NOT NULL,
  `friend` int(10) unsigned NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `message` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_addfriends`
--

/*!40000 ALTER TABLE `shareit_addfriends` DISABLE KEYS */;
/*!40000 ALTER TABLE `shareit_addfriends` ENABLE KEYS */;


--
-- Definition of table `shareit_bookmarks`
--

DROP TABLE IF EXISTS `shareit_bookmarks`;
CREATE TABLE `shareit_bookmarks` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `uri` varchar(100) NOT NULL,
  `title` varchar(100) default NULL,
  `description` varchar(1000) default NULL,
  `user_id` int(10) default NULL,
  `bookmark_date` date NOT NULL,
  `bookmark_time` time NOT NULL,
  `tags` varchar(100) default NULL,
  `share` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_bookmarks`
--

/*!40000 ALTER TABLE `shareit_bookmarks` DISABLE KEYS */;
INSERT INTO `shareit_bookmarks` (`id`,`uri`,`title`,`description`,`user_id`,`bookmark_date`,`bookmark_time`,`tags`,`share`) VALUES 
 (12,'http://www.google.com','Google','The no.1 search engine in the world',16,'2009-04-05','09:17:25','search, engine, world',1),
 (13,'http://www.facebook.com','Facebook','Online social networking',16,'2009-04-05','09:28:41','facebook, friends, reunion',1);
/*!40000 ALTER TABLE `shareit_bookmarks` ENABLE KEYS */;


--
-- Definition of table `shareit_comments`
--

DROP TABLE IF EXISTS `shareit_comments`;
CREATE TABLE `shareit_comments` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `bookmark_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `comment_date` date NOT NULL,
  `comment_time` time NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_comments`
--

/*!40000 ALTER TABLE `shareit_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `shareit_comments` ENABLE KEYS */;


--
-- Definition of table `shareit_forums`
--

DROP TABLE IF EXISTS `shareit_forums`;
CREATE TABLE `shareit_forums` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `forumname` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `creator` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`forumname`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_forums`
--

/*!40000 ALTER TABLE `shareit_forums` DISABLE KEYS */;
INSERT INTO `shareit_forums` (`id`,`forumname`,`description`,`date`,`time`,`creator`) VALUES 
 (5,'Discussion Forum','This is a discurssion form for Software Testing. Happy Coding.. loh kok coding sih. ha ha','2009-04-05','17:04:33','khal');
/*!40000 ALTER TABLE `shareit_forums` ENABLE KEYS */;


--
-- Definition of table `shareit_groups`
--

DROP TABLE IF EXISTS `shareit_groups`;
CREATE TABLE `shareit_groups` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `groupname` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `creator` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_2` (`groupname`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_groups`
--

/*!40000 ALTER TABLE `shareit_groups` DISABLE KEYS */;
INSERT INTO `shareit_groups` (`id`,`groupname`,`description`,`date`,`time`,`creator`) VALUES 
 (10,'Data Mining Group','This group is for who are interested to advance their knowledge in data management techniques. cheers mate!','2009-04-05','12:49:08','khal');
/*!40000 ALTER TABLE `shareit_groups` ENABLE KEYS */;


--
-- Definition of table `shareit_inboxes`
--

DROP TABLE IF EXISTS `shareit_inboxes`;
CREATE TABLE `shareit_inboxes` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `sender` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `message` varchar(1000) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `recipient` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_inboxes`
--

/*!40000 ALTER TABLE `shareit_inboxes` DISABLE KEYS */;
INSERT INTO `shareit_inboxes` (`id`,`sender`,`date`,`time`,`message`,`status`,`recipient`) VALUES 
 (1,12,'2009-04-04','18:38:04','khal',0,12),
 (2,13,'2009-04-04','18:41:50','test khal',0,12),
 (3,12,'2009-04-04','18:59:11','iman',0,13);
/*!40000 ALTER TABLE `shareit_inboxes` ENABLE KEYS */;


--
-- Definition of table `shareit_joinforums`
--

DROP TABLE IF EXISTS `shareit_joinforums`;
CREATE TABLE `shareit_joinforums` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) unsigned NOT NULL,
  `forumid` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_joinforums`
--

/*!40000 ALTER TABLE `shareit_joinforums` DISABLE KEYS */;
INSERT INTO `shareit_joinforums` (`id`,`userid`,`forumid`,`date`,`time`) VALUES 
 (4,16,5,'2009-04-05','17:04:33');
/*!40000 ALTER TABLE `shareit_joinforums` ENABLE KEYS */;


--
-- Definition of table `shareit_joingroups`
--

DROP TABLE IF EXISTS `shareit_joingroups`;
CREATE TABLE `shareit_joingroups` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) unsigned NOT NULL,
  `groupid` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_joingroups`
--

/*!40000 ALTER TABLE `shareit_joingroups` DISABLE KEYS */;
INSERT INTO `shareit_joingroups` (`id`,`userid`,`groupid`,`date`,`time`) VALUES 
 (13,16,10,'2009-04-05','12:49:08'),
 (14,16,10,'2009-04-05','16:38:41');
/*!40000 ALTER TABLE `shareit_joingroups` ENABLE KEYS */;


--
-- Definition of table `shareit_outboxes`
--

DROP TABLE IF EXISTS `shareit_outboxes`;
CREATE TABLE `shareit_outboxes` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `recipient` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `status` tinyint(1) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `sender` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_outboxes`
--

/*!40000 ALTER TABLE `shareit_outboxes` DISABLE KEYS */;
INSERT INTO `shareit_outboxes` (`id`,`recipient`,`date`,`time`,`status`,`message`,`sender`) VALUES 
 (1,12,'2009-04-04','18:38:05',0,'khal',12),
 (2,12,'2009-04-04','18:41:50',0,'test khal',13),
 (3,13,'2009-04-04','18:59:11',0,'iman',12);
/*!40000 ALTER TABLE `shareit_outboxes` ENABLE KEYS */;


--
-- Definition of table `shareit_profiles`
--

DROP TABLE IF EXISTS `shareit_profiles`;
CREATE TABLE `shareit_profiles` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) unsigned NOT NULL,
  `dateofbirth` date default NULL,
  `program` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `school` varchar(100) default NULL,
  `faculty` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  CONSTRAINT `FK_shareit_profiles_1` FOREIGN KEY (`id`) REFERENCES `shareit_users` (`id`),
  CONSTRAINT `FK_shareit_profiles_2` FOREIGN KEY (`id`) REFERENCES `shareit_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_profiles`
--

/*!40000 ALTER TABLE `shareit_profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `shareit_profiles` ENABLE KEYS */;


--
-- Definition of table `shareit_tags`
--

DROP TABLE IF EXISTS `shareit_tags`;
CREATE TABLE `shareit_tags` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `tag_name` varchar(100) NOT NULL,
  `bookmark_id` int(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_tags`
--

/*!40000 ALTER TABLE `shareit_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `shareit_tags` ENABLE KEYS */;


--
-- Definition of table `shareit_users`
--

DROP TABLE IF EXISTS `shareit_users`;
CREATE TABLE `shareit_users` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `creation_time` time NOT NULL,
  `creation_date` date NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_username` (`username`),
  KEY `Index_user` (`username`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shareit_users`
--

/*!40000 ALTER TABLE `shareit_users` DISABLE KEYS */;
INSERT INTO `shareit_users` (`id`,`username`,`password`,`first_name`,`last_name`,`email`,`creation_time`,`creation_date`) VALUES 
 (16,'khal','khal','Khalilur','Rahman','khal.rahman@gmail.com','09:14:43','2009-04-05');
/*!40000 ALTER TABLE `shareit_users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
