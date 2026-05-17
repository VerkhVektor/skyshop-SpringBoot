package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return title;
    }

    @Override @JsonIgnore
    public String searchTerm() {
        return toString();
    }



    @Override @JsonIgnore
    public String searchContent() {
        return "ARTICLE";
    }

    @Override
    public UUID getId() {
        return this.id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return this.title.equals(article.title);
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }
}
