# Sistema de Gestão de Consultas Médicas

## 📌 Descrição Geral
O projeto tem como objetivo modernizar a gestão de consultas médicas em clínicas e hospitais, eliminando problemas comuns como:
- Agendamentos conflitantes;
- Comunicação ineficaz entre profissionais e pacientes;
- Falta de controle eficiente do histórico de atendimentos.

## 🎯 Objetivos
- Cadastro de médicos e pacientes;
- Agendamento e cancelamento de consultas;
- Histórico de atendimentos acessível por usuários autorizados;
- Notificações automáticas por e-mail/SMS;
- Interfaces separadas para pacientes, médicos e atendentes.

## 🧑‍🤝‍🧑 Atores do Sistema
- **Paciente**
- **Médico**
- **Atendente**

## 📋 Casos de Uso Principais

### UC1: Agendar Consulta
**Atores:** Paciente  
**Fluxo Principal:**
1. Paciente faz login.
2. Escolhe "Agendar Consulta".
3. Sistema exibe médicos, datas e horários.
4. Paciente seleciona médico, data e horário.
5. Sistema verifica disponibilidade.
6. Se disponível:
   - Registra a consulta no banco de dados;
   - Envia notificação ao médico;
   - Confirma ao paciente.

### UC2: Cancelar Consulta
**Atores:** Paciente ou Médico  
**Fluxo Principal:**
1. Login no sistema;
2. Acesso à tela "Minhas Consultas";
3. Seleção da consulta a ser cancelada;
4. Confirmação do cancelamento;
5. Sistema atualiza status, notifica envolvidos, exibe sucesso.

### UC3: Visualizar Histórico de Consultas
**Atores:** Paciente ou Médico  
**Fluxo Principal:**
1. Login no sistema;
2. Acesso à tela de histórico;
3. Sistema exibe lista de consultas anteriores com data, status e participantes;
4. Detalhamento por consulta disponível.

### UC4: Confirmar Realização de Consulta
**Atores:** Médico (principal), Paciente (secundário)  
**Fluxo Principal:**
1. Médico acessa "Consultas do Dia";
2. Seleciona a consulta;
3. Marca como realizada, com opção de adicionar observações;
4. Sistema registra data/hora, notifica paciente e adiciona ao histórico.

## 🔔 Notificações Automáticas
Utiliza o padrão **Observer** para:
- Agendamentos;
- Cancelamentos;
- Confirmações de consulta.

## 🧱 Arquitetura e Padrões de Projeto
- **MVC (Model-View-Controller):** Separação de responsabilidades.
- **Observer:** Para notificações.
- **Singleton:** Para controle de instâncias críticas.
- **Facade:** Para simplificar interações com subsistemas.

## ⛔ Restrições
- Prontuário eletrônico não incluso;
- Faturamento e cobrança fora do escopo;
- Tempo total de execução: 9 semanas.

## 📆 Cronograma
- Semanas 1-2: Levantamento e modelagem;
- Semanas 3-4: Backend e banco de dados;
- Semanas 5-6: Frontend;
- Semanas 7-8: Testes;
- Semana 9: Apresentação.

## ✅ Resultados Esperados
- Plataforma funcional e amigável;
- Banco de dados seguro;
- Notificações automáticas;
- Adoção pelos usuários com treinamento;
- Redução de faltas e aumento de eficiência.
