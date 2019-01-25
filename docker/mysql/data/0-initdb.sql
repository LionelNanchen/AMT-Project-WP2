-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 24, 2019 at 10:51 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `amt_project`
--
CREATE DATABASE IF NOT EXISTS `amt_project` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `amt_project`;

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `id` bigint(20) NOT NULL,
  `app_key` varchar(255) DEFAULT NULL,
  `app_token` varchar(255) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`id`, `app_key`, `app_token`, `description`, `enabled`, `name`) VALUES
(1, 'mra', 'mra', 'mra', b'1', 'mra'),
(2, 'toto', 'toto', 'toto', b'1', 'toto');

-- --------------------------------------------------------

--
-- Table structure for table `award`
--

DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` int(11) NOT NULL,
  `nb_points` int(11) DEFAULT NULL,
  `rule_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
CREATE TABLE `badge` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `badge_users`
--

DROP TABLE IF EXISTS `badge_users`;
CREATE TABLE `badge_users` (
  `badges_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL,
  `timestamp` tinyblob,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `point_scale`
--

DROP TABLE IF EXISTS `point_scale`;
CREATE TABLE `point_scale` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `properties`
--

DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reward`
--

DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `id` int(11) NOT NULL,
  `nb_points` int(11) DEFAULT NULL,
  `rule_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rules`
--

DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `badge_id` bigint(20) DEFAULT NULL,
  `point_scale_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rules`
--

INSERT INTO `rules` (`id`, `name`, `quantity`, `type`, `badge_id`, `point_scale_id`) VALUES
(1, 'lioni', 10, 'test', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `rules_conditions`
--

DROP TABLE IF EXISTS `rules_conditions`;
CREATE TABLE `rules_conditions` (
  `id` int(11) NOT NULL,
  `rule_key` varchar(255) DEFAULT NULL,
  `rule_operator` varchar(255) DEFAULT NULL,
  `rule_value` varchar(255) DEFAULT NULL,
  `rule_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `remote_user_id` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_model`
--

DROP TABLE IF EXISTS `user_model`;
CREATE TABLE `user_model` (
  `id` bigint(20) NOT NULL,
  `remote_user_id` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_model`
--

INSERT INTO `user_model` (`id`, `remote_user_id`, `application_id`) VALUES
(1, 'totototo', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_lspnba25gpku3nx3oecprrx8c` (`name`);

--
-- Indexes for table `award`
--
ALTER TABLE `award`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4ifwsg4s178i294xror13apjo` (`rule_id`),
  ADD KEY `FKqhaecygddm67kf8q5hk4i2ar6` (`user_id`);

--
-- Indexes for table `badge`
--
ALTER TABLE `badge`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2edjm9ugh4qgg8xk1nxgokl7y` (`application_id`);

--
-- Indexes for table `badge_users`
--
ALTER TABLE `badge_users`
  ADD KEY `FK9xb1kf1py7rqs7elh8v7oktsa` (`users_id`),
  ADD KEY `FK92rbcsyfy9k0ssmrr9kye8p2r` (`badges_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi8bsvlthqr8lngsyshiqsodak` (`user_id`);

--
-- Indexes for table `point_scale`
--
ALTER TABLE `point_scale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKordmmhy224wxbvi4scr8ft77s` (`application_id`);

--
-- Indexes for table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt8u8rmsrceb442x23k6v87lfu` (`event_id`);

--
-- Indexes for table `reward`
--
ALTER TABLE `reward`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3jr6tf3ybp30h1yqadvgbexw5` (`rule_id`),
  ADD KEY `FK5234qjb0eiwkucnn5hua207nf` (`user_id`);

--
-- Indexes for table `rules`
--
ALTER TABLE `rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc0q608rux0j2lmx0gy1v5lmi9` (`badge_id`),
  ADD KEY `FKa4dw6xkj7d8jfehbqw8rl6gr6` (`point_scale_id`);

--
-- Indexes for table `rules_conditions`
--
ALTER TABLE `rules_conditions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5h0cgs6xilojeaa6gm7c91jsw` (`rule_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKloqygibpr0cjij9v6hm7a5cus` (`application_id`);

--
-- Indexes for table `user_model`
--
ALTER TABLE `user_model`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdw17o3ojina9ov8oxpdq6569t` (`application_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `award`
--
ALTER TABLE `award`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `badge`
--
ALTER TABLE `badge`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `point_scale`
--
ALTER TABLE `point_scale`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reward`
--
ALTER TABLE `reward`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rules`
--
ALTER TABLE `rules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `rules_conditions`
--
ALTER TABLE `rules_conditions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_model`
--
ALTER TABLE `user_model`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `award`
--
ALTER TABLE `award`
  ADD CONSTRAINT `FK4ifwsg4s178i294xror13apjo` FOREIGN KEY (`rule_id`) REFERENCES `rules` (`id`),
  ADD CONSTRAINT `FKqhaecygddm67kf8q5hk4i2ar6` FOREIGN KEY (`user_id`) REFERENCES `user_model` (`id`);

--
-- Constraints for table `badge`
--
ALTER TABLE `badge`
  ADD CONSTRAINT `FK2edjm9ugh4qgg8xk1nxgokl7y` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`);

--
-- Constraints for table `badge_users`
--
ALTER TABLE `badge_users`
  ADD CONSTRAINT `FK92rbcsyfy9k0ssmrr9kye8p2r` FOREIGN KEY (`badges_id`) REFERENCES `badge` (`id`),
  ADD CONSTRAINT `FK9xb1kf1py7rqs7elh8v7oktsa` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FKi8bsvlthqr8lngsyshiqsodak` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `point_scale`
--
ALTER TABLE `point_scale`
  ADD CONSTRAINT `FKordmmhy224wxbvi4scr8ft77s` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`);

--
-- Constraints for table `properties`
--
ALTER TABLE `properties`
  ADD CONSTRAINT `FKt8u8rmsrceb442x23k6v87lfu` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`);

--
-- Constraints for table `reward`
--
ALTER TABLE `reward`
  ADD CONSTRAINT `FK3jr6tf3ybp30h1yqadvgbexw5` FOREIGN KEY (`rule_id`) REFERENCES `rules` (`id`),
  ADD CONSTRAINT `FK5234qjb0eiwkucnn5hua207nf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `rules_conditions`
--
ALTER TABLE `rules_conditions`
  ADD CONSTRAINT `FK5h0cgs6xilojeaa6gm7c91jsw` FOREIGN KEY (`rule_id`) REFERENCES `rules` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKloqygibpr0cjij9v6hm7a5cus` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`);

--
-- Constraints for table `user_model`
--
ALTER TABLE `user_model`
  ADD CONSTRAINT `FKdw17o3ojina9ov8oxpdq6569t` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`);
