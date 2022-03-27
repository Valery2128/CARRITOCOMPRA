-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2022 a las 01:43:32
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectotienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) UNSIGNED NOT NULL,
  `Documento` varchar(10) DEFAULT NULL,
  `Nombres` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Con_pass` varchar(100) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `idRol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `Documento`, `Nombres`, `Direccion`, `Email`, `Password`, `Con_pass`, `estado`, `idRol`) VALUES
(1, '00000001', 'Usuario Admin', 'Sistemas', 'admin@gmail.com', 'admin123', 'admin123', '1', 1),
(19, '100109', 'lolal', 'cra103Cbis#140b-14', 'lola123@gmail.com', 'nuevacontraseña1', 'nuevacontraseña1', '1', 2),
(27, '12345678', 'mario v', 'Colombia 123', 'jmo@gmail.com', '12345', '12345', '0', 2),
(36, '99999999', 'user0002', 'los alamos', 'user@gmail.com', '123', '123', '0', 2),
(48, '09889890', 'Cliente 005', 'Los Andes 345', 'cliente005@gmail.com', '12345', '12345', '1', 2),
(56, '1001091385', 'Valery Juliana', 'carrera 103 c bis #140', 'vjmorantes@misena.edu.co', 'nuevacontraseña', 'nuevacontraseña', '1', 2),
(58, '1007103082', 'sebastian mariÃ±o', 'cedfregre', 'zhixtin1207@icloud.com', 'valerymiamor', 'valerymiamor', '1', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `idCompras` int(11) UNSIGNED NOT NULL,
  `idCliente` int(11) UNSIGNED NOT NULL,
  `idPago` int(11) UNSIGNED NOT NULL,
  `FechaCompras` varchar(11) DEFAULT NULL,
  `Monto` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `igv` double DEFAULT NULL,
  `montoFinal` double DEFAULT NULL,
  `Estado` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`idCompras`, `idCliente`, `idPago`, `FechaCompras`, `Monto`, `descuento`, `igv`, `montoFinal`, `Estado`) VALUES
(92, 1, 54, '23/03/2022', 339.98, 0, 61.196400000000004, 339.98, 'Cancelado - En Proceso de Envio'),
(93, 48, 55, '23/03/2022', 1154.99, 0, 207.8982, 1154.99, 'Cancelado - En Proceso de Envio'),
(94, 1, 56, '23/03/2022', 1154.99, 0, 207.8982, 1154.99, 'Cancelado - En Proceso de Envio'),
(95, 56, 57, '25/03/2022', 464.97, 0, 83.69460000000001, 464.97, 'Cancelado - En Proceso de Envio'),
(96, 58, 58, '26/03/2022', 1169.99, 0, 210.5982, 1169.99, 'Cancelado - En Proceso de Envio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_compras`
--

CREATE TABLE `detalle_compras` (
  `idDetalle` int(10) UNSIGNED NOT NULL,
  `idProducto` int(11) UNSIGNED NOT NULL,
  `idCompras` int(11) UNSIGNED NOT NULL,
  `Cantidad` int(11) UNSIGNED DEFAULT NULL,
  `PrecioCompra` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `precioFinal` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_compras`
--

INSERT INTO `detalle_compras` (`idDetalle`, `idProducto`, `idCompras`, `Cantidad`, `PrecioCompra`, `descuento`, `precioFinal`, `subtotal`) VALUES
(145, 22, 92, 2, 15, 0, 15, 0),
(146, 23, 92, 2, 154.99, 0, 154.99, 0),
(152, 22, 96, 1, 15, 15, 0, 0),
(153, 23, 96, 1, 154.99, 154.99, 0, 0),
(154, 24, 96, 1, 1000, 1000, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `idPago` int(11) UNSIGNED NOT NULL,
  `Monto` double DEFAULT NULL,
  `idTarjeta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`idPago`, `Monto`, `idTarjeta`) VALUES
(44, 600000, 1),
(45, 600000, 1),
(46, 600000, 1),
(47, 300, 1),
(48, 300, 1),
(49, 600000, 1),
(50, 304, 1),
(51, 600624, 1),
(52, 600, 1),
(53, 1200900, 1),
(54, 339.98, 1),
(55, 1154.99, 1),
(56, 1154.99, 1),
(57, 464.97, 1),
(58, 1169.99, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) UNSIGNED NOT NULL,
  `Nombres` varchar(255) DEFAULT NULL,
  `Foto` varchar(200) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `Precio` double DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `Nombres`, `Foto`, `Descripcion`, `Precio`, `Stock`) VALUES
(22, 'AUDIFONOS', 'http://localhost/img/158838-headphones-review-apple-airpods-3rd-generation-review-product-shots-image25-cgpbkmwpfd.jpg', '', 15, 1),
(23, 'BANDA DE COLORES', 'http://localhost/img/BBVA-OpenMind-tendencias-tecnologia-2015-applewatch.jpg', '', 154.99, -5),
(24, 'TELEVISOR PLASMA', 'http://localhost/img/descarga (4).jfif', '', 1000, -2),
(26, 'COMBO PORTATIL', 'http://localhost/img/electroÌ�nicos-1.jpg', '', 1000, 1),
(27, 'MOUSE', 'http://localhost/img/imgblanca.jpg', '', 1000, 1),
(31, 'MARCA T.V SAMSUNG', 'http://localhost/img/samsung-logo-1993.jpg', '', 1000, 1),
(41, 'celular', 'http://localhost/img/descarga (1).jfif', '', 6, 1),
(42, 'NUEVO PRODUCTOiiiiiiiiiiiiiiiiiiiiiiiiii', 'http://localhost/img/73913461-21ec-413e-9a72-887e42da4cb6_alta-libre-aspect-ratio_default_0.jpg', '', 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `estado` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `Descripcion`, `estado`) VALUES
(1, 'ADMINISTRADOR', '1'),
(2, 'USUARIO', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `idTarjeta` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL DEFAULT '0',
  `Numero` varchar(50) NOT NULL DEFAULT '0',
  `FechaCaducidad` varchar(50) NOT NULL DEFAULT '0',
  `CodSeguridad` varchar(50) NOT NULL DEFAULT '0',
  `saldo` varchar(50) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`idTarjeta`, `Nombre`, `Numero`, `FechaCaducidad`, `CodSeguridad`, `saldo`) VALUES
(1, 'JUAN PEREZ', '12345678', '11/26', '123', '963948950283.0801');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `FK_DOCUMENTO` (`Documento`),
  ADD KEY `FK_idRol` (`idRol`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idCompras`),
  ADD KEY `Compras_FKIndex1` (`idPago`),
  ADD KEY `Compras_FKIndex2` (`idCliente`);

--
-- Indices de la tabla `detalle_compras`
--
ALTER TABLE `detalle_compras`
  ADD PRIMARY KEY (`idDetalle`,`idProducto`,`idCompras`),
  ADD KEY `Producto_has_Compras_FKIndex1` (`idProducto`),
  ADD KEY `Producto_has_Compras_FKIndex2` (`idCompras`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`idPago`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`),
  ADD UNIQUE KEY `Nombres` (`Nombres`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`idTarjeta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idCompras` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT de la tabla `detalle_compras`
--
ALTER TABLE `detalle_compras`
  MODIFY `idDetalle` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=155;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `idPago` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `idTarjeta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK_idRol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`idPago`) REFERENCES `pago` (`idPago`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_compras`
--
ALTER TABLE `detalle_compras`
  ADD CONSTRAINT `detalle_compras_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detalle_compras_ibfk_2` FOREIGN KEY (`idCompras`) REFERENCES `compras` (`idCompras`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
