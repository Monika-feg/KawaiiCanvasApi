package com.kawaiicanvas.kawaicanvas.Bot.model;

public enum ChatBotResponseEnum {
    LEVERANCE(
            "Hej! Normalt tar leveransen 2 till 5 arbetsdagar beroende på din adress. När din order skickas får du ett spårningsnummer så att du kan följa paketet hela vägen."),
    RETURNS("Hej! Självklart kan du göra en retur. Vänligen se till att produkten är i originalskick och kontakta vår kundtjänst med ditt ordernummer så guidar vi dig vidare genom processen."),
    PAYING("Hej! När du betalar med kort dras pengarna direkt, medan faktura- och delbetalningsalternativ kan ta upp till 14 dagar."),
    CONTACT("Hej! Du kan kontakta vår kundtjänst för tillfället enbart via e-post på support@kawaiic.com.");

    private final String systemPrompt;

    ChatBotResponseEnum(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

}
