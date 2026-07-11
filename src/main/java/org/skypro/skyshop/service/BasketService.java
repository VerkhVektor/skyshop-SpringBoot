package org.skypro.skyshop.service;

import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket products;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket products, StorageService storageService) {
        this.products = products;
        this.storageService = storageService;

    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isPresent()) {
            products.addProduct(id);
            System.out.println("продукт добавлен!!!!!!11");
        } else {
            throw new NoSuchProductException("Продукт с ID " + id + " не найден в хранилище");

        }


    }




    int a = 1;
    char b = '1';
    public UserBasket getUserBasket() {
        Map<UUID, Integer> product = products.getProducts();
        List<BasketItem> basketItems = product.entrySet().stream()
                .map(entry -> new BasketItem(
                        storageService.getProductById(entry.getKey()).get(),
                        entry.getValue()
                ))
                .toList();
        for (BasketItem basketItem : basketItems) {
            System.out.println(basketItem.toString());
        }
        System.out.println("List 2 должен быть равен List 1" + basketItems.toString());
        return new UserBasket(basketItems);
    }

}
