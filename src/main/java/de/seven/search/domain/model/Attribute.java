package de.seven.search.domain.model;

import lombok.Getter;

@Getter
public enum Attribute {
    KUECHE("Küche"),
    WLAN("WLAN"),
    ARBEITSPLATZ("Arbeitsplatz"),
    TV("TV"),
    WASCHMASCHINE("Waschmaschine"),
    TROCKNER("Trockner");

    Attribute(String attribute){
        this.attribute = attribute;
    }

    private final String attribute;
}
