# KawaiiCanvasApi

KawaiiCanvasApi är en backan-API byggt med spring boot och mongoDB atlas för att hantera en webbshop. Projektet är en skol uppgift och kan enkelt köras lokalt. Finns även en deployadversion fram till 17/11 -2025.

# Funktioner

* API ( som hjälper till som en kundtjänst)
* Websocket för att räkna kundkorgen
* Hantera tavlor ( hämta tavlor, hämta via id, skapa nya tavlor och ta bort tavlor)
* Hantera kundvagn
* hantera order
* Säkerhet med spring Security
* MongoDb Atlas
* Docker file
* Enkel betalning med hjälp av Stripe

#Teknologier

* Java 21
* spring boot 3.5
* MongoDb Atlas
* Maven
* Docker

# Kom igång

Klona ner repot:
```bash
git@github.com:Monika-feg/KawaiiCanvasApi.git
```
Ha ett konto på MongoDb Atlas
```bash
https://www.mongodb.com
```
Skapa ett konto på Stripe om du inte har
```bash
https://stripe.com
```

Skapa ett konto på OpenAI om du inte har :
```bash
```

1. Skapa en .env fil likt detta exempel:
```bash
MONGO_DB_URI=din mongoDB

AI_KEY= din AI nyckel
OPENAI_API_URL=https://api.openai.com/v1/chat/completions

STRIPE_API_KEY=din stripe nyckel

STRIPE_SUCCESS_URL=https://example.com/success
STRIPE_CANCEL_URL=https://example.com/success

ADMIN_USERNAME=din admin
ADMIN_PASSWORD=lösenord
```

Starta Apiet med hjälp av Run Java knappen.


