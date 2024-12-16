-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS `apresentacoes_musicais`;
USE `apresentacoes_musicais`;

-- Criação da tabela solicitante
CREATE TABLE IF NOT EXISTS `solicitante` (
    `codigo` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `cargo` VARCHAR(50) NOT NULL,
    `telefone` VARCHAR(15) NOT NULL,
    `email` VARCHAR(100) NOT NULL
);

-- Criação da tabela grupo
CREATE TABLE IF NOT EXISTS `grupo` (
    `codigo` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `coordenador` VARCHAR(100) NOT NULL,
    `telefone_coordenador` VARCHAR(15) NOT NULL,
    `quantidade_de_musicos` INT NOT NULL
);

-- Criação da tabela transporte
CREATE TABLE IF NOT EXISTS `transporte` (
    `codigo` INT AUTO_INCREMENT PRIMARY KEY,
    `nome_motorista` VARCHAR(100) NOT NULL,
    `telefone` VARCHAR(15) NOT NULL,
    `tipo_do_veiculo` VARCHAR(50) NOT NULL
);

-- Criação da tabela evento
CREATE TABLE IF NOT EXISTS `evento` (
    `codigo` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `local` VARCHAR(150) NOT NULL,
    `horario` DATETIME NOT NULL,
    `grupo` INT DEFAULT NULL,
    `status` ENUM('PENDENTE', 'CONFIRMADO', 'CANCELADO') NOT NULL,
    `transporte` INT DEFAULT NULL,
    `solicitante` INT DEFAULT NULL,
    FOREIGN KEY (`grupo`) REFERENCES `grupo`(`codigo`) ON DELETE SET DEFAULT ON UPDATE CASCADE,
    FOREIGN KEY (`transporte`) REFERENCES `transporte`(`codigo`) ON DELETE SET DEFAULT ON UPDATE CASCADE,
    FOREIGN KEY (`solicitante`) REFERENCES `solicitante`(`codigo`) ON DELETE SET DEFAULT ON UPDATE CASCADE
);

-- Inserção de registros padrão para chave estrangeira
INSERT INTO `grupo` (`codigo`, `nome`, `coordenador`, `telefone_coordenador`, `quantidade_de_musicos`) VALUES
(NULL, 'Aguardando disponibilidade de grupo', 'Coordenador Padrão', '0000-0000', 0);

INSERT INTO `transporte` (`codigo`, `nome_motorista`, `telefone`, `tipo_do_veiculo`) VALUES
(NULL, 'Aguardando confirmação de transporte', '0000-0000', 'Desconhecido');

INSERT INTO `solicitante` (`codigo`, `nome`, `cargo`, `telefone`, `email`) VALUES
(NULL, 'Aguardando informações de solicitante', 'Cargo Padrão', '0000-0000', 'padrao@exemplo.com');

-- Atualizando chaves estrangeiras padrão para referências
ALTER TABLE `evento`
    ALTER `grupo` SET DEFAULT 1,
    ALTER `transporte` SET DEFAULT 1,
    ALTER `solicitante` SET DEFAULT 1;
