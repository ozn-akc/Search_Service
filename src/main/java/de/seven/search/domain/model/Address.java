package de.seven.search.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Address {
    String country;
    String city;
}
