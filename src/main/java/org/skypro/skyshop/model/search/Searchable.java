package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {
    @JsonIgnore
    String searchTerm();

    @JsonIgnore
    String searchContent();

    default String getStringRepresentation() {
        return searchTerm() + " — " + searchContent();
    }

    UUID getId();



}
