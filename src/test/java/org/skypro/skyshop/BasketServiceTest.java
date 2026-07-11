package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    ProductBasket productBasket;
    @Mock
    StorageService storageService;
    @InjectMocks
    BasketService basketService;

    //Добавление несуществующего товара
    @Test
    public void whenProductIsNotPresent_thenThrowException(){
        UUID id = UUID.randomUUID();
        Mockito.when(storageService.getProductById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProduct(id);
        });

        verify(productBasket, never()).addProduct(ArgumentMatchers.any(UUID.class));
    }

    //Добавление существующего товара вызывает метод addProduct у мока ProductBasket

    @Test
    public void whenProductIsPresent_thenCallAddProduct() {
        Product product1 = new SimpleProduct("Телефон", 60000);
        Mockito.when(storageService.getProductById(product1.getId())).thenReturn(Optional.of(product1));
        basketService.addProduct(product1.getId());
        verify(productBasket).addProduct(product1.getId());
    }

    //Метод getUserBacket возвращает пустую корзину, если ProductBasket пуст.
    @Test
    public void whenProductBasketIsEmpty_thenGetUserBasketEmpty() {

        Mockito.when(productBasket.getProducts()).thenReturn( Collections.emptyMap());
        UserBasket userbasket = basketService.getUserBasket();
        assertNotNull(userbasket);
        assertTrue(userbasket.getItems().isEmpty());
    }

    //Метод getUserBasket возвращает заполненную корзину, если в ProductBasket есть товары.

    @Test
    public void whenUserBasketIsFull_thenGetUserBasketFull() {
        Map<UUID, Integer> userbasket = new HashMap<>();
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Product testProduct1 = new SimpleProduct("Бананы", 100);
        Product testProduct2 = new SimpleProduct("Яблоки", 200);
        userbasket.put(id, 1);
        userbasket.put(id2, 2);
        Mockito.when(productBasket.getProducts()).thenReturn( userbasket);
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.of(testProduct1));
        Mockito.when(storageService.getProductById(id2)).thenReturn(Optional.of(testProduct2));

       UserBasket result = basketService.getUserBasket();
       assertEquals(userbasket.size(), result.getItems().size());

    }




}
