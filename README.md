# 🚀 API Java com Spring — Arquitetura CQRS com Cache Distribuído

## 📌 Visão Geral

Este projeto é uma API desenvolvida em **Java com Spring**, baseada no padrão **CQRS (Command Query Responsibility Segregation)**, utilizando **CommandBus** e **QueryBus** para segregação de responsabilidades.

A arquitetura foi projetada para:

- Separar claramente operações de escrita e leitura  
- Melhorar performance de consultas com cache distribuído  
- Escalar leituras via réplicas de banco de dados  
- Garantir organização e baixo acoplamento através de handlers  

---

## 🏗️ Arquitetura

A aplicação segue o fluxo:

Controller → DTO (Request) → Command/Query → Bus → Handler → Persistência/Cache

### 🔹 1. Camada de Entrada (Controller)

- Recebe **DTOs (Requests)** via endpoints HTTP.
- Cada rota decide se a operação será:
  - **Command** (escrita)
  - **Query** (leitura)

---

## 📚 CQRS na Prática

### ✅ Commands
- Representam **operações de escrita**
- Não retornam dados (apenas `Result<Void>`)
- Enviados ao `CommandBus`
- Processados por `CommandHandlers`

Fluxo:

Controller → Command → CommandBus → CommandHandler → Banco (Master)

---

### 🔎 Queries
- Representam **operações de leitura**
- Retornam dados
- Enviadas ao `QueryBus`
- Processadas por `QueryHandlers`

Fluxo com cache:

Controller → Query → QueryBus → Redis → (Cache Miss) → ProxySQL → Replicas → Retorno + Cache Update

---

## ⚡ Estratégia de Leitura (Diferencial do Projeto)

O `QueryBus` implementa uma estratégia de alta performance:

1. Recebe uma Query  
2. Verifica se o objeto está no **Redis**  
3. Caso esteja:
   - Retorna imediatamente (cache hit)  
4. Caso não esteja:
   - Consulta o banco via **ProxySQL**
   - Busca nas **réplicas**
   - Retorna o resultado
   - Atualiza o cache  

---

## 🧠 Cache

- Padrão: **Cache-Aside**
- Tecnologia: Redis
- Objetivo:
  - Reduzir latência
  - Diminuir carga no banco
  - Melhorar throughput de leitura

---

## 🗄️ Banco de Dados

Arquitetura composta por:

- 1 Master (escrita)
- 2 Réplicas (leitura)
- Gerenciamento via **ProxySQL**

### Estratégia:

- Escritas → Master  
- Leituras → Réplicas  
- Balanceamento automático via ProxySQL  

Isso garante:

- Alta performance
- Escalabilidade horizontal
- Distribuição eficiente de carga

---

## 🧩 Stack Tecnológica

- Java
- Spring Framework
- Redis
- MySQL
- ProxySQL
- Arquitetura baseada em CQRS

---

## 📊 Fluxo Completo de Leitura

HTTP Request  
↓  
DTO  
↓  
Query  
↓  
QueryBus  
↓  
Redis (Cache Check)  
↓ (miss)  
ProxySQL  
↓  
Replicas  
↓  
Retorno + Cache Update  

---

## 📊 Fluxo Completo de Escrita

HTTP Request  
↓  
DTO  
↓  
Command  
↓  
CommandBus  
↓  
CommandHandler  
↓  
Master Database  

---

## 📦 Estrutura Conceitual de Pacotes

controller/  
dto/  
command/  
query/  
bus/  
handler/  
repository/  
config/  

---

## 🎯 Benefícios da Arquitetura

- ✔ Separação clara entre leitura e escrita  
- ✔ Alta performance com Redis  
- ✔ Escalabilidade via réplicas  
- ✔ Baixo acoplamento  
- ✔ Código organizado e extensível  
- ✔ Fácil manutenção  
- ✔ Preparado para ambientes distribuídos  

---

## 🚀 Possíveis Evoluções

- EventBus  
- Event Sourcing  
- Observabilidade com OpenTelemetry  
- Métricas com Prometheus  
- Circuit Breaker (Resilience4j)  
- Estratégia avançada de cache invalidation  
- Evolução para microservices  

---

## 📈 Escalabilidade

A arquitetura permite:

- Escalar API horizontalmente  
- Escalar Redis  
- Adicionar novas réplicas de banco  
- Separação futura em microserviços  

---

## 👨‍💻 Autor

Projeto desenvolvido com foco em:

- Arquitetura limpa  
- Performance  
- Escalabilidade  
- Boas práticas de engenharia de software  
