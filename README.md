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
https://www.mongodb.com/lp/cloud/atlas/try4-reg?utm_source=google&utm_campaign=search_gs_pl_evergreen_atlas_core-high-int_retarget-brand_gic-null_ww-tier3_ps-all_desktop_eng_lead&utm_term=mongodb%20atlas&utm_medium=cpc_paid_search&utm_ad=e&utm_ad_campaign_id=22194044133&adgroup=174717509699&cq_cmp=22194044133&gad_source=1&gad_campaignid=22194044133&gbraid=0AAAAADQ1402kRW6Yw3xoGIxhal8VGBkwZ&gclid=CjwKCAjwu9fHBhAWEiwAzGRC_2mil2_KssVyBdCS039whgEHqBhUrmQND-FKFh9NutdyPoh9n0HR7xoCtycQAvD_BwE
```
Skapa ett konto på Stripe om du inte har
```bash
https://stripe.com/se?utm_campaign=EMEA_SE_se_Google_Search_Brand_Stripe_EXA-21013069848&utm_medium=cpc&utm_source=google&utm_content=707576416463&utm_term=stripe&utm_matchtype=e&utm_adposition=&utm_device=c&gad_source=1&gad_campaignid=21013069848&gbraid=0AAAAADKNRO4POEf805ShU3L0lET9qaVkn&gclid=CjwKCAjwu9fHBhAWEiwAzGRC_0G3OTBGGU_9OUYQhvvgnRTB3ghzRIEjkck6NDkTxUrZKjEO3pVGjRoC2U0QAvD_BwE
```

Skapa ett konto på OpenAI om du inte har :
```bash
```

1. Skapa en .env fil likt detta exempel:
```bash
MONGO_DB_URI=din mongoDB

AI_KEY= din AI nyckel
OPENAI_API_URL=https://api.openai.com/v1/chat/completions

CLOUDINARY_API_KEY=

CLOUDINARY_API_SECRET=

CLOUDINARY_CLOUD_NAME=dlhqajdjy

STRIPE_API_KEY=din stripe nyckel

STRIPE_SUCCESS_URL=https://example.com/success
STRIPE_CANCEL_URL=https://example.com/success

ADMIN_USERNAME=din admin
ADMIN_PASSWORD=lösenord
```


