package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchText) {
        Collection<SearchResult> result = storageService.getAllSearchables().values().stream()
                .filter(i -> i.searchTerm().contains(searchText))
                .map(i -> new SearchResult(i.searchTerm(), i.getId().toString(), i.searchContent()))
                .collect(Collectors.toList());
        System.out.println("SearchService.search" + result.toString());
        return result;
    }
}

