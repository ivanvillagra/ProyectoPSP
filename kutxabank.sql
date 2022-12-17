-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 17-12-2022 a las 12:35:47
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `kutxabank`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bankaccounts`
--

DROP TABLE IF EXISTS `bankaccounts`;
CREATE TABLE IF NOT EXISTS `bankaccounts` (
  `idBA` bigint NOT NULL AUTO_INCREMENT,
  `iban` varchar(255) NOT NULL,
  `balance` int NOT NULL,
  `idUser` bigint NOT NULL,
  PRIMARY KEY (`idBA`),
  KEY `fk_iduser_bankaccount` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `idTrans` bigint NOT NULL AUTO_INCREMENT,
  `idAcountStart` bigint NOT NULL,
  `idAcountEnd` bigint NOT NULL,
  `money` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idTrans`),
  KEY `idAcountEnd` (`idAcountEnd`),
  KEY `idAcountStart` (`idAcountStart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `idUser` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Surname` varchar(50) NOT NULL,
  `pass` int NOT NULL,
  `DNI` varchar(9) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `unique_dni` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bankaccounts`
--
ALTER TABLE `bankaccounts`
  ADD CONSTRAINT `fk_iduser_bankaccount` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`idAcountEnd`) REFERENCES `bankaccounts` (`idBA`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`idAcountStart`) REFERENCES `bankaccounts` (`idBA`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
