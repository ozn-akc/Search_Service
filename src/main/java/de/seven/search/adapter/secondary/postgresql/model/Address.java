package de.seven.search.adapter.secondary.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "productId")
    @MapsId
    private Product product;
    String country;
    String city;

    public de.seven.search.domain.model.Address toDomainAddress() {
        return de.seven.search.domain.model.Address.builder()
                .city(city)
                .country(country)
                .build();
    }

    public static Address fromDomainAddress(de.seven.search.domain.model.Address domainAddress, Product product) {
        return Address.builder()
                .city(domainAddress.getCity())
                .country(domainAddress.getCountry())
                .product(product)
                .build();
    }
}
