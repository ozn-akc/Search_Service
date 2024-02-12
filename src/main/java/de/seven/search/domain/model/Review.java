package de.seven.search.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {
    Integer value;
    User user;
}
