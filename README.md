
<img src="https://raw.githubusercontent.com/thiagodnf/cardapio-ru-bot/master/src/main/resources/images/logo.png" width="130px"/>


# Cardápio RU Bot

Bot para Telegram que monitora e alerta o cardápio do RU da sua universidade

[![Build Status](https://travis-ci.org/thiagodnf/cardapio-ru-bot.svg?branch=master)](https://travis-ci.org/thiagodnf/cardapio-ru-bot)
[![codecov](https://codecov.io/gh/thiagodnf/cardapio-ru-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/thiagodnf/cardapio-ru-bot)

## Recursos

- Ver o cardápio (café, almoço e jantar) de ontem, hoje e amanhã
- Definir alertas para comidas
- Supporte a todos os campus da universidade

## Universidades suportadas

O bot funciona com as seguintes universidades:

 - Universidade Federal do Paraná (UFPR)

## Quais campus o bot monitora?

O Bot monitora os seguintes campus:

|Universidade | Chave               |     Campus          |
|-------------|---------------------|---------------------|
|   UFPR      | politecnico         | Centro Politécnico  |
|             | central             | Central             |
|             | botanico            | Jardim Botânico     |
|             | agrarias            | Agrárias            |
|             | mirassol            | Mirassol            |
|             | litoral             | UFPR Litoral        |
|             | cem                 | CEM                 |
|             | palotina            | Palotina            |
|             | jandaia             | Jandaia do Sul      |

## Como adicionar no meu Telegram?

É simples! Apenas clique https://t.me/CardapioRUBot para adicionar. Uma vez adicionado você pode incluir ele em algum dos grupos que você já utiliza

## Como usar?

O uso do bot é através de comandos. Você precisa enviar comandos para interarir com ele. A seguir é apresentado todos os comandos disponíveis separados por funcionalidades.

### Ver Cardápio

Você pode ver o cardápio do campus monitorado digitando os seguintes comandos.

| Comando   | Descrição                   | Parâmetros  | Exemplo    |
|-----------|-----------------------------|-------------|------------|
|/ruhoje    | Exibe o cardápio de hoje    | Não tem     | /ruhoje    |
|/ruontem   | Exibe o cardápio de ontem   | Não tem     | /ruontem   |
|/ruamanha  | Exibe o cardápio de amanhã  | Não tem     | /ruamanha  |

### Definir Alertas

Caso você queria ser notificado da presença de algum item em específico no cardápio, você pode usar os seguintes comandos.

| Comando       | Descrição                             | Parâmetros  | Exemplo                   |
|---------------|---------------------------------------|-------------|---------------------------|
|/rualerte      | Define alertas para uma comida        | [comidas]+  | /rualerte picadinho fruta |
|/runaoalerte   | Remove uma comida da lista de alertas | [comidas]+  | /runaoalerte picadinho    |
|/rualertas     | Exibe os alertas cadastrados          | Não Tem     | /rualertas                |
|/rusemalertas  | Remove todos os alertas               | Não Tem     | /rusemalertas             |

### Monitorar Campus

Por padrão o bot monitora o cardápio do Centro Politécnico da UFPR. Porém, você pode trocar o campus usando os seguintes comandos.

| Comando           | Descrição                               | Parâmetros  | Exemplo                   |
|-------------------|-----------------------------------------|-------------|---------------------------|
|/rumonitorecampus  | Define o campus que você quer monitorar | [chave]     | /rumonitorecampus central |
|/rucampus          | Exibe o campus que você monitora        | Não tem     | /rucampus                 |

* Para ver quais chaves disponíveis, [acesse aqui](#quais-campus-o-bot-monitora)

#### Outros comandos disponíveis:

- /start - Exibe as mensagens de boas vindas
- /help - Exibe informações sobre o bot

## Alertas

O bot é capaz de enviar notificações para os usuários. Esses alertas são enviados todos os dias 11:00 para o almoço e 17:30 para o jantar para todos os usuários que criaram alertas ([Ver comandos](#definir-alertas))

## Atenção

Este Bot é um produto extra-oficial e não está oficialmente vinculada à alguma universidade
