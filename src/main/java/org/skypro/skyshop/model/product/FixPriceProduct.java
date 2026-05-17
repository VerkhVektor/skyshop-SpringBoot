package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final short FIX_PRICE = 500;

    public FixPriceProduct(String name) {
        super(name);
        this.id = UUID.randomUUID();

    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return name + " :" + "Фиксированная цена " + FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


}
