## Frontend

Det finns ett tillhörande frontend-repo: [KawaiiCanvasClient](https://github.com/Monika-feg/KawaiiCanvasClient)

## Brancher och miljöer

- Använd **main**-branchen i både backend- och frontend-repo för lokal utveckling och testning.
- Använd **deploy**-branchen i både backend- och frontend-repo om du vill testa den deployade versionen.

Se till att Stripe-URL:er och API-anrop pekar rätt beroende på om du kör lokalt eller mot deployment.

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
5. Skapa en `.env`-fil i projektroten enligt detta exempel:

   ````env
   MONGO_DB_URI=din mongoDB
   AI_KEY=din AI nyckel
   OPENAI_API_URL=https://api.openai.com/v1/chat/completions
   STRIPE_API_KEY=din stripe nyckel
   STRIPE_SUCCESS_URL=http://localhost:5173/#/show-order
   STRIPE_CANCEL_URL=http://localhost:5173/#/cart
   > **Tips för Stripe lokalt:**
   > För att kunna testa betalning mot min lokala frontend måste `stripe.success.url2` och `stripe.cancel.url` i `application.properties` (eller `.env`) vara:
   >
   > ```properties
   > stripe.success.url2=http://localhost:5173/#/show-order
   > stripe.cancel.url=http://localhost:5173/#/cart
   > ```
   > Annars kommer du inte tillbaka till rätt sida efter betalning.
   ADMIN_USERNAME=din admin
   ADMIN_PASSWORD=lösenord
   ````

6. Starta projektet lokalt:

   ```bash
   ./mvnw spring-boot:run
   ```

   eller kör via din IDE.

7. API-dokumentation (Swagger UI):
   När projektet körs lokalt, öppna [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) för att se och testa alla endpoints.

## AI-bot och personlighet

Boten kan svara både på fördefinierade frågor (via enum) och på fria frågor med hjälp av OpenAI. Du kan styra botens personlighet genom att skicka in valfri text som `systemPrompt` i API-anropet, t.ex.:

- "Du är en glad och hjälpsam kundtjänstmedarbetare."
- "Du är expert på konst och design."
- "Du svarar alltid med en rolig emoji."

Se exempel på API-anrop i frontend-delen eller i Swagger UI.

## Säkerhet

Projektet använder Spring Security. Sätt admin-användare och lösenord i `.env`-filen. Endpoints kan kräva autentisering beroende på konfiguration.

## Testning

Testklasser finns under `src/test/java/com/kawaiicanvas/kawaicanvas/`. Kör tester med:

```bash
./mvnw test
```

## Miljövariabler

Projektet använder `spring-dotenv` för att ladda miljövariabler från `.env`-filen automatiskt vid start.

## Frontend-integration

Om du använder en separat frontend, se till att API-anropen går mot rätt port (`http://localhost:8080`) och att CORS är korrekt konfigurerat. Se `@CrossOrigin` i `ChatController`.

## Open Source API-dokumentation

Swagger/OpenAPI finns på `/swagger-ui.html` när projektet körs.

## Docker

För att starta projektet och din container lokalt:

Bygg Docker-image:

```bash
docker build -t kawaicanvas-api .
docker run -p 8080:8080 kawaicanvas-api
```

Vill du testa den deployade versionen, gå in på deploy-branchen.

### Deployment (Render)

1. Bygg Docker-image:
   ```bash
   docker build -t kawaicanvas-api .
   ```
2. Starta med miljövariabler:
   ```bash
   docker run -p 8080:8080 --env-file .env kawaicanvas-api
   ```
3. **På Render:** Lägg till miljövariabler via dashboarden (Settings → Environment). Render använder inte `.env`-filen i Docker-image, utan du sätter variablerna direkt i Render.

   **Tips:**

- Sätt aldrig känsliga nycklar direkt i koden eller i versionshanterad `application.properties`.
- Deployment-branchen är separat från main och används bara för deployment.

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
