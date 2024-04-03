package de.seven.search.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class Product {
    String productId;
    List<String> images;
    Price price;
    Address address;
    List<Review> reviews;
    List<LocalDate> rentedDays;
    List<Attribute> attributes;
    List<Bed> beds;
    Integer bedrooms;
    Integer bathrooms;
}
