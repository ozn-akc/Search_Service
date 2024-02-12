package de.seven.search.application.model;

import de.seven.search.domain.model.Product;
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

    public boolean doesProductMatchSearchCriteria(Product product) {
        return (product.getAddress().getCountry().equals(country)
                && guests <= product.getMaxGuests()
                && product.getRentedDays().stream().noneMatch(rented -> dateRange.stream()
                .anyMatch(rented::equals)));
    }
}
