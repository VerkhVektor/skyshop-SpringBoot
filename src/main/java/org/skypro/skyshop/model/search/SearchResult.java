package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final String name;
    private final String id;
    private final String conentType;

    public SearchResult(String name, String id, String conentType) {
        this.name = name;
        this.id = id;
        this.conentType = conentType;
    }

    public static SearchResult fromSearcheble(Searchable search) {
        SearchResult result = new SearchResult(search.searchTerm(), search.getId().toString(), search.searchContent());
        return result;

    }

    public UUID getId() {
        return UUID.fromString(id);
    }

    public String getName() {
        return name;

    }

    public String getConentType() {
        return conentType;
    }

    @Override
    public String toString() {
        return "ID:  " + id + " имя: " + name + " тип контента: " + conentType;
    }
}

