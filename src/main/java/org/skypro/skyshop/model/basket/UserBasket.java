package org.skypro.skyshop.model.basket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    int total;

    public UserBasket(List<BasketItem> items) {
        this.items = Collections.unmodifiableList(items);
        this.total = items.stream()
                .mapToInt(i -> i.product.getPrice() * i.quantity)
                .sum();
    }

    public List<BasketItem> getItems() {
        return this.items;
    }

    public int getTotal() {
        return this.total;
    }
}
