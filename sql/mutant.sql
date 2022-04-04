-- --------------------------------------------------------
-- Host:                         db-aws-maria.ctvyerp4pkoc.us-east-1.rds.amazonaws.com
-- Versión del servidor:         10.4.13-MariaDB-log - Source distribution
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para mutant
CREATE DATABASE IF NOT EXISTS `mutant` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mutant`;

-- Volcando estructura para tabla mutant.dna
CREATE TABLE IF NOT EXISTS `dna` (
  `dna` longtext NOT NULL,
  `is_mutan` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`dna`(100))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mutant.dna: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `dna` DISABLE KEYS */;
INSERT IGNORE INTO `dna` (`dna`, `is_mutan`) VALUES
	('', 0),
	('AAGCGACAGTGCTTATGTAGAAGGCCCCTACCAAGA', 1),
	('ATGCGAAAGTGCTTATGTAGAAGGCCCCTACCAAGA', 1),
	('ATGCGACAGTGATTATGTAGAAGGCCCCTACCAAGA', 1),
	('ATGCGACAGTGCTTATGTAGAAGGCCCCTA', 0),
	('ATGCGACAGTGCTTATGTAGAAGGCCCCTACCAAGA', 1),
	('ATGCGACAGTGCTTATGTAGAAGGCCCCTAkCAAGA', 0),
	('ATGCGACAGTGCTTATGTAGAAGGCCCCTATCAAGA', 1),
	('TTGCGACAGTGATTATGGAGAAGGCCCCTACCAAGA', 1),
	('TTGCGACAGTGATTATGTAGAAGGCCCCTACCAAGA', 1);
/*!40000 ALTER TABLE `dna` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
