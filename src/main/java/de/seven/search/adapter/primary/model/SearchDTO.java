package de.seven.search.adapter.primary.model;

import de.seven.search.application.model.FilterCriteria;
import de.seven.search.application.model.SearchCriteria;
import lombok.Getter;

@Getter
public class SearchDTO {
    SearchCriteria searchCriteria;
    FilterCriteria filterCriteria;
}
