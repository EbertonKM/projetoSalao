-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13/02/2025 às 02:22
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projetosalao`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `atendimento`
--

CREATE TABLE `atendimento` (
  `id` int(11) NOT NULL,
  `horario` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_pago` int(11) DEFAULT NULL,
  `pessoa_cliente_id` int(11) DEFAULT NULL,
  `pessoa_profissional_id` int(11) DEFAULT NULL,
  `servico_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cargo`
--

CREATE TABLE `cargo` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cargo`
--

INSERT INTO `cargo` (`id`, `descricao`) VALUES
(1, 'Cliente'),
(2, 'Profissional'),
(3, 'Gerente');

-- --------------------------------------------------------

--
-- Estrutura para tabela `notificacao`
--

CREATE TABLE `notificacao` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `emissao` datetime(6) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `pessoa_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `cargo_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pessoa`
--

INSERT INTO `pessoa` (`cargo_id`, `id`, `email`, `nome`, `senha`, `telefone`) VALUES
(3, 1, 'gerente@gmail.com', 'Usuário Gerente', 'gerente', '01234567890');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pessoa_has_servico`
--

CREATE TABLE `pessoa_has_servico` (
  `pessoa_id` int(11) NOT NULL,
  `servico_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `servico`
--

CREATE TABLE `servico` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `preco` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `atendimento`
--
ALTER TABLE `atendimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyc5rbka8ok5kkl8mifq17gcp` (`pessoa_cliente_id`),
  ADD KEY `FKlbcqeq2ues2y1a33m3o8m4p9f` (`pessoa_profissional_id`),
  ADD KEY `FKmw9y79dthcenijemk3j7841hc` (`servico_id`);

--
-- Índices de tabela `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `notificacao`
--
ALTER TABLE `notificacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoqhde8rgobc8o2rivikvtgr16` (`pessoa_id`);

--
-- Índices de tabela `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2fifkgh5jq9ae4p5yox4highf` (`cargo_id`);

--
-- Índices de tabela `pessoa_has_servico`
--
ALTER TABLE `pessoa_has_servico`
  ADD KEY `FKf0lv8eey3eo0xf6kk2l20mri1` (`servico_id`),
  ADD KEY `FK9mr5bw9kshryms3dc76udf9bk` (`pessoa_id`);

--
-- Índices de tabela `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `atendimento`
--
ALTER TABLE `atendimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cargo`
--
ALTER TABLE `cargo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `notificacao`
--
ALTER TABLE `notificacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `servico`
--
ALTER TABLE `servico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `atendimento`
--
ALTER TABLE `atendimento`
  ADD CONSTRAINT `FKjyc5rbka8ok5kkl8mifq17gcp` FOREIGN KEY (`pessoa_cliente_id`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `FKlbcqeq2ues2y1a33m3o8m4p9f` FOREIGN KEY (`pessoa_profissional_id`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `FKmw9y79dthcenijemk3j7841hc` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`);

--
-- Restrições para tabelas `notificacao`
--
ALTER TABLE `notificacao`
  ADD CONSTRAINT `FKoqhde8rgobc8o2rivikvtgr16` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`);

--
-- Restrições para tabelas `pessoa`
--
ALTER TABLE `pessoa`
  ADD CONSTRAINT `FK2fifkgh5jq9ae4p5yox4highf` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`);

--
-- Restrições para tabelas `pessoa_has_servico`
--
ALTER TABLE `pessoa_has_servico`
  ADD CONSTRAINT `FK9mr5bw9kshryms3dc76udf9bk` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `FKf0lv8eey3eo0xf6kk2l20mri1` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
