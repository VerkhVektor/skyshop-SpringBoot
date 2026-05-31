package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public abstract class Product implements Searchable {
    protected String name;
    protected UUID id;
    //private final int cost;

    protected Product(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        //Название продукта не может быть пустой строкой или null. При этом пустая строка может быть также строкой,
        // состоящей только из пробелов: в этом случае правило не выполняется, так как это неправильное
        // название для продукта.
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым!");
        }
        //this.cost = cost;


    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return 0;
    }

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return name;
    }

    @JsonIgnore
    @Override
    public String searchContent() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return this.name.equals(product.getName());
    }


    @Override
    public int hashCode() {
        return this.name.hashCode();
    }


}

