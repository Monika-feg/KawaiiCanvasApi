package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Canvas.CanvasRepository;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.Cart.model.CartItem;

// felhantering tagit inspiration av
// https://www.w3schools.com/java/java_ref_errors.asp
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CanvasRepository canvasRepository;

    // skapa en ny kundvagn
    public Cart createNewCart(Cart cart) {
        try {
            return cartRepository.save(cart);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Cart data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not create new cart ", e);
        }

    }

    // hitta kundvagn med hjälp av id
    public Cart getCartById(String id) {
        try {
            return cartRepository.findById(id).orElse(null);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Cart data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not find cart with id: " + id, e);
        }
    }

    // Lägger till en tavla till kundvagnen
    public Cart addCanvasToCart(String cartId, String canvasId, int quantity) {
        try {
            Cart cart = getCartById(cartId);
            Canvas canvas = canvasRepository.findById(canvasId).orElseThrow(
                    () -> new IllegalArgumentException("Canvas not found with id: " + canvasId));

            Optional<CartItem> existingCanvas = cart.getItems().stream()
                    .filter(item -> item.getCanvas().getId().equals(canvasId))
                    .findFirst();

            if (existingCanvas.isPresent()) {
                existingCanvas.get().setNumberOfCanvases(quantity);
            } else {
                CartItem newItem = new CartItem();
                newItem.setId(UUID.randomUUID().toString());
                newItem.setCanvas(canvas);
                newItem.setNumberOfCanvases(quantity);
                cart.getItems().add(newItem);
            }

            return cartRepository.save(cart);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Cart data is invalid ", e);

        } catch (DataAccessException e) {
            throw new RuntimeException(" Could not put Canvas in cart", e);
        }

    }

    // tar bort vara ifrån kundvagnen
    public Cart removeCanvasFromCart(String cartId, String canvasId) {
        try {
            Cart cart = getCartById(cartId);
            Canvas canvas = canvasRepository.findById(canvasId)
                    .orElseThrow(() -> new IllegalArgumentException("Canvas not found with id: " + canvasId));

            if (cart != null && canvas != null) {
                cart.getItems().removeIf(item -> item.getCanvas().getId().equals(canvasId));
                return cartRepository.save(cart);
            }
            return cart;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Canvas data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not delete canvas with id: " + canvasId, e);
        }

    }

    // räkna ut totalpriset i kundvagnen
    public BigDecimal getTotalPrice(String cartId) {
        try {
            Cart cart = getCartById(cartId);
            BigDecimal totalPrice = BigDecimal.ZERO;
            // leverans avgift
            BigDecimal deliveryFee = new BigDecimal("49");
            // här är gränsen för fri frakt
            BigDecimal freeDeliveryThreshold = new BigDecimal("200");
            // lägger ihop priset för varje tavla i kundvagnen
            for (var item : cart.getItems()) {
                totalPrice = totalPrice.add(new BigDecimal(item.getCanvas().getPrice())
                        .multiply(new BigDecimal(item.getNumberOfCanvases())));
            }

            // om totalpriset är under 200 kr läggs leveransavgiften på totalpriset
            if (totalPrice.compareTo(BigDecimal.ZERO) > 0 && totalPrice.compareTo(freeDeliveryThreshold) < 0) {
                totalPrice = totalPrice.add(deliveryFee);
            }
            return totalPrice;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price value for canvas: " + e);
        } catch (RuntimeException e) {
            throw new RuntimeException(" Something went wrong");

        }

    }

    // töm varukorgen när man betalt
    public Cart clearCart(String cartId) {
        try {

            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
            if (cart != null) {
                cart.getItems().clear();
                return cartRepository.save(cart);
            }
            return cart;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Cart data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not delete canvas from cart: " + cartId, e);
        }

    }

}
