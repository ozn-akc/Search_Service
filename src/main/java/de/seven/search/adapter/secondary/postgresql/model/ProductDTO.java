package de.seven.search.adapter.secondary.postgresql.model;

import de.seven.search.domain.model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity(name = "Product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    List<ImageDTO> images;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    PriceDTO price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    AddressDTO address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    List<ReviewDTO> reviews;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    List<RentedDayDTO> rentedDays;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    List<AttributeDTO> attributes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    List<BedDTO> beds;
    Integer bedrooms;
    Integer bathrooms;

    public Product toDomainProduct(){
        return Product.builder()
                .productId(productId)
                .images(Optional.ofNullable(images)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(image -> image.url)
                        .toList())
                .price(Optional.ofNullable(price)
                        .map(PriceDTO::toDomainPrice)
                        .orElse(null))
                .address(Optional.ofNullable(address)
                        .map(AddressDTO::toDomainAddress)
                        .orElse(null))
                .reviews(Optional.ofNullable(reviews)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(ReviewDTO::toDomainReview)
                        .toList())
                .rentedDays(Optional.ofNullable(rentedDays)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(rentedDay -> rentedDay.day)
                        .toList())
                .beds(Optional.ofNullable(beds)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(BedDTO::toDomainBed)
                        .toList())
                .attributes(Optional.ofNullable(attributes)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(attribute -> attribute.attribute)
                        .toList())
                .bedrooms(bedrooms)
                .bathrooms(bathrooms)
                .build();
    }

    public static ProductDTO fromDomainProduct(Product domainProduct) {
        ProductDTO product = ProductDTO.builder()
                .productId(domainProduct.getProductId())
                .images(
                        Optional.ofNullable(domainProduct.getImages())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(image -> ImageDTO.builder().url(image).build())
                                .toList()
                )
                .bathrooms(domainProduct.getBathrooms())
                .bedrooms(domainProduct.getBedrooms())
                .beds(
                        Optional.ofNullable(domainProduct.getBeds())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(BedDTO::fromDomainBed)
                                .toList()
                )
                .attributes(
                        Optional.ofNullable(domainProduct.getAttributes())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(attribute -> AttributeDTO.builder().attribute(attribute).build())
                                .toList()
                )
                .rentedDays(
                        Optional.ofNullable(domainProduct.getRentedDays())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(rentedDay -> RentedDayDTO.builder().day(rentedDay).build())
                                .toList()
                )
                .reviews(
                        Optional.ofNullable(domainProduct.getReviews())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(ReviewDTO::fromDomainReview)
                                .toList()
                )
                .build();
        product.setPrice(Optional.ofNullable(domainProduct.getPrice())
                .map(price -> PriceDTO.fromDomainPrice(price, product))
                .orElse(null));
        product.setAddress(Optional.ofNullable(domainProduct.getAddress())
                .map(address -> AddressDTO.fromDomainAddress(address, product))
                .orElse(null));
        return product;
    }
}