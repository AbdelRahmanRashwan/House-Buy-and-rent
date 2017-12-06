-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2017 at 08:31 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ia`
--

-- --------------------------------------------------------

--
-- Table structure for table `*user`
--

use house_rental;

CREATE TABLE `user` (
  `Name` varchar(150) NOT NULL,
  `Id` int(11) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Phone` varchar(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `picPath` varchar(150) NOT NULL,
  `PrefrencesId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE `advertisement` (
  `Id` int(11) NOT NULL,
  `houseId` int(11) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `rate` float NOT NULL,
  `suspend` tinyint(4) NOT NULL,
  `adOwnerId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `house`
--

CREATE TABLE `house` (
  `Id` int(11) NOT NULL,
  `Size` int(11) NOT NULL,
  `Description` varchar(150) NOT NULL,
  `Floor` int(11) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `PicId` int(11) NOT NULL,
  `Longitude` varchar(100) NOT NULL,
  `Latitude` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `Id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `Description` int(11) NOT NULL,
  `AdvertismentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `picture`
--

CREATE TABLE `picture` (
  `Id` int(11) NOT NULL,
  `path` varchar(150) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `prefrence`
--

CREATE TABLE `prefrence` (
  `Id` int(11) NOT NULL,
  `Type` varchar(150) NOT NULL,
  `size` int(11) NOT NULL,
  `area` varchar(150) NOT NULL,
  `Floor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `userId` int(11) NOT NULL,
  `AdvertisementId` int(11) NOT NULL,
  `rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_advertisement`
--

CREATE TABLE `user_advertisement` (
  `userId` int(11) NOT NULL,
  `AdvertisementId` int(11) NOT NULL,
  `comment` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `*user`
--
ALTER TABLE `*user`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `PrefrencesId` (`PrefrencesId`),
  ADD KEY `picId` (`picPath`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `houseId` (`houseId`),
  ADD KEY `adOwnerId` (`adOwnerId`);

--
-- Indexes for table `house`
--
ALTER TABLE `house`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `PicId` (`PicId`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD KEY `userId` (`userId`),
  ADD KEY `AdvertismentId` (`AdvertismentId`);

--
-- Indexes for table `picture`
--
ALTER TABLE `picture`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `prefrence`
--
ALTER TABLE `prefrence`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `CategoryId` (`size`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD KEY `userId` (`userId`),
  ADD KEY `AdvertisementId` (`AdvertisementId`);

--
-- Indexes for table `user_advertisement`
--
ALTER TABLE `user_advertisement`
  ADD KEY `userId` (`userId`),
  ADD KEY `AdvertisementId` (`AdvertisementId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `*user`
--
ALTER TABLE `*user`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `advertisement`
--
ALTER TABLE `advertisement`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `house`
--
ALTER TABLE `house`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `picture`
--
ALTER TABLE `picture`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `prefrence`
--
ALTER TABLE `prefrence`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `*user`
--
ALTER TABLE `*user`
  ADD CONSTRAINT `*user_ibfk_2` FOREIGN KEY (`PrefrencesId`) REFERENCES `prefrence` (`Id`);

--
-- Constraints for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD CONSTRAINT `advertisement_ibfk_1` FOREIGN KEY (`houseId`) REFERENCES `house` (`Id`),
  ADD CONSTRAINT `advertisement_ibfk_2` FOREIGN KEY (`adOwnerId`) REFERENCES `*user` (`Id`);

--
-- Constraints for table `house`
--
ALTER TABLE `house`
  ADD CONSTRAINT `house_ibfk_1` FOREIGN KEY (`PicId`) REFERENCES `picture` (`Id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `*user` (`Id`),
  ADD CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`AdvertismentId`) REFERENCES `advertisement` (`Id`);

--
-- Constraints for table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `rate_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `*user` (`Id`),
  ADD CONSTRAINT `rate_ibfk_2` FOREIGN KEY (`AdvertisementId`) REFERENCES `advertisement` (`Id`);

--
-- Constraints for table `user_advertisement`
--
ALTER TABLE `user_advertisement`
  ADD CONSTRAINT `user_advertisement_ibfk_1` FOREIGN KEY (`AdvertisementId`) REFERENCES `advertisement` (`Id`),
  ADD CONSTRAINT `user_advertisement_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `*user` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


use house_rental;
INSERT INTO picture VALUES(1,'/home/pictures','soso');
INSERT INTO house VALUES (1,500,'Big and nice house with 2 rooms and one bathroom',1,'House',1,16.7,12.5,'active');