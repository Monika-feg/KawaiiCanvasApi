# KawaiiCanvasApi

KawaiiCanvasApi är ett backend-API byggt med Spring Boot och MongoDB Atlas för att hantera en webbshop. Projektet är en skoluppgift och kan enkelt köras lokalt. Finns även en deployad version fram till 17/11 -2025.

Deploy adress: https://kawaiicanvasapi.onrender.com

## Funktioner

- API (kundtjänst med OpenAI)
- WebSocket för att räkna kundkorgen
- Hantera tavlor (hämta, skapa, ta bort)
- Hantera kundvagn
- Hantera order
- Säkerhet med Spring Security
- MongoDB Atlas
- Dockerfile
- Enkel betalning med Stripe

## Teknologier

- Java 21
- Spring Boot 3.5
- MongoDB Atlas
- Maven
- Docker
- Stripe
- OpenAI
- WebSocket

## Kom igång

1. Klona ner repot:
   ```bash
   git clone git@github.com:Monika-feg/KawaiiCanvasApi.git
   ```
2. Ha ett konto på MongoDB Atlas: https://www.mongodb.com
3. Skapa ett konto på Stripe: https://stripe.com
4. Skapa ett konto på OpenAI: https://platform.openai.com
5. Skapa en .env-fil likt detta exempel:

   ```env
   MONGO_DB_URI=din mongoDB
   AI_KEY=din AI nyckel
   OPENAI_API_URL=https://api.openai.com/v1/chat/completions
   STRIPE_API_KEY=din stripe nyckel
   STRIPE_SUCCESS_URL=https://example.com/success
   STRIPE_CANCEL_URL=https://example.com/cancel
   ADMIN_USERNAME=din admin
   ADMIN_PASSWORD=lösenord
   ```

   Starta projektet med run java

## Docker

För att starta projektet och din container lokalt
Vill du testa den depoyade så gå in på deploy branchen.
Den har en egen Readme.

Bygg Docker-image:

```bash
docker build -t kawaicanvas-api .
```

Starta containern:

```bash
docker run -p 8080:8080 kawaicanvas-api
```

## API-exempel

**Exempel på fler endpoints:**

- `GET /canvas` – Hämta alla tavlor
- `POST /cart` – Lägg till i kundvagn
- `POST /order` – Skapa order

## Testa

Kör tester:

```bash
./mvnw test
```

## Bidra

Bidrag är välkomna! Skapa gärna en issue eller en pull request.

## Kontakt

Monika Engström – [GitHub](https://github.com/Monika-feg)

## Licens

SkolProjekt
