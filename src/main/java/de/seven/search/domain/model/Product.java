package de.seven.search.domain.model;

import de.seven.search.application.model.FilterCriteria;
import de.seven.search.application.model.SearchCriteria;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class Product {
    Integer productId;
    List<String> images;
    Double price;
    Address address;
    List<Review> reviews;
    List<LocalDate> rentedDays;
    List<Attribute> attributes;
    List<Bed> beds;
    Integer maxGuests;
    Integer bedrooms;
    Integer bathrooms;

    public boolean doesProductMatchCriteria(SearchCriteria search) {
        return (address.getCountry().equals(search.getCountry())
                && search.getGuests() <= maxGuests
                && rentedDays.stream().noneMatch(rented -> search.getDateRange().stream()
                .anyMatch(rented::equals)));
    }

    public boolean doesProductMatchCriteria(FilterCriteria filter) {
        return true;
    }


}
