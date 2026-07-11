package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    StorageService storageService ;
    @InjectMocks
    private  SearchService searchService ;


    //Поиск в случае отсутствия объектов StorageService
    @Test
    public void whenStorageServiceisEmpry_thenSearchReturnsEmptyResult() {
        Mockito.when(storageService.getAllSearchables()).thenReturn(Collections.<UUID, Searchable>emptyMap());

        Collection <SearchResult> result = searchService.search("random");
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenStorageServiceHasNothing_thenSearchReturnsEmptyResult() {
        Map<UUID, Searchable> testProducts = new HashMap<>();
        Product product1 = new SimpleProduct("Телефон", 60000);
        testProducts.put(product1.getId(), product1);

        Mockito.when(storageService.getAllSearchables()).thenReturn(testProducts);
        Collection <SearchResult> result = searchService.search("Этого текста там нет");
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenStorageServiceHasResult_thenSearchReturnsResult() {
        Map<UUID, Searchable> testProducts = new HashMap<>();
        Product product1 = new SimpleProduct("Телефон", 60000);
        testProducts.put(product1.getId(), product1);

        Mockito.when(storageService.getAllSearchables()).thenReturn(testProducts);
        Collection <SearchResult> result = searchService.search("Телефон");
        assertTrue(result.stream().anyMatch(searchResult -> searchResult.getId().equals(product1.getId())));
        assertEquals(1, result.size());
        assertEquals(product1.getName(), result.stream().toList().get(0).getName());

    }


}
