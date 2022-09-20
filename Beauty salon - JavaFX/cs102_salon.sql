-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2020 at 11:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs102_salon`
--

-- --------------------------------------------------------

--
-- Table structure for table `klijent`
--

CREATE TABLE `klijent` (
  `id` int(11) NOT NULL,
  `ime` varchar(64) NOT NULL,
  `prezime` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `klijent`
--

INSERT INTO `klijent` (`id`, `ime`, `prezime`) VALUES
(1, 'Aleksandra', 'Pavlovic'),
(3, 'Jelena', 'Jelic'),
(4, 'Maja', 'Markovic'),
(5, 'Sara', 'Mitic'),
(6, 'Katarina', 'Krstic');

-- --------------------------------------------------------

--
-- Table structure for table `radnik`
--

CREATE TABLE `radnik` (
  `id` int(11) NOT NULL,
  `ime` varchar(64) NOT NULL,
  `prezime` varchar(64) NOT NULL,
  `specijalnost` varchar(64) NOT NULL,
  `zarada` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `radnik`
--

INSERT INTO `radnik` (`id`, `ime`, `prezime`, `specijalnost`, `zarada`) VALUES
(1, 'Kristina', 'Kikic', 'Sminker', 800),
(2, 'Aleksandra', 'Markovic', 'Frizer', 1450),
(4, 'Sanja', 'Savic', 'Sminker', 3000),
(6, 'Danica', 'Peric', 'Frizer', 2050);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `naziv` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `naziv`) VALUES
(1, 'aktivan'),
(2, 'zavrsen');

-- --------------------------------------------------------

--
-- Table structure for table `tretman`
--

CREATE TABLE `tretman` (
  `id` int(11) NOT NULL,
  `id_radnika` int(11) NOT NULL,
  `id_klijenta` int(11) NOT NULL,
  `id_usluge` int(11) NOT NULL,
  `id_statusa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tretman`
--

INSERT INTO `tretman` (`id`, `id_radnika`, `id_klijenta`, `id_usluge`, `id_statusa`) VALUES
(1, 4, 1, 10, 2),
(2, 2, 4, 5, 2),
(3, 6, 5, 5, 2),
(4, 6, 3, 1, 2),
(5, 6, 6, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `usluga`
--

CREATE TABLE `usluga` (
  `id` int(11) NOT NULL,
  `naziv` varchar(64) NOT NULL,
  `cena` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usluga`
--

INSERT INTO `usluga` (`id`, `naziv`, `cena`) VALUES
(1, 'Feniranje kratke kose', 600),
(2, 'Feniranje kose srednje dužine', 700),
(3, 'Feniranje duge kose', 1000),
(4, 'Feniranje ekstra duge kose', 1000),
(5, 'Presa / kupa - kosa srednje dužine', 900),
(6, 'Presa / kupa - duga kosa', 1200),
(7, 'Presa / kupa - ekstra duga kosa', 1400),
(10, 'Sminkanje', 2000),
(29, 'Farbanje', 1000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `klijent`
--
ALTER TABLE `klijent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `radnik`
--
ALTER TABLE `radnik`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tretman`
--
ALTER TABLE `tretman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_klijenta` (`id_klijenta`),
  ADD KEY `id_radnika` (`id_radnika`),
  ADD KEY `id_usluge` (`id_usluge`),
  ADD KEY `id_statusa` (`id_statusa`);

--
-- Indexes for table `usluga`
--
ALTER TABLE `usluga`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `naziv` (`naziv`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `klijent`
--
ALTER TABLE `klijent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `radnik`
--
ALTER TABLE `radnik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tretman`
--
ALTER TABLE `tretman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `usluga`
--
ALTER TABLE `usluga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tretman`
--
ALTER TABLE `tretman`
  ADD CONSTRAINT `tretman_ibfk_1` FOREIGN KEY (`id_klijenta`) REFERENCES `klijent` (`id`),
  ADD CONSTRAINT `tretman_ibfk_2` FOREIGN KEY (`id_radnika`) REFERENCES `radnik` (`id`),
  ADD CONSTRAINT `tretman_ibfk_3` FOREIGN KEY (`id_usluge`) REFERENCES `usluga` (`id`),
  ADD CONSTRAINT `tretman_ibfk_4` FOREIGN KEY (`id_statusa`) REFERENCES `status` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
