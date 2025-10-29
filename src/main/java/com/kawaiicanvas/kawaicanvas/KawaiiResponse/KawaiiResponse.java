package com.kawaiicanvas.kawaicanvas.KawaiiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// skapade en generell responsklass för API:et
// fick hjälp med logiken från copilot då jag vill ha en generell responsklass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KawaiiResponse<T> {
    private String message;
    private T data;

    // hjälpmetod för att skapa framgångsrespons
    public static <T> KawaiiResponse<T> success(String message, T data) {
        return new KawaiiResponse<>(message, data);
    }

    // hjälpmetod för att skapa felrespons
    public static <T> KawaiiResponse<T> error(String message) {
        return new KawaiiResponse<>(message, null);
    }
}
