-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 29-07-2024 a las 15:13:13
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
-- Base de datos: `sahia2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Asistencia`
--

CREATE TABLE `Asistencia` (
  `id` int(11) NOT NULL,
  `dia_Inicio` datetime NOT NULL,
  `dia_Fin` datetime NOT NULL,
  `comentario` char(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Asistencia`
--

INSERT INTO `Asistencia` (`id`, `dia_Inicio`, `dia_Fin`, `comentario`, `usuario_id`) VALUES
(1, '2024-07-20 09:13:32', '2024-07-20 18:13:32', 'Consulta de pacientes', 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cita_Medica`
--

CREATE TABLE `Cita_Medica` (
  `id` int(11) NOT NULL,
  `motivo` char(255) NOT NULL,
  `numero` varchar(11) NOT NULL,
  `estado` int(1) NOT NULL,
  `horario_id` int(11) NOT NULL,
  `especialidad_id` int(11) NOT NULL,
  `paciente_id` int(11) DEFAULT NULL,
  `medico_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Cita_Medica`
--

INSERT INTO `Cita_Medica` (`id`, `motivo`, `numero`, `estado`, `horario_id`, `especialidad_id`, `paciente_id`, `medico_id`) VALUES
(24, 'Dolor de espalda agudo', 'GERI0000001', 0, 91, 7, 1, 4),
(25, 'Dolor de garganta agudo', 'GERI0000001', 0, 100, 7, 3, 4),
(26, 'Dolor abdominal', 'GERI0000001', 2, 1, 7, 3, 4),
(27, 'Dolor de cabeza y mareos fuerte', 'GERI0000002', 2, 1, 7, 3, 4),
(28, 'dolores articulares en manos', 'GERI0000001', 1, 92, 7, 1, 4),
(29, 'Dolor de cabeza y mareos', 'GERI0000001', 1, 93, 7, 1, 4),
(30, 'Dolor muscular y debilidad general', 'GERI0000002', 2, 1, 7, 3, 4),
(31, 'Dolor de talon derecho', 'GERI0000003', 0, 118, 7, 3, 4),
(34, 'Dolor abdominal severo', 'GERI0000002', 1, 95, 7, 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Consultorio`
--

CREATE TABLE `Consultorio` (
  `id` int(11) NOT NULL,
  `nombre` char(100) NOT NULL,
  `descripcion` char(255) NOT NULL,
  `ubicacion` char(100) NOT NULL,
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Consultorio`
--

INSERT INTO `Consultorio` (`id`, `nombre`, `descripcion`, `ubicacion`, `estado`) VALUES
(1, 'Consultorio 1', 'Color celeste, con juguetes de niños', 'Primera planta alta, a lado de secretaría principal', 0),
(2, 'Consultorio 2 - Cardiología', 'Area con mural de un corazón', 'Planta baja a mano izquierda, a lado de Farmacia', 2),
(3, 'Consultorio 3 - Medicina Familiar', 'Area con ventanas grandes y grises', 'Segunda planta alta, girando a la izquierda de la salida del ascensor', 0),
(4, 'Consultorio 4', 'Espacio con muebles color marrón', 'Segundo piso a mano derecha', 0),
(5, 'Consultorio 5', 'Espacio con muebles color grises', 'Segundo piso a mano izquierda', 1),
(6, 'Consultorio 6', 'Espacio con muebles color crema', 'Tercer piso a mano derecha', 1),
(7, 'Consultorio 7', 'Espacio con muebles color blanco', 'Tercer piso mano izquierda', 1),
(10, 'Consultorio 8', 'Paredes grises con fondo blanco', 'Tercer piso, a lado derecho del ascensor', 0),
(12, 'Consultorio final', 'PAREREDE FGRIES', 'TERCER PISO', 1),
(13, 'Consultorio1', 'hh', 'j', 1),
(14, 'Consultorio de pediatria', 'Area con juguetes de niño', 'tercera planta alta', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Consultorio_Medico`
--

CREATE TABLE `Consultorio_Medico` (
  `consultorio_id` int(11) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Consultorio_Medico`
--

INSERT INTO `Consultorio_Medico` (`consultorio_id`, `medico_id`, `fecha`) VALUES
(10, 4, '2024-07-28 19:22:51'),
(1, 14, '2024-07-26 13:32:38'),
(2, 15, '2024-07-26 14:15:29'),
(3, 15, '2024-07-26 14:16:21'),
(4, 16, '2024-07-28 13:39:01'),
(14, 17, '2024-07-28 18:44:03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Especialidad`
--

CREATE TABLE `Especialidad` (
  `id` int(11) NOT NULL,
  `descripcion` char(255) NOT NULL,
  `estado` int(1) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  `nombre` char(255) NOT NULL,
  `usuario_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Especialidad`
--

INSERT INTO `Especialidad` (`id`, `descripcion`, `estado`, `fecha_creacion`, `fecha_modificacion`, `nombre`, `usuario_id`) VALUES
(1, 'Cuidado médico de niños y adolescentes.', 0, '2024-06-01 00:00:00', '2024-07-27 11:17:09', 'Pediatría', 10),
(2, 'Diagnóstico y tratamiento de alergias y trastornos del sistema inmunológico.', 0, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Alergología e Inmunología', 1),
(3, 'Enfermedades del corazón y del sistema circulatorio.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Cardiología', 1),
(4, 'Enfermedades de la piel, cabello y uñas.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Dermatología', 1),
(5, 'Trastornos hormonales y del metabolismo.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Endocrinología', 1),
(6, 'Enfermedades del sistema digestivo.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Gastroenterología', 1),
(7, 'Atención médica a personas mayores.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Geriatría', 1),
(8, 'Enfermedades de la sangre y los órganos hematopoyéticos.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Hematología', 1),
(9, 'Atención integral a adultos.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Medicina Interna', 1),
(10, 'Atención médica integral a personas de todas las edades.', 0, '2024-07-01 00:00:00', '2024-07-27 11:42:24', 'Medicina Familiar', 10),
(11, 'Recuperación de funciones y calidad de vida tras enfermedades o lesiones.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Rehabilitación', 1),
(12, 'Enfermedades de los riñones.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Nefrología', 1),
(13, 'Enfermedades del sistema respiratorio.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Neumología', 1),
(14, 'Trastornos del sistema nervioso.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Neurología', 1),
(15, 'Salud reproductiva femenina y embarazo.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Obstetricia y Ginecología', 1),
(16, 'Enfermedades de los ojos.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Oftalmología', 1),
(17, 'Diagnóstico y tratamiento del cáncer.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Oncología', 1),
(18, 'Trastornos del sistema musculoesquelético.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Ortopedia y Traumatología', 1),
(19, 'Enfermedades de los oídos, nariz y garganta.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Otorrinolaringología', 1),
(20, 'Trastornos mentales y emocionales.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Psiquiatría', 1),
(21, 'Trastornos reumáticos y enfermedades autoinmunes.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Reumatología', 1),
(22, 'Enfermedades del sistema urinario y del aparato reproductor masculino.', 1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'Urología', 1),
(26, 'Problemas de la piel', 0, '2024-07-27 11:14:08', '2024-07-27 11:47:15', 'Dermatología', 10),
(27, 'HHHH', 2, '2024-07-28 16:54:24', '2024-07-28 16:54:32', 'PEDIATRIA 1', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Estadistica`
--

CREATE TABLE `Estadistica` (
  `id` int(11) NOT NULL,
  `citaMedica_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Factura`
--

CREATE TABLE `Factura` (
  `id` int(11) NOT NULL,
  `costoProcedimiento` double DEFAULT NULL,
  `direccionEmisor` char(255) NOT NULL,
  `direccionReceptor` char(255) NOT NULL,
  `honorariosBasico` double DEFAULT NULL,
  `identificacionEmisor` char(13) NOT NULL,
  `identificacionReceptor` char(13) NOT NULL,
  `nombreEmisor` char(255) NOT NULL,
  `nombreReceptor` char(255) NOT NULL,
  `descripcion` char(255) NOT NULL,
  `fecha` date NOT NULL,
  `formaPago` char(50) DEFAULT NULL,
  `citaMedica_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Historia_Medico`
--

CREATE TABLE `Historia_Medico` (
  `id` int(11) NOT NULL,
  `diagnostico` varchar(255) NOT NULL,
  `examenes` char(255) NOT NULL,
  `proximo_control` int(11) NOT NULL,
  `cita_medica_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Historia_Medico`
--

INSERT INTO `Historia_Medico` (`id`, `diagnostico`, `examenes`, `proximo_control`, `cita_medica_id`) VALUES
(7, 'Amigdalitis aguda', 'Examenes en ayunas', 90, 25),
(8, 'hernia discal', 'radiografía de toda la espalda', 94, 24),
(9, 'DOLOR ', 'radiografía', 110, 31);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Horario`
--

CREATE TABLE `Horario` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `tipo` char(50) DEFAULT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Horario`
--

INSERT INTO `Horario` (`id`, `fecha`, `hora`, `tipo`, `estado`) VALUES
(1, '2000-07-01', '00:57:06', 'CANCELADO', 2),
(90, '2024-07-29', '09:00:00', 'MATUTINO', 0),
(91, '2024-07-29', '09:30:00', 'MATUTINO', 0),
(92, '2024-07-29', '10:00:00', 'MATUTINO', 0),
(93, '2024-07-29', '10:30:00', 'MATUTINO', 0),
(94, '2024-07-29', '11:00:00', 'MATUTINO', 0),
(95, '2024-07-29', '11:30:00', 'MATUTINO', 0),
(96, '2024-07-29', '16:00:00', 'VESPERTINO', 1),
(97, '2024-07-29', '16:30:00', 'VESPERTINO', 1),
(98, '2024-07-29', '17:00:00', 'VESPERTINO', 1),
(99, '2024-07-29', '17:30:00', 'VESPERTINO', 1),
(100, '2024-07-27', '09:00:00', 'MATUTINO', 0),
(101, '2024-07-27', '09:30:00', 'MATUTINO', 1),
(102, '2024-07-27', '10:00:00', 'MATUTINO', 1),
(103, '2024-07-27', '10:30:00', 'MATUTINO', 1),
(104, '2024-07-27', '11:00:00', 'MATUTINO', 1),
(105, '2024-07-27', '11:30:00', 'MATUTINO', 1),
(106, '2024-07-27', '16:00:00', 'VESPERTINO', 1),
(107, '2024-07-27', '16:30:00', 'VESPERTINO', 1),
(108, '2024-07-27', '17:00:00', 'VESPERTINO', 1),
(109, '2024-07-27', '17:30:00', 'VESPERTINO', 1),
(110, '2024-07-28', '09:00:00', 'MATUTINO', 0),
(111, '2024-07-28', '09:30:00', 'MATUTINO', 1),
(112, '2024-07-28', '10:00:00', 'MATUTINO', 1),
(113, '2024-07-28', '10:30:00', 'MATUTINO', 1),
(114, '2024-07-28', '11:00:00', 'MATUTINO', 1),
(115, '2024-07-28', '11:30:00', 'MATUTINO', 1),
(116, '2024-07-28', '16:00:00', 'VESPERTINO', 1),
(117, '2024-07-28', '16:30:00', 'VESPERTINO', 1),
(118, '2024-07-28', '17:00:00', 'VESPERTINO', 0),
(119, '2024-07-28', '17:30:00', 'VESPERTINO', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Medicamento`
--

CREATE TABLE `Medicamento` (
  `id` int(11) NOT NULL,
  `cantidad_Disponible` int(11) NOT NULL,
  `fecha_Vencimiento` date DEFAULT NULL,
  `nombre` char(255) NOT NULL,
  `presentacion` char(255) DEFAULT NULL,
  `via_Administracion` char(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Medicamento`
--

INSERT INTO `Medicamento` (`id`, `cantidad_Disponible`, `fecha_Vencimiento`, `nombre`, `presentacion`, `via_Administracion`) VALUES
(1, 10, '2025-02-11', 'Paracetamol', 'Tabletas', 'Oral'),
(2, 30, '2025-02-06', 'Ibuprofeno', 'Cápsulas', 'Oral');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Medicamento_Receta`
--

CREATE TABLE `Medicamento_Receta` (
  `medicamento_id` int(11) NOT NULL,
  `receta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Medico`
--

CREATE TABLE `Medico` (
  `id` int(11) NOT NULL,
  `persona_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Medico`
--

INSERT INTO `Medico` (`id`, `persona_id`) VALUES
(4, 15),
(2, 26),
(3, 27),
(6, 32),
(11, 37),
(12, 38),
(13, 39),
(14, 40),
(15, 41),
(16, 46),
(17, 49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Medico_Especialidad`
--

CREATE TABLE `Medico_Especialidad` (
  `medico_id` int(11) NOT NULL,
  `especialidad_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Medico_Especialidad`
--

INSERT INTO `Medico_Especialidad` (`medico_id`, `especialidad_id`) VALUES
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 7),
(16, 7),
(17, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Medico_Horario`
--

CREATE TABLE `Medico_Horario` (
  `medico_id` int(11) NOT NULL,
  `horario_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Medico_Horario`
--

INSERT INTO `Medico_Horario` (`medico_id`, `horario_id`) VALUES
(4, 90),
(4, 91),
(4, 92),
(4, 93),
(4, 94),
(4, 95),
(4, 96),
(4, 97),
(4, 98),
(4, 99),
(4, 100),
(4, 101),
(4, 102),
(4, 103),
(4, 104),
(4, 105),
(4, 106),
(4, 107),
(4, 108),
(4, 109),
(4, 110),
(4, 111),
(4, 112),
(4, 113),
(4, 114),
(4, 115),
(4, 116),
(4, 117),
(4, 118),
(4, 119);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Paciente`
--

CREATE TABLE `Paciente` (
  `id` int(11) NOT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `edad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Paciente`
--

INSERT INTO `Paciente` (`id`, `persona_id`, `edad`) VALUES
(1, 1, 29),
(2, 3, 34),
(3, 6, 56),
(4, 42, 24),
(5, 43, 29),
(6, 45, 44),
(7, 47, 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Persona`
--

CREATE TABLE `Persona` (
  `id` int(11) NOT NULL,
  `identificacion` char(13) NOT NULL,
  `apellidos` char(255) NOT NULL,
  `ciudad_residencia` char(30) NOT NULL,
  `direccion` char(255) NOT NULL,
  `correo` char(255) DEFAULT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nombres` char(255) NOT NULL,
  `genero` char(10) NOT NULL,
  `telefono` char(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Persona`
--

INSERT INTO `Persona` (`id`, `identificacion`, `apellidos`, `ciudad_residencia`, `direccion`, `correo`, `fecha_nacimiento`, `nombres`, `genero`, `telefono`) VALUES
(1, '1105102543', 'JARAMILLO ROSALES', 'LOJA', 'GONZANAMA Y SUCRE', 'rcjaramillo2@utpl.edu.ec', '2000-04-03', 'ROCÍO DEL CARMEN', 'FEMENINO', '0987654321'),
(2, '1105102550', 'JARAMILLO CONDE', 'LOJA DANIEL ALVAREZ', '18 DE NOVIEMBRE Y GONZANAMÁ Y EDUARDO', 'gigi2@gmail.com', '2020-10-02', 'GIGI RAQUEL', 'FEMENINO', '0987123456'),
(3, '1105102548', 'TORRES', 'loja', 'rocafuerte', 'ctorres@gmail.com', '1995-07-13', 'CARLOS', 'MASCULINO', '987654322'),
(4, '1109654321', 'CHIO', 'loja', 'roca chio', 'cchio@gmail.com', '1995-07-13', 'JUAN', 'FEMENINO', '987654322'),
(5, '1105102500', 'NOVILLO', 'loja', 'rocafuerte y sucre', 'mnovillo@gmail.com', '1995-07-20', 'MAXILLO', 'MASCULINO', '987654321'),
(6, '1109543234', 'GRANDA', 'LOJA', 'rocafuerte y sucre', 'cuchi@gmail.com', '1995-07-19', 'CUCHI', 'MASCULINO', '987655656'),
(7, '1192929292', 'LEON', 'SAN GERARDO', 'LOJITA', 'aleon@gmail.com', '1995-07-20', 'ALEX', 'MASCULINO', '987656565'),
(8, '1107654321', 'TORRES', 'LOJA', 'SANTA CLARA', 'rtorres@gmail.com', '1980-07-18', 'RAMIRO', 'MASCULINO', '987654321'),
(9, '11065432145', 'SAMANIEGO', 'ZAMORA', 'FAIQUES Y CEDROS', 'rsamaniego@gmail.com', '1995-12-16', 'RAFAEL', 'MASCULINO', '987676767'),
(10, '1111111111', 'DOS', 'LOJA', 'SUCRE', 'ud@gmail.com', '1998-07-12', 'UNO', 'MASCULINO', '989898989'),
(11, '1111111111', 'DOS', 'LOJA', 'SUCRE', 'ud@gmail.com', '1998-07-13', 'UNO', 'MASCULINO', '898989898'),
(12, '1111111111', 'DOS', 'LOJA', 'SUCRE', 'ud@gmail.com', '1998-07-12', 'UNO', 'MASCULINO', '989898989'),
(13, '1212121212', 'GUAMAN', 'LOJA', 'AMABLE MARIA', 'dguaman@gmail.com', '1998-07-12', 'DAVID', 'MASCULINO', '0988989898'),
(14, '1313131313', 'JARAMILLO', 'SANTA CLARA,PALANDA', 'AV. LA CANELA,SANTA CLARA', 'ejaramillo@gmail.com,rjaramiLLO@GMAIL.COM', '1955-07-11', 'ELICEO', 'MASCULINO', '919191919'),
(15, '1414141414', 'ROSALES', 'SANTA CLARA', 'AV LA CANELA', 'mrosales@gmail.com', '1960-07-12', 'MARINA', 'FEMENINO', '959595959'),
(16, '1188888888', 'CASTILLO', 'ZAMORA CHINCHIPE', 'ZOILO RODRIGUEZ Y MIGUEL ESPARZA', 'scastillo@gmail.com', '1985-07-13', 'SANTIAJO', 'MASCULINO', '977797779'),
(17, '1777777777777', 'FLORES', 'PALANDA', 'AV. DEL MAESTRO Y SUCRE', 'rflores@gmail.com', '1999-07-21', 'RITA', 'FEMENINO', '987654321'),
(18, '1145454545454', 'PALACIOS', 'LOJA', 'QUITO Y 24 DE MAYO', 'rpalacios@gmail.com', '1995-07-26', 'RICARDO', 'MASCULINO', '0989765432'),
(19, '1106534233', 'CONTRERAS', 'LOJA', 'CIPRES Y ROBLES', 'contreras@gmail.com', '1998-07-19', 'RUBEN', 'OTRO', '0987654321'),
(20, 'JJJ', 'JJJ', 'JJJ', 'JJJ', 'jj@gmail.com', '2024-07-12', 'JJJ', 'MASCULINO', '0988888888'),
(21, 'JJJJ', 'KKK', 'KKK', 'KK', 'kk@gmail.com', '2024-07-18', 'JJJ', 'FEMENINO', '0988888888'),
(22, '1109988888888', 'PALACIOS', 'LOJA', 'SUCRE Y CARIAMANGA', 'jpalacios@gmail.com', '2024-07-20', 'JUAN', 'MASCULINO', '0988888888'),
(23, '0712345678', 'JARAMILLO', 'ZAMORA CHINCHIPE', 'CANELA Y SANTA CLARA', 'bjaramillo@gmail.com', '2010-07-17', 'BLADIMIR', 'MASCULINO', '0987654321'),
(24, '1212121212', 'JAIMITO', 'LOJA', 'ROCAFUERTE Y SUCRE', 'japellido@gmail.com', '1997-07-13', 'JAIMITO', 'MASCULINO', '0987676767'),
(25, '1212121122', 'JJJJ', 'JJJJ', 'JJJJ', 'hh@gmail.com', '2000-07-05', 'ANTHONY', 'MASCULINO', '0988888888'),
(26, '1313131331313', 'TACURI PEREZ', ' LOJA', 'SUCRE Y ROCAFUERTE', 'atacuri@gmail.com', '2001-07-13', 'AMANDA XIMENA', 'MASCULINO', '0987878787'),
(27, '1192929292', 'JIMENEZ RAMIREZ', 'SANTO DOMINGO', 'JAIME ROLDOS Y JULIAN AROSEMENA', 'rjimenez@gmail.com', '1997-07-19', 'RICARDO DAVID', 'MASCULINO', '0987654321'),
(28, '1104950454', 'NOVILLO CRIOLLO', 'LOJA', 'SAUCES NORTE', 'mnovillo@gmail.com', '1998-07-14', 'MAX EMILIANO', 'MASCULINO', '0987654321'),
(29, '11056666666', 'PAREDES', 'LOJA', 'ROCAFUERTE Y SUCRE', 'jparedes@gmail.com', '1997-07-19', 'JUANITO', 'MASCULINO', '0987654321'),
(30, '1105151515151', 'PANCHIN', 'LOJA NUEVA', 'ROCAFUERTE Y BOLIVAR', 'ppanchin@gmail.com', '2024-07-19', 'PANCHO', 'MASCULINO', '0987654321'),
(31, '1144444444', 'CONDE PEÑA', 'LOJA', 'MIGUEL RIOFRIO Y SUCRE', 'jconde@gmail.com', '1980-07-27', 'JOAQUINA MARIBEL', 'FEMENINO', '0987654334'),
(32, '1133333333', 'GARRIDO FLORES', 'LOJA', 'GONZANAMA Y 18 DE NOVIEMBRE', 'ggarrido@gmail.com', '2024-07-19', 'GABRIEL JOAQUIN', 'FEMENINO', '0989898989'),
(33, '1100000000', 'LUDEÑA', 'LOJA', 'SUCRE Y ROCAFUERTE', 'jludena@gmail.com', '1996-07-11', 'JAVIER', 'MASCULINO', '0989888888'),
(34, '1177777777', 'VALLEJO', 'LOJA', 'SUCRE ROCAFUERTE', 'mvallejo@gmail.com', '1997-07-19', 'MANUELITO', 'MASCULINO', '0975757575'),
(35, '1190909090', 'PEREZ', 'LOJA', 'MIGUEL RIOFRIO Y AV UNIVERSITARIA', 'rperez@gmail.com', '1993-07-20', 'RUBEN CLEMENCIO', 'MASCULINO', '0943434343'),
(36, '1100000001', 'MAZA CARDENAS', 'LOJA ECUADOR', 'ROCAFUERTE Y 18 DE NOVIEMBRE', 'lmaza@gmail.com', '1986-07-21', 'LIZETH', 'FEMENINO', '0932323233'),
(37, '1105102676', 'DIAZ', 'LOJA', 'ROCAFUERTE Y SUCRE', 'ddiaz@gmail.com', '1980-07-12', 'DIANA', 'FEMENINO', '0923456789'),
(38, '1162626262626', 'ESPINOSA', 'LOJA', 'ROCAFUERTE Y MORELOS', 'mespinosa@gmail.com', '1986-07-12', 'MARIA JOSE', 'FEMENINO', '0953525252'),
(39, '1106734242222', 'CEVALLOS MONTERO', 'LOJA', 'SUCRE Y KINGMAN', 'jcevallos@gamil.com', '1998-08-30', 'JOSUE', 'MASCULINO', '0987342424'),
(40, '1173737373773', 'DIAZ', 'LOJA', 'ROCAFUERTE Y DANIEL ALVAREZ', 'jdiaz@gmail.com', '1991-07-10', 'JHOAN', 'MASCULINO', '0934342441'),
(41, '1524352525525', 'MARTINEZ', 'LOJA', 'SUCRE Y 18', 'cmartinez@gmail.com', '2024-07-24', 'CARLOS', 'MASCULINO', '0871324354'),
(42, '1515151551515', 'CABRERA', 'LOJA', 'SUCRE Y ROCAFUERTE', 'pcabrera@gmail.com', '2000-07-10', 'PEDRO', 'MASCULINO', '0952314768'),
(43, '1145245445454', 'TORRES', 'LOJA', 'JAIME ROLDOS Y MARCELINO', 'jftorres@gmail.com', '1995-07-03', 'JENNY FERNANDA', 'FEMENINO', '0982345677'),
(44, '1234156262', 'SARANGO TACURI', 'LOJA', 'ROCAFUERTE Y SUCRE', 'jmsarango@gmail.com', '1998-07-13', 'JUAN MANUEL', 'MASCULINO', '0987654321'),
(45, '1781788888', 'ROLDOS AGUILERA', 'LOJA ECUADOR', 'ROCAFUERTE Y SUCRE', 'jaime@gmail.com', '1980-07-21', 'JAIME', 'MASCULINO', '0984535355'),
(46, '1924553535535', 'MONTERO SALCEDO', 'LOJA ECUADOR', 'SUCRE Y ROCAFUERTE', 'rmontero@gmail.com', '1987-07-13', 'DR RAFAEL', 'MASCULINO', '0976432111'),
(47, '1231231231', 'LOPEZ', 'LOJA', 'ROCFUERTE Y SUCRE', 'gigi@gmail.com', '1990-07-06', 'PACIENTE GIGI', 'FEMENINO', '0987654231'),
(48, '1109877777', 'VALVERDE SOTOMAYOR', 'LOJA ECUADOR', 'SUCRE Y GONZANAMA', 'lvalverde@gmail.com', '1980-07-12', 'LUIS DAMIAN', 'MASCULINO', '0987455545'),
(49, '1134567899999', 'PALADINES', 'LOJA ECUADOR', 'ROCAFUERTE ENTRE SUVRE', 'epaladines@gmail.com', '1980-07-13', 'DR ENRIQUE', 'MASCULINO', '0988764664');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Receta`
--

CREATE TABLE `Receta` (
  `id` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `indicaciones` char(255) NOT NULL,
  `duracion_Tratamiento` char(30) NOT NULL,
  `frecuencia_Tratamiento` char(30) NOT NULL,
  `cita_medica_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Receta`
--

INSERT INTO `Receta` (`id`, `fecha`, `indicaciones`, `duracion_Tratamiento`, `frecuencia_Tratamiento`, `cita_medica_id`) VALUES
(15, '2024-07-28', 'Tomar paracetamol', 'dos días', 'cada 8 horas', 25),
(16, '2024-07-28', 'Mantener reposo por 2 semanas, dormir con una almohada de soporte.\nTomar ibuprofeno', '3 días', 'cada 2 horas', 24),
(17, '2024-07-28', 'Tomar ibuprofeno y hacer terapia', '2 semanas', 'capsula cada 8 horas', 31);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reporte`
--

CREATE TABLE `Reporte` (
  `id` int(11) NOT NULL,
  `descripcion` char(255) NOT NULL,
  `fechaGeneracion` date NOT NULL,
  `citaMedica_id` int(11) DEFAULT NULL,
  `asistencia_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Rol`
--

CREATE TABLE `Rol` (
  `id` int(11) NOT NULL,
  `nombre` char(25) NOT NULL,
  `tipo_permiso` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Rol`
--

INSERT INTO `Rol` (`id`, `nombre`, `tipo_permiso`) VALUES
(1, 'ADMIN', 'ALL'),
(2, 'USER', 'TECNICO'),
(3, 'MEDICO', 'ALL'),
(4, 'PACIENTE', 'RESTRINGIDO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `id` int(11) NOT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `password` char(100) NOT NULL,
  `username` char(100) NOT NULL,
  `enabled` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`id`, `persona_id`, `password`, `username`, `enabled`) VALUES
(1, 1, '$2a$10$RB6rOUwusgKiqeHvseJuXO4cNMpVM0PpX3bqC4b9gTtljJfp5BiIe', 'rochio', 1),
(3, 2, '$2a$10$nwfhwNXZgOFeZMew1IxRzu.z7/j6RLR07v64kxLvmJulZu4HzeLI2', 'gigi', 1),
(6, 12, '$2a$10$XBCecoTNOzyVeVKxHXQ8t.2KrQpi8DIrnGU9g.xWiAA51GaEOoFcy', 'juanito', 1),
(7, 13, 'david', 'david', 0),
(8, 14, '$2a$10$1qeWHr0XkEp8UlgcbFHmIuwAx2.OaUBM6SQ1qSmSIoRZftIebqkJa', 'eliceo,liceo', 1),
(9, 15, '$2a$10$RB6rOUwusgKiqeHvseJuXO4cNMpVM0PpX3bqC4b9gTtljJfp5BiIe', 'marina', 1),
(10, 16, '$2a$10$/A/KDVmHQ4eXN.g0.eG/6uDtbiFW2NI6wiz/pXuuvrvypNet4xlPS', 'santa', 1),
(11, 17, '$2a$10$RB6rOUwusgKiqeHvseJuXO4cNMpVM0PpX3bqC4b9gTtljJfp5BiIe', 'rita', 1),
(12, 18, '$2a$10$0cVA01GJqfUXY9OqXEGXW./yrtartRdMRZbg/XgkoSo1WdOjgxi2C', 'richar', 1),
(14, 20, '$2a$10$w7.hP4uXr6cH57U6wFr2Ou5cmwhbQMrY7I6DXHFxFbvPlW3.Zj42K', 'jjjj', 1),
(16, 22, '$2a$10$bCM588CjPF5X5LSnw345he8eN9jE6iBGWeEtdwecGKoKi3dldps1O', 'juan', 1),
(17, 23, '$2a$10$dgn7iPxNuZAR/E9klXbRqONQ5bdAU/H5ghkZkRQDfyblRtj8EbWja', 'bladi', 1),
(18, 24, '$2a$10$OMr3NUKlT4XO8dqlbWLs0efTHcTWKni3aGbO4HQ6rGN72sdAl4yfK', 'jaimito', 1),
(19, 25, '$2a$10$irhHsdVx9/8ZROJNRfLQKuAG60R9iKjt11GY3vQQt.FVEiLBsMbl6', 'tony', 1),
(20, 26, '$2a$10$IjekN5TEYYypaXxIN.IEzubeeoaRdv/LYtQPu5X60S8hRC7olVinq', 'amanda', 1),
(21, 27, '$2a$10$sPmSNu2y2y1rl3SeT6GpC.mdwdokrof.8XFuzMBrFx.U9yr691iqm', 'ricardito', 1),
(22, 28, '$2a$10$ComxLmpwSYBLP5eJRBNiA.ePvAXzY2Og6K9SqFeVI6ru13FvmSZyO', 'maxito', 1),
(23, 29, '$2a$10$6B/6m7OrT6OZWok7PVmc..ux5kGnIXFGsa7n2.yOa82e1SEZIBh8y', 'juanito2', 1),
(24, 30, '$2a$10$h7WwAKfL7ywwwH8AbED2COMfIU6iqu22g9Pcv25J.mf4Vtj9DPSQ.', 'pancho', 1),
(25, 6, '$2a$10$RB6rOUwusgKiqeHvseJuXO4cNMpVM0PpX3bqC4b9gTtljJfp5BiIe', 'cuchi', 1),
(26, 31, '$2a$10$g834nwpV41QxP3SjX3EL3OjcOtSxn3gSAa50dwCbbExf4PaUVnDBq', 'joaquina', 1),
(27, 32, '$2a$10$uAkAXfNrx9rSp4fTET/R9.In6Z01Ep9BAaCcQX4Eop1BKvf9mnq7O', 'gabriel', 1),
(28, 33, '$2a$10$rcv0RxkIB6gBez/C5qVQEuO5zZ8aKmPsbyYt9gugKfdWV.zfmoPhG', 'javier', 1),
(29, 34, '$2a$10$4v.dAfFkmwFuo.tFhH/5n.Zf3wsp25DMZV4Mb4oLTN2a0IQ2OLKLi', 'manuelino', 1),
(30, 35, '$2a$10$B0Ip48YbuHO/FHRhVvpNE.QX2VK66NGTp6xQrbnLAqR33BACTo3j.', 'ruben', 1),
(31, 36, '$2a$10$TMiX8ZIpisVfzRn1cBU5/OB8QGNVIkajk4fm.jR.QBQXjZPm0LmxS', 'lizeth', 1),
(32, 37, '$2a$10$o.5C8ea9BtwkP6ntPTf5zO2Nwx1aSp3iJbVU0BMOHe1ThHBsmF/F2', 'dianita', 1),
(33, 38, '$2a$10$YlcVAt2d/HgXO84p0eNPru7CbKXQE63vCa/Ly5k2dtjvfywcw7AaG', 'bajito', 1),
(34, 39, '$2a$10$7L7ZfxnBWsYnzBw.errZVOuLWoxuake7RrxthvvVHC4eyW6n4XxZ.', 'josue', 1),
(35, 40, '$2a$10$0QGEvsgTc6/BqNm3Z7p9IeTnuQT8zUm2OYiy5/Mpi97b6iUfvjXX.', 'jhoan', 1),
(36, 41, '$2a$10$piBw8azrchjy9MPf6gZkrOlyW1nz4k7Ebg/tcLxCyAZYfwEftWuru', 'carlitosm', 1),
(37, 42, '$2a$10$e8FoLtBUM8awoPOIdePo9OZW1fDPulHeN.acFsPCpZl4OBm.HovB6', 'pepito', 1),
(38, 43, '$2a$10$KdXiW.OI53lXgXJYUpy31.IbTp3l.kZF0ePN7Aa8U2agCwQfD6igq', 'jenny', 0),
(39, 44, '$2a$10$YLAv8JIzlBRe/.vT9z6XwOgFbd4k5LNNaN267j4Z/TFEonsLuPd/C', 'roció', 1),
(40, 45, '$2a$10$P1vGMuHdH/Gl9JSn9.Y5UOmSxMsSOWJWNZNQSfAtNgcz7uZ9UdpNq', 'jaime', 1),
(41, 46, '$2a$10$qPoggHELGkbBK8IMfmYfs..wIXXtoVbTVk0QxdrfOwnLhGA6hmUHi', 'rafita', 1),
(42, 47, '$2a$10$r80Em419JoapAWJKwoFB6uz4CIRlkAsEgUK6SQ14L3pH5CZHtyicG', 'gigi2', 1),
(43, 48, '$2a$10$0ftggXG0nFCvoGb1SmTNheEHqnMmcPXGMIy9NmURxKygiHOtQICRi', 'luchio2', 1),
(44, 49, '$2a$10$Di/xEPthxGnfhMnV5yqD0.yraxDTSMwMHCcJABjPYSD0M849p5OgC', 'enrique', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario_Rol`
--

CREATE TABLE `Usuario_Rol` (
  `usuario_id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Usuario_Rol`
--

INSERT INTO `Usuario_Rol` (`usuario_id`, `rol_id`) VALUES
(1, 1),
(3, 2),
(6, 2),
(7, 1),
(8, 1),
(9, 3),
(10, 1),
(11, 1),
(12, 2),
(14, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 3),
(21, 3),
(22, 1),
(23, 1),
(24, 1),
(25, 4),
(26, 3),
(27, 3),
(28, 3),
(29, 3),
(30, 3),
(31, 3),
(32, 3),
(33, 3),
(34, 3),
(35, 3),
(36, 3),
(37, 4),
(38, 4),
(39, 1),
(40, 4),
(41, 3),
(42, 4),
(43, 1),
(44, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Asistencia`
--
ALTER TABLE `Asistencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `Cita_Medica`
--
ALTER TABLE `Cita_Medica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `medico_id` (`medico_id`),
  ADD KEY `paciente_id` (`paciente_id`),
  ADD KEY `especialidad_id` (`especialidad_id`),
  ADD KEY `horario_id` (`horario_id`);

--
-- Indices de la tabla `Consultorio`
--
ALTER TABLE `Consultorio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Consultorio_Medico`
--
ALTER TABLE `Consultorio_Medico`
  ADD PRIMARY KEY (`medico_id`,`consultorio_id`),
  ADD KEY `consultorio_id` (`consultorio_id`);

--
-- Indices de la tabla `Especialidad`
--
ALTER TABLE `Especialidad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `Estadistica`
--
ALTER TABLE `Estadistica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `citaMedica_id` (`citaMedica_id`);

--
-- Indices de la tabla `Factura`
--
ALTER TABLE `Factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `citaMedica_id` (`citaMedica_id`);

--
-- Indices de la tabla `Historia_Medico`
--
ALTER TABLE `Historia_Medico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `proximo_control` (`proximo_control`),
  ADD KEY `cita_medica_id` (`cita_medica_id`);

--
-- Indices de la tabla `Horario`
--
ALTER TABLE `Horario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Medicamento`
--
ALTER TABLE `Medicamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Medicamento_Receta`
--
ALTER TABLE `Medicamento_Receta`
  ADD PRIMARY KEY (`medicamento_id`,`receta_id`),
  ADD KEY `receta_id` (`receta_id`),
  ADD KEY `medicamento_id` (`medicamento_id`);

--
-- Indices de la tabla `Medico`
--
ALTER TABLE `Medico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `Medico_Especialidad`
--
ALTER TABLE `Medico_Especialidad`
  ADD PRIMARY KEY (`medico_id`,`especialidad_id`),
  ADD KEY `especialidad_id` (`especialidad_id`),
  ADD KEY `medico_id` (`medico_id`);

--
-- Indices de la tabla `Medico_Horario`
--
ALTER TABLE `Medico_Horario`
  ADD PRIMARY KEY (`medico_id`,`horario_id`),
  ADD KEY `horario_id` (`horario_id`),
  ADD KEY `medico_id` (`medico_id`);

--
-- Indices de la tabla `Paciente`
--
ALTER TABLE `Paciente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `Persona`
--
ALTER TABLE `Persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Receta`
--
ALTER TABLE `Receta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cita_medica_id` (`cita_medica_id`);

--
-- Indices de la tabla `Reporte`
--
ALTER TABLE `Reporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `citaMedica_id` (`citaMedica_id`),
  ADD KEY `asistencia_id` (`asistencia_id`);

--
-- Indices de la tabla `Rol`
--
ALTER TABLE `Rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `Usuario_Rol`
--
ALTER TABLE `Usuario_Rol`
  ADD PRIMARY KEY (`usuario_id`,`rol_id`),
  ADD KEY `rol_id` (`rol_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Asistencia`
--
ALTER TABLE `Asistencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `Cita_Medica`
--
ALTER TABLE `Cita_Medica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `Consultorio`
--
ALTER TABLE `Consultorio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `Especialidad`
--
ALTER TABLE `Especialidad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `Estadistica`
--
ALTER TABLE `Estadistica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Factura`
--
ALTER TABLE `Factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Historia_Medico`
--
ALTER TABLE `Historia_Medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `Horario`
--
ALTER TABLE `Horario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT de la tabla `Medicamento`
--
ALTER TABLE `Medicamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `Medico`
--
ALTER TABLE `Medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `Paciente`
--
ALTER TABLE `Paciente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `Persona`
--
ALTER TABLE `Persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `Receta`
--
ALTER TABLE `Receta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `Reporte`
--
ALTER TABLE `Reporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Rol`
--
ALTER TABLE `Rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Asistencia`
--
ALTER TABLE `Asistencia`
  ADD CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`);

--
-- Filtros para la tabla `Cita_Medica`
--
ALTER TABLE `Cita_Medica`
  ADD CONSTRAINT `cita_medica_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `Paciente` (`id`),
  ADD CONSTRAINT `cita_medica_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `Medico` (`id`),
  ADD CONSTRAINT `cita_medica_ibfk_3` FOREIGN KEY (`especialidad_id`) REFERENCES `Especialidad` (`id`),
  ADD CONSTRAINT `cita_medica_ibfk_4` FOREIGN KEY (`horario_id`) REFERENCES `Horario` (`id`);

--
-- Filtros para la tabla `Consultorio_Medico`
--
ALTER TABLE `Consultorio_Medico`
  ADD CONSTRAINT `consultorio_medico_ibfk_1` FOREIGN KEY (`consultorio_id`) REFERENCES `Consultorio` (`id`),
  ADD CONSTRAINT `consultorio_medico_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `Medico` (`id`);

--
-- Filtros para la tabla `Especialidad`
--
ALTER TABLE `Especialidad`
  ADD CONSTRAINT `especialidad_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`);

--
-- Filtros para la tabla `Estadistica`
--
ALTER TABLE `Estadistica`
  ADD CONSTRAINT `estadistica_ibfk_1` FOREIGN KEY (`citaMedica_id`) REFERENCES `Cita_Medica` (`id`);

--
-- Filtros para la tabla `Factura`
--
ALTER TABLE `Factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`citaMedica_id`) REFERENCES `Cita_Medica` (`id`);

--
-- Filtros para la tabla `Historia_Medico`
--
ALTER TABLE `Historia_Medico`
  ADD CONSTRAINT `historia_medico_ibfk_1` FOREIGN KEY (`cita_medica_id`) REFERENCES `Cita_Medica` (`id`),
  ADD CONSTRAINT `historia_medico_ibfk_2` FOREIGN KEY (`proximo_control`) REFERENCES `Horario` (`id`);

--
-- Filtros para la tabla `Medicamento_Receta`
--
ALTER TABLE `Medicamento_Receta`
  ADD CONSTRAINT `medicamento_receta_ibfk_1` FOREIGN KEY (`medicamento_id`) REFERENCES `Medicamento` (`id`),
  ADD CONSTRAINT `medicamento_receta_ibfk_2` FOREIGN KEY (`receta_id`) REFERENCES `Receta` (`id`);

--
-- Filtros para la tabla `Medico`
--
ALTER TABLE `Medico`
  ADD CONSTRAINT `medico_ibfk_1` FOREIGN KEY (`persona_id`) REFERENCES `Persona` (`id`);

--
-- Filtros para la tabla `Medico_Especialidad`
--
ALTER TABLE `Medico_Especialidad`
  ADD CONSTRAINT `medico_especialidad_ibfk_1` FOREIGN KEY (`medico_id`) REFERENCES `Medico` (`id`),
  ADD CONSTRAINT `medico_especialidad_ibfk_2` FOREIGN KEY (`especialidad_id`) REFERENCES `Especialidad` (`id`);

--
-- Filtros para la tabla `Medico_Horario`
--
ALTER TABLE `Medico_Horario`
  ADD CONSTRAINT `medico_horario_ibfk_1` FOREIGN KEY (`medico_id`) REFERENCES `Medico` (`id`),
  ADD CONSTRAINT `medico_horario_ibfk_2` FOREIGN KEY (`horario_id`) REFERENCES `Horario` (`id`);

--
-- Filtros para la tabla `Paciente`
--
ALTER TABLE `Paciente`
  ADD CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`persona_id`) REFERENCES `Persona` (`id`);

--
-- Filtros para la tabla `Receta`
--
ALTER TABLE `Receta`
  ADD CONSTRAINT `receta_ibfk_1` FOREIGN KEY (`cita_medica_id`) REFERENCES `Cita_Medica` (`id`);

--
-- Filtros para la tabla `Reporte`
--
ALTER TABLE `Reporte`
  ADD CONSTRAINT `reporte_ibfk_1` FOREIGN KEY (`citaMedica_id`) REFERENCES `Cita_Medica` (`id`),
  ADD CONSTRAINT `reporte_ibfk_2` FOREIGN KEY (`asistencia_id`) REFERENCES `Asistencia` (`id`);

--
-- Filtros para la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`persona_id`) REFERENCES `Persona` (`id`);

--
-- Filtros para la tabla `Usuario_Rol`
--
ALTER TABLE `Usuario_Rol`
  ADD CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario` (`id`),
  ADD CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`rol_id`) REFERENCES `Rol` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
