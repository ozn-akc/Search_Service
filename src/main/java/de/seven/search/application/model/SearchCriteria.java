package de.seven.search.application.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class SearchCriteria {
    String country;
    List<LocalDate> dateRange;
    Integer guests;
}
