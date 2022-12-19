-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-12-2022 a las 22:18:25
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

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

CREATE TABLE `bankaccounts` (
  `idBA` bigint(20) NOT NULL,
  `iban` varchar(255) NOT NULL,
  `balance` int(11) NOT NULL,
  `idUser` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `bankaccounts`
--

INSERT INTO `bankaccounts` (`idBA`, `iban`, `balance`, `idUser`) VALUES
(2, 'ES824005236', 911953, 16),
(3, 'ES814102118', 113300, 18),
(4, 'ES863759410', 18100, 19);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `idUser` bigint(20) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Surname` varchar(50) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `passVer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`idUser`, `Name`, `Surname`, `pass`, `DNI`, `passVer`) VALUES
(15, 'Juan', 'Da Silva', 'd7792ea01be12978c51f26450979310f', '72843606F', ''),
(16, 'Ivan', 'Villagra', 'a5fdd0f9bc9dd4b6716fc42cb1ee8a30', '72843626F', '815674'),
(18, 'JON', 'BANDEIRA', 'e18cc01ebb345c3cb94472b1caa731fd', '72845645R', '072599'),
(19, 'JOn', 'Vazquez', '128cd2903296df61435195c790bba717', '72843555H', '224703');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bankaccounts`
--
ALTER TABLE `bankaccounts`
  ADD PRIMARY KEY (`idBA`),
  ADD KEY `fk_iduser_bankaccount` (`idUser`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `unique_dni` (`DNI`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bankaccounts`
--
ALTER TABLE `bankaccounts`
  MODIFY `idBA` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `idUser` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bankaccounts`
--
ALTER TABLE `bankaccounts`
  ADD CONSTRAINT `fk_iduser_bankaccount` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
