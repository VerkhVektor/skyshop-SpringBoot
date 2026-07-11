package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    public void addProduct(UUID productId) {
        if (this.products.containsKey(productId)) {
            products.put(productId, this.products.get(productId) + 1);
        } else {
            products.computeIfAbsent(productId, key -> 1);
        }


    }

    public Map<UUID, Integer> getProducts() {
        System.out.println("getProducts" + this.products.toString());
        return Collections.unmodifiableMap(this.products);
    }
}
