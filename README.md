
<img src="https://raw.githubusercontent.com/thiagodnf/ufpr-cardapio-ru-bot/master/img/logo.png" width="130px"/>


# UFPR Cardápio RU Bot

Bot para Telegram do Cardápio do RU da UFPR

[![Build Status](https://travis-ci.org/thiagodnf/ufpr-cardapio-ru-bot.svg?branch=master)](https://travis-ci.org/thiagodnf/ufpr-cardapio-ru-bot)
[![codecov](https://codecov.io/gh/thiagodnf/ufpr-cardapio-ru-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/thiagodnf/ufpr-cardapio-ru-bot)

## Como funciona?

O bot acessa a página http://www.pra.ufpr.br/portal/ru/cardapio-2/ a cada hora e executa um parse para capturar o cardápio de todos os campus da universidade

## Recursos

- Ver o cardápio (café, almoço e jantar) de ontem, hoje e amanhã
- Definir alertas para comidas
- Supporte a todos os campus da universidade

## Quais campus o bot monitora?

O Bot monitora os seguintes campus:

| Chave               |     Campus          |
|---------------------|---------------------|
| politecnico  | Centro Politécnico  |
| central             | Central             |
| botanico            | Jardim Botânico     |
| agrarias            | Agrárias            |
| mirassol            | Mirassol            |
| litoral             | UFPR Litoral        |
| cem                 | CEM                 |
| palotina            | Palotina            |
| jandaia      | Jandaia do Sul      |

## Como adicionar no meu Telegram?

É simples! Apenas clique http://t.me/UFPRCardapioRUBot para adicionar. Uma vez adicionado você pode incluir ele em algum dos grupos que você já utiliza

## Como usar?

O uso do bot é através de comandos. Você precisa enviar comandos para interarir com ele. A seguir é apresentado todos os comandos disponíveis separados por funcionalidades.

### Ver Cardápio

Você pode ver o cardápio do campus monitorado digitando os seguintes comandos.

| Comando   | Descrição                   | Parâmetros  | Exemplo    |
|-----------|-----------------------------|-------------|------------|
|/ru        | Exibe o cardápio de hoje    | Não tem     | /ru        |
|/ruontem   | Exibe o cardápio de ontem   | Não tem     | /ruontem   |
|/ruamanha  | Exibe o cardápio de amanhã  | Não tem     | /ruamanha  |

### Definir Alertas

Caso você queria ser notificado da presença de algum item em específico no cardápio, você pode usar os seguintes comandos.

| Comando       | Descrição                             | Parâmetros  | Exemplo                   |
|---------------|---------------------------------------|-------------|---------------------------|
|/rualerte      | Define alertas para uma comida        | [comidas]+  | /rualerte picadinho fruta |
|/runaoalerte   | Remove uma comida da lista de alertas | [comida]    | /ru                       |
|/rualertas     | Exibe os alertas cadastrados          | Não Tem     | /rualertas                |
|/rusemalertas  | Remove todos os alertas               | Não Tem     | /rusemalertas             |

### Monitorar Campus

Por padrão o bot monitora o cardápio do Centro Politécnico. Porém, você pode trocar o campus usando os seguintes comandos.

| Comando           | Descrição                               | Parâmetros  | Exemplo                   |
|-------------------|-----------------------------------------|-------------|---------------------------|
|/rumonitorecampus  | Define o campus que você quer monitorar | [chave]     | /rumonitorecampus central |
|/rucampus          | Exibe o campus que você monitora        | Não tem     | /rucampus                 |

* Para ver quais chaves disponíveis, [acesse aqui](#quais-campus-o-bot-monitora)

#### Outros comandos disponíveis:

- /start - Exibe as mensagens de boas vindas
- /help - Exibe informações sobre o bot

## Alertas

O bot é capaz de enviar notificações para os usuários. Esses alertas são enviados todos os dias 11:30 para o almoço e 17:30 para o jantar para todos os usuários que criaram alertas ([Ver comandos](#definir-alertas))