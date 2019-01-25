# JMeter

Ce document présente nos tests JMeter.

## Prérequis

Avant de commencer nos tests, nous avons créé une Rule qui référe un pointScale et un Badge qui existent déjà.

```json
{
  "badgeID": 17,
  "conditions": [
    {
      "key": "string",
      "operator": "==",
      "value": "string"
    }
  ],
  "name": "string",
  "pointsScaleID": 17,
  "quantity": 1,
  "type": "string"
}
```

## Stratégie de test

Nous avons 20 unités qui font une boucle de 5 requêtes de POST d'event avec le payload suivant: 

```json
{
  "properties": [
    {
      "key": "string",
      "value": "string"
    }
  ],
  "remoteUserId": "1",
  "timestamp": "2019-01-24T21:18:06.155Z",
  "type": "string"
}
```

Le résultat final devrait être un nombre de points égal à 100.

## Résultat

Nous avons remarqué que le nombre de point n'était pas correcte, nous avons donc un problème de conccurence. Le nombre que nous avons relevé est de 88.