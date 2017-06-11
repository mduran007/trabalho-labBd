/**
 * @author JESSICA CARNEIRO BATISTA
 * Matéria Laboratorio de Engenharia
 * FATEC ZL 5º ADS - Tarde
 * 11/11/2016
 */

DROP DATABASE IF EXISTS ael;

CREATE DATABASE ael;

USE ael;


CREATE TABLE `cliente` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(300) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `endereco` VARCHAR(30) NOT NULL
) ENGINE = innodb;


CREATE TABLE `emprestimo` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `idCliente` INT(11) NOT NULL,
  `idLivro` INT(11) NOT NULL,
  `dataEmprestimo` DATE NOT NULL,
  `dataDevolucao` DATE NOT NULL,
  `devolvido` VARCHAR(20) NOT NULL
) ENGINE = innodb;


CREATE TABLE `livro` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(300) NOT NULL,
  `tipoColecao` VARCHAR(300) NOT NULL,
  `numEdicao` INT(11) NOT NULL,
  `ano` INT(11) NOT NULL
) ENGINE = innodb;


-- INSERE AS FK's NAS TABELAS

ALTER TABLE `emprestimo` ADD CONSTRAINT `fk_cliente` FOREIGN KEY ( `idCliente` ) REFERENCES `cliente` ( `id` );
ALTER TABLE `emprestimo` ADD CONSTRAINT `fk_livro` FOREIGN KEY ( `idLivro` ) REFERENCES `livro` ( `id` );

  
-- DADOS PARA TESTE:

 
INSERT INTO `cliente` (`id`, `nome`, `telefone`, `endereco`) 
VALUES (1, 'Ze do Teste', '22222222', 'Av Testando, 5');


INSERT INTO `livro` (`id`, `nome`, `tipoColecao`, `numEdicao`, `ano`) 
VALUES (1, 'Livro Teste', 'BatTest', 2, 2016);

INSERT INTO `emprestimo` (`id`, `idCliente`, `idLivro`, `dataEmprestimo`, `dataDevolucao`, `devolvido`) 
VALUES ('1', '1', '1', '2016-11-01', '2016-11-10','emprestado');
