package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StorageService {
    private final Map <UUID, Product> productsStorage;
    private final Map <UUID, Article> articlesStorage;
    public StorageService() {
        this.productsStorage = addProduct();
        this.articlesStorage = addArticle();
    }

    public Map<UUID, Product> getProductsStorage() {
        return productsStorage;
    }
    public Map<UUID, Article> getArticlesStorage() {
        return articlesStorage;

    }

    private HashMap<UUID, Product> addProduct() {
        HashMap<UUID, Product> products = new HashMap<>();
        Product product1 = new SimpleProduct("Телефон", 60000);
        Product product2 = new DiscountedProduct("Ноутбук", 50000, 20);
        Product product3 = new DiscountedProduct("МФУ", 40000, 15);
        Product product4 = new FixPriceProduct("Клавиатура");
        Product product5 = new SimpleProduct("Роутер", 10000);
        Product product6 = new SimpleProduct("Монитор", 20000);

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
        products.put(product4.getId(), product4);
        products.put(product5.getId(), product5);
        products.put(product6.getId(), product6);


        return products;
    }
    private HashMap<UUID, Article> addArticle() {
        HashMap<UUID, Article> articles = new HashMap<>();

        Article article1 = new  Article("Про уродов и Людей", "Статья про фильм Балабанова");
        Article article2 = new Article("Я Название статьи 2", "Текст второй статьи");
        Article article3 = new Article("В Название статьи 3", "Текст текст текст статьи длиииинннный!!");
        Article article4 = new Article("В Название статьи 4", "Текст текст текст статьи длиииинннный!!");
        Article article5 = new Article("Б Название статьи 5", "Текст текст текст статьи длиииинннный!!");
        Article article6 = new Article(" Название статьи 67", "Текст текст текст статьи длиииинннный!!");
        Article article7 = new Article("Название статьи 5454545", "Текст текст текст статьи длиииинннный!!");
        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
        articles.put(article3.getId(), article3);
        articles.put(article4.getId(), article4);
        articles.put(article5.getId(), article5);
        articles.put(article6.getId(), article6);
        articles.put(article7.getId(), article7);

        return articles;
    }
    public Map<UUID, Searchable>  getAllSearchables() {
        HashMap<UUID, Searchable> searchables = new HashMap<>();
        for (Map.Entry<UUID, Product> entry : productsStorage.entrySet()) {
            Product product = entry.getValue();
            searchables.put(entry.getKey(), product);
        }
        for (Map.Entry<UUID, Article> entry : articlesStorage.entrySet()) {
            Article article = entry.getValue();
            searchables.put(entry.getKey(), article);
        }

        return searchables;
    }


}
