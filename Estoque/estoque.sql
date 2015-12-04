-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 19-Nov-2015 às 23:28
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `estoque`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `id_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `CPF` varchar(14) NOT NULL,
  `Cidade` varchar(45) NOT NULL,
  `Endereco` varchar(45) NOT NULL,
  `Numero` varchar(5) NOT NULL,
  `Bairro` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Cliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id_Cliente`, `Nome`, `CPF`, `Cidade`, `Endereco`, `Numero`, `Bairro`) VALUES
(1, 'Cliente 01', '111.111.111-11', 'Cidade 01', 'Endereço 01', '111', 'Bairro 01'),
(2, 'Cliente 02', '222.222.222-22', 'Cidade 02', 'Endereço 02', '222', 'Bairro'),
(3, 'Cliente 03', '333.333.333-33', 'Cidade 03', 'Endereço 03', '333', 'Bairro 03');

-- --------------------------------------------------------

--
-- Estrutura da tabela `comprar`
--

CREATE TABLE IF NOT EXISTS `comprar` (
  `Fk_Produto` int(11) NOT NULL,
  `Quantidade` int(11) NOT NULL,
  `Valor_Unidade` float NOT NULL,
  `Data` date NOT NULL,
  KEY `Fk_Id_Produto` (`Fk_Produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `comprar`
--

INSERT INTO `comprar` (`Fk_Produto`, `Quantidade`, `Valor_Unidade`, `Data`) VALUES
(1, 10, 5, '2015-11-19'),
(2, 20, 10, '2015-11-19'),
(3, 30, 15, '1111-11-11'),
(4, 40, 20, '2015-11-19');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE IF NOT EXISTS `estoque` (
  `id_produto` int(11) NOT NULL,
  `Quantidade` int(11) NOT NULL,
  KEY `id_produto` (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estoque`
--

INSERT INTO `estoque` (`id_produto`, `Quantidade`) VALUES
(1, 10),
(2, 20),
(3, 30),
(4, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `id_Fornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `Razao_Social` varchar(45) NOT NULL,
  `CNPJ` varchar(18) NOT NULL,
  `Cidade` varchar(45) NOT NULL,
  `Endereco` varchar(45) NOT NULL,
  `Numero` varchar(5) NOT NULL,
  `Bairro` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Fornecedor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`id_Fornecedor`, `Razao_Social`, `CNPJ`, `Cidade`, `Endereco`, `Numero`, `Bairro`) VALUES
(1, 'Empresa 01', '11.111.111/1111-11', 'Cidade 01', 'Endereço 01', '111', 'Bairro 01'),
(2, 'Empresa 02', '22.222.222/2222-22', 'Cidade 02', 'Endereço 02', '222', 'Bairro 02'),
(3, 'Empresa 03', '33.333.333/3333-33', 'Cidade 03', 'Endereço 03', '333', 'Bairro 03'),
(4, 'Empresa 04', '44.444.444/4444-44', 'Cidade 04', 'Endereço 04', '444', 'Bairro 04'),
(5, 'Empresa 05', '55.555.555/5555-55', 'Cidade 05', 'Endereço 05', '555', 'Bairro 04'),
(6, 'Empresa 06', '66.666.666/6666-66', 'Cidade 06', 'Endereço 06', '666', 'Bairro 06');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `id_Produto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Data` date NOT NULL,
  `fornecedor_id_Fornecedor` int(11) NOT NULL,
  PRIMARY KEY (`id_Produto`),
  KEY `fk_produto_fornecedor1_idx` (`fornecedor_id_Fornecedor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id_Produto`, `Nome`, `Data`, `fornecedor_id_Fornecedor`) VALUES
(1, 'Produto 01', '2015-11-19', 1),
(2, 'Produto 02', '2015-11-19', 2),
(3, 'Produto 03', '2015-11-19', 3),
(4, 'Produto 04', '2015-11-19', 4),
(5, 'Produto 05', '2015-11-19', 5),
(6, 'Produto 22', '2015-11-19', 2),
(7, 'Hoje', '2015-11-19', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vender`
--

CREATE TABLE IF NOT EXISTS `vender` (
  `Fk_Cliente` int(11) NOT NULL,
  `Fk_Produto` int(11) NOT NULL,
  `Quantidade` int(11) NOT NULL,
  `Valor_Unidade` float NOT NULL,
  `Desconto` float NOT NULL,
  `Data` date NOT NULL,
  KEY `Fk_Cliente` (`Fk_Cliente`),
  KEY `vender_ibfk_2` (`Fk_Produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vender`
--

INSERT INTO `vender` (`Fk_Cliente`, `Fk_Produto`, `Quantidade`, `Valor_Unidade`, `Desconto`, `Data`) VALUES
(1, 4, 10, 5, 5, '2015-11-19'),
(3, 4, 30, 10, 10, '2015-11-19');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `comprar`
--
ALTER TABLE `comprar`
  ADD CONSTRAINT `comprar_ibfk_3` FOREIGN KEY (`Fk_Produto`) REFERENCES `produto` (`id_Produto`);

--
-- Limitadores para a tabela `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`id_produto`) REFERENCES `comprar` (`Fk_Produto`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_produto_fornecedor1` FOREIGN KEY (`fornecedor_id_Fornecedor`) REFERENCES `fornecedor` (`id_Fornecedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `vender`
--
ALTER TABLE `vender`
  ADD CONSTRAINT `vender_ibfk_1` FOREIGN KEY (`Fk_Cliente`) REFERENCES `cliente` (`id_Cliente`),
  ADD CONSTRAINT `vender_ibfk_2` FOREIGN KEY (`Fk_Produto`) REFERENCES `estoque` (`id_produto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
