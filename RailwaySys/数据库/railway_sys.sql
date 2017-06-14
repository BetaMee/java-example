-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2017-06-14 04:07:30
-- 服务器版本： 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `railway_sys`
--

-- --------------------------------------------------------

--
-- 表的结构 `websites`
--

CREATE TABLE `websites` (
  `id` int(11) NOT NULL,
  `train_name` char(20) NOT NULL DEFAULT '',
  `start_date` char(20) NOT NULL DEFAULT '',
  `start_time` char(20) NOT NULL DEFAULT '',
  `start_city` char(10) NOT NULL DEFAULT '',
  `end_city` char(10) NOT NULL DEFAULT '',
  `tickets` int(11) NOT NULL DEFAULT '0',
  `price` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `websites`
--

INSERT INTO `websites` (`id`, `train_name`, `start_date`, `start_time`, `start_city`, `end_city`, `tickets`, `price`) VALUES
(1, 'T101', '2017-11-5', '11:00:00', '北京', '上海', 500, 0),
(2, 'D50', '2017-11-6', '12:00:00', '南京', '上海', 500, 0),
(5, 'ddsf', 'fdsa', 'fds', 'asfd', 'dsaf', 200, 200),
(6, 'D502', '2017-8-9', '11:30:00', '上海', '成都', 800, 200),
(7, 'T500', '2018-8-9', '18:20:00', '上海', '杭州', 20, 500),
(8, 'G50', '2017-6-9', '15:30:00', '上海', '苏州', 500, 50),
(9, 'D50', '2017-6-15', '16:00:00', '??', '??', 500, 56),
(10, 'D96', '2017-9-8', '12:00:00', '??', '??', 1000, 500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `websites`
--
ALTER TABLE `websites`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `websites`
--
ALTER TABLE `websites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
