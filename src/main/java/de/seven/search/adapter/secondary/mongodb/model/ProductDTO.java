package de.seven.search.adapter.secondary.mongodb.model;

import de.seven.search.domain.model.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    List<String> images;
    Price price;
    Address address;
    List<Review> reviews;
    List<LocalDate> rentedDays;
    List<Attribute> attributes;
    List<Bed> beds;
    Integer bedrooms;
    Integer bathrooms;

    public Product toDomainProduct(){
        return Product.builder()
                .productId(id)
                .images(images)
                .price(price)
                .address(address)
                .reviews(reviews)
                .rentedDays(rentedDays)
                .beds(beds)
                .attributes(attributes)
                .bedrooms(bedrooms)
                .bathrooms(bathrooms)
                .build();
    }

    public static ProductDTO fromDomainProduct(Product domainProduct) {
        return ProductDTO.builder()
                .id(domainProduct.getProductId())
                .images(domainProduct.getImages())
                .price(domainProduct.getPrice())
                .address(domainProduct.getAddress())
                .bathrooms(domainProduct.getBathrooms())
                .bedrooms(domainProduct.getBedrooms())
                .beds(domainProduct.getBeds())
                .attributes(domainProduct.getAttributes())
                .rentedDays(domainProduct.getRentedDays())
                .reviews(domainProduct.getReviews())
                .build();
    }
}