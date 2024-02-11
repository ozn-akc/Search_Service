package de.seven.search.domain.model;

import lombok.Getter;

@Getter
public enum BedType {
    SCHLAFCOUCH("Schlafcouch"),
    QUEENSIZE_DOPPELBETT("Queensize Doppelbett");

    BedType(String type){
        this.type = type;
    }

    private final String type;
}
