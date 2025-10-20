package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;

public class CartServiceTest {

    // Räkna ut totalpris och leveransavgift och om det blir rätt
    @Test
    public void getTotalPriceAndDeliveryFeeTest() {
        // skapa en kundvagn
        Cart cart = new Cart();
        cart.setId("testCartId");

        // skapa några tavlor med priser
        Canvas canvas1 = new Canvas();
        canvas1.setId("canvas1");
        canvas1.setTitle("Cute Cat");
        canvas1.setPrice("80");

        Canvas canvas2 = new Canvas();
        canvas2.setId("canvas2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("50");

        // initialisera canvases listan och lägg till tavlorna
        cart.setCanvases(Arrays.asList(canvas1, canvas2));

        // räkna ut totalpriset
        BigDecimal totalPrice = cart.getCanvases().stream()
                .map(canvas -> new BigDecimal(canvas.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // lägg till leverans avgift om totalpriset är under 200 kr
        BigDecimal deliveryFee = new BigDecimal("49");
        BigDecimal freeDeliveryThreshold = new BigDecimal("200");
        if (totalPrice.compareTo(freeDeliveryThreshold) < 0) {
            totalPrice = totalPrice.add(deliveryFee);
        }

        System.out.println("Total price: " + totalPrice);

        // Lägg till assertion för att verifiera resultatet
        assertEquals(new BigDecimal("179"), totalPrice);
    }

    // Test för att räkna ut totalpriset utan leveransavgift om det blir rätt
    @Test
    public void totalPriceWithoutDeliveryFeeTest() {
        // skapa en kundvagn
        Cart cart = new Cart();
        cart.setId("testCartId");

        // skapa några tavlor med priser
        Canvas canvas1 = new Canvas();
        canvas1.setId("canvas1");
        canvas1.setTitle("Cute Cat");
        canvas1.setPrice("120");

        Canvas canvas2 = new Canvas();
        canvas2.setId("canvas2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("100");

        // initialisera canvases listan och lägg till tavlorna
        cart.setCanvases(Arrays.asList(canvas1, canvas2));

        // räkna ut totalpriset
        BigDecimal totalPrice = cart.getCanvases().stream()
                .map(canvas -> new BigDecimal(canvas.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // lägg till leverans avgift om totalpriset är under 200 kr
        BigDecimal deliveryFee = new BigDecimal("49");
        BigDecimal freeDeliveryThreshold = new BigDecimal("200");
        if (totalPrice.compareTo(freeDeliveryThreshold) < 0) {
            totalPrice = totalPrice.add(deliveryFee);
        }

        System.out.println("Total price: " + totalPrice);

        // Lägg till assertion för att verifiera resultatet
        assertEquals(new BigDecimal("220"), totalPrice);

    }

}