package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basicPrice;
    private int discountPercent;
    public DiscountedProduct(String name, int basicPrice, int discountPercent) {
        super(name);
        this.id = UUID.randomUUID();
        this.basicPrice = basicPrice;
        this.discountPercent = discountPercent;
        if (basicPrice <= 0) {
            throw new IllegalArgumentException("цена должна быть выше 0!");
        }
        if ((discountPercent < 0) || (discountPercent > 100)) {
            throw new IllegalArgumentException("Процент скидки должен быть от 0 до 100!");
        }
    }
    @Override
    public int getPrice() {
        return basicPrice - discountPercent*basicPrice/100;
    }
    @Override
    public String toString() {
        return name + " :" + getPrice() + " (скидка " + discountPercent + " %)";
    }
    @Override
    public boolean isSpecial() {
        return true;
    }

}
