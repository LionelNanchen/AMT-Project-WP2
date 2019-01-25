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

Nous avons remarqué que le nombre de point n'était pas correcte, et avons remarqué que cela vient du fait que plusieurs Rewards sont créées pour une même Rules/User ce qui pose un problème lorsque l'on récupère la reward à incrémenter, la requête que le serveur fait à la base de données nous en retourne plusieurs ce qui rend une erreur 500 aux clients. Il faudrait ajouter une contrainte afin que l'agregat Rules/User soit unique et que SQL retourne une erreur qui puisse intérompre la transaction en cours.