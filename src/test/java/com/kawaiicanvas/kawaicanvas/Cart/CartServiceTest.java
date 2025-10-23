package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.Cart.model.CartItem;

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

        CartItem item1 = new CartItem();
        item1.setId("item1");
        item1.setCanvas(canvas1);
        item1.setNumberOfCanvases(1);

        Canvas canvas2 = new Canvas();
        canvas2.setId("canvas2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("50");

        CartItem item2 = new CartItem();
        item2.setId("item2");
        item2.setCanvas(canvas2);
        item2.setNumberOfCanvases(1);

        // initialisera canvases listan och lägg till tavlorna
        cart.setItems(Arrays.asList(item1, item2));

        // räkna ut totalpriset
        BigDecimal totalPrice = cart.getItems().stream()
                .map(item -> new BigDecimal(item.getCanvas().getPrice())
                        .multiply(new BigDecimal(item.getNumberOfCanvases())))
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
        canvas1.setPrice("80");

        CartItem item1 = new CartItem();
        item1.setId("item1");
        item1.setCanvas(canvas1);
        item1.setNumberOfCanvases(1);

        Canvas canvas2 = new Canvas();
        canvas2.setId("canvas2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("80");

        CartItem item2 = new CartItem();
        item2.setId("item2");
        item2.setCanvas(canvas2);
        item2.setNumberOfCanvases(2);

        // initialisera canvases listan och lägg till tavlorna
        cart.setItems(Arrays.asList(item1, item2));

        // räkna ut totalpriset
        BigDecimal totalPrice = cart.getItems().stream()
                .map(item -> new BigDecimal(item.getCanvas().getPrice())
                        .multiply(new BigDecimal(item.getNumberOfCanvases())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // lägg till leverans avgift om totalpriset är under 200 kr
        BigDecimal deliveryFee = new BigDecimal("49");
        BigDecimal freeDeliveryThreshold = new BigDecimal("200");
        if (totalPrice.compareTo(freeDeliveryThreshold) < 0) {
            totalPrice = totalPrice.add(deliveryFee);
        }

        System.out.println("Total price: " + totalPrice);

        // Lägg till assertion för att verifiera resultatet
        assertEquals(new BigDecimal("240"), totalPrice);

    }

}