# 🧩 Projeto Banco de Dados

Este projeto foi desenvolvido em **Java**, utilizando **JDBC** para integração com um banco de dados **MySQL**.  
O objetivo é praticar a manipulação de dados, o uso de **procedures**, **triggers**, **views** e o relacionamento entre diversas tabelas.

---

## 👥 Equipe
- **Samuel Ponciano**  
- **Rhydrian Coutinho**  
- **Nathan Vinicius**  
- **Pedro Avellar**

---

## 🚀 Tecnologias Utilizadas
- **Java 17+**
- **MySQL 8+**
- **JDBC (Java Database Connectivity)**
- **Driver MySQL Connector/J**
- **IDE:** VSCode e Workbench

---

## 🗂️ Estrutura do Banco de Dados

O sistema gerencia informações de clientes, vendedores, transportadoras, produtos e vendas.  
Há também tabelas especiais para controle de **funcionários** e **clientes com benefícios**.

**Tabelas principais:**
- `clientes` — dados dos clientes.  
- `vendedor` — informações dos vendedores.  
- `transportadora` — empresas de entrega.  
- `produtos` — itens disponíveis para venda.  
- `venda` — registros de vendas, ligando clientes, vendedores e transportadoras.  
- `vendas_produto` — associação entre produtos e vendas.  
- `funcionario` e `funcionario_especial` — gestão de funcionários e bônus.  
- `cliente_especial` — gerenciamento de cashback.

---

## 📋 Funcionalidades
- Cadastro e consulta de clientes, vendedores e produtos.  
- Registro de vendas e controle de transportadoras.  
- Cálculo automático de cashback e bônus.  
- Visualização de dados consolidados através de views.  

---

# ⚙️ Funções SQL – Projeto Java JDBC + MySQL

Este módulo (`CriarFunction.java`) cria funções no banco de dados **MySQL** para realizar cálculos e consultas automatizadas.  
Essas funções são executadas diretamente no banco e podem ser chamadas pelo código Java ou em consultas SQL.

---

## 🧠 Funções Criadas

### 🧮 `Calcular_idade(p_id_cliente INT)`
Retorna a **idade do cliente** com base em sua data de nascimento cadastrada.  
- **Entrada:** ID do cliente  
- **Retorno:** Idade (INT)

---

### 🚚 `Soma_fretes(destino VARCHAR(200))`
Calcula o **total de fretes** cobrados para um determinado endereço de destino.  
- **Entrada:** Endereço de destino  
- **Retorno:** Valor total de fretes (DECIMAL)

---

## ⚙️ Procedures Criadas

### 🧾 `Reajuste`
Aplica um **reajuste salarial** a todos os funcionários de um determinado cargo.  
**Parâmetros:**
- `p_percentual` → percentual de aumento (ex: 10 para 10%)  
- `p_cargo` → cargo dos funcionários que receberão o reajuste  

---

### 🎁 `Sorteio`
Realiza um **sorteio aleatório** entre os clientes.  
Clientes especiais recebem **R$200,00**, enquanto os demais recebem **R$100,00**.

---

### 🛒 `RegistrarVenda`
Registra uma **venda de produto**, atualizando automaticamente o estoque.  
**Funções:**  
- Insere o produto vendido na tabela `vendas_produto`  
- Decrementa a quantidade no estoque (`produtos`)  

---

## 📊 Views Criadas

- **totalVenda_Cliente:** mostra o total de vendas e o valor gasto por cada cliente.  
- **totalVendas_transportadora:** exibe o total de vendas realizadas por cada transportadora.  
- **view_produtos_por_vendedor:** lista produtos vendidos por cada vendedor e a quantidade de vendas.  

---
📅 **Data de conclusão:** 10/11/2025  

