-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2023 a las 17:59:04
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectojava`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `idComentario` int(11) NOT NULL,
  `contenido` text DEFAULT NULL,
  `fechaComentario` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `idUsuarioComentante` int(11) DEFAULT NULL,
  `idNoticia` int(11) DEFAULT NULL,
  `idComentarioPadre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`idComentario`, `contenido`, `fechaComentario`, `idUsuarioComentante`, `idNoticia`, `idComentarioPadre`) VALUES
(1, 'Este es un comentario para la Noticia 1', '2023-06-02 16:30:00', 1, 1, NULL),
(2, 'Este es un comentario para la Noticia 2', '2023-06-02 16:30:00', 2, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticias`
--

CREATE TABLE `noticias` (
  `idNoticia` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `contenido` text DEFAULT NULL,
  `fechaPublicacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `idUsuarioPublicante` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `noticias`
--

INSERT INTO `noticias` (`idNoticia`, `titulo`, `contenido`, `fechaPublicacion`, `idUsuarioPublicante`) VALUES
(1, 'Titulo Noticia 1', 'Contenido Noticia 1', '2023-06-02 16:30:00', 1),
(2, 'Titulo Noticia 2', 'Contenido Noticia 2', '2023-06-02 16:30:00', 2);

--
-- Disparadores `noticias`
--
DELIMITER $$
CREATE TRIGGER `before_insert_noticias` BEFORE INSERT ON `noticias` FOR EACH ROW BEGIN
   DECLARE usuario_autorizado BOOLEAN;
   SELECT esInterno AND esAutorizado INTO usuario_autorizado
   FROM Usuarios
   WHERE idUsuario = NEW.idUsuarioPublicante;
   
   IF usuario_autorizado = 0 THEN
      SIGNAL SQLSTATE '45000'
         SET MESSAGE_TEXT = 'Solo usuarios internos y autorizados pueden publicar noticias.';
   END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `idPersonal` int(11) NOT NULL,
  `apePaterno` varchar(50) DEFAULT NULL,
  `apeMaterno` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fechaDeIngreso` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`idPersonal`, `apePaterno`, `apeMaterno`, `nombre`, `direccion`, `fechaDeIngreso`) VALUES
(1, 'Gomez', 'Fernandez', 'Juan', 'Calle Mayor 10', '2023-06-02 16:30:00'),
(2, 'Lopez', 'Martinez', 'Maria', 'Avenida Principal 20', '2023-06-02 16:30:00'),
(3, 'Apellido3', 'ApellidoMaterno3', 'Nombre3', 'Dirección3', '2023-06-04 04:47:31'),
(4, 'Apellido4', 'ApellidoMaterno4', 'Nombre4', 'Dirección4', '2023-06-04 04:47:31'),
(5, 'Apellido5', 'ApellidoMaterno5', 'Nombre5', 'Dirección5', '2023-06-04 04:47:31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `idPersonal` int(11) DEFAULT NULL,
  `nombreUsuario` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `esInterno` tinyint(1) DEFAULT NULL,
  `esAutorizado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `idPersonal`, `nombreUsuario`, `email`, `password`, `esInterno`, `esAutorizado`) VALUES
(1, 1, 'juan123', 'juan@gmail.com', 'pass123', 1, 1),
(2, 2, 'maria456', 'maria@gmail.com', 'pass456', 1, 1),
(3, 3, 'Usuario3', 'usuario3@example.com', 'password3', 1, 1),
(4, 4, 'Usuario4', 'usuario4@example.com', 'password4', 1, 1),
(5, 5, 'Usuario5', 'usuario5@example.com', 'password5', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`idComentario`),
  ADD KEY `idUsuarioComentante` (`idUsuarioComentante`),
  ADD KEY `idNoticia` (`idNoticia`),
  ADD KEY `idComentarioPadre` (`idComentarioPadre`);

--
-- Indices de la tabla `noticias`
--
ALTER TABLE `noticias`
  ADD PRIMARY KEY (`idNoticia`),
  ADD KEY `idUsuarioPublicante` (`idUsuarioPublicante`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`idPersonal`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `idPersonal` (`idPersonal`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`idUsuarioComentante`) REFERENCES `usuarios` (`idUsuario`),
  ADD CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`idNoticia`) REFERENCES `noticias` (`idNoticia`),
  ADD CONSTRAINT `comentarios_ibfk_3` FOREIGN KEY (`idComentarioPadre`) REFERENCES `comentarios` (`idComentario`);

--
-- Filtros para la tabla `noticias`
--
ALTER TABLE `noticias`
  ADD CONSTRAINT `noticias_ibfk_1` FOREIGN KEY (`idUsuarioPublicante`) REFERENCES `usuarios` (`idUsuario`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`idPersonal`) REFERENCES `personal` (`idPersonal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
