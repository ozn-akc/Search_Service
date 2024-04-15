package de.seven.search.adapter.secondary.postgresql.model;

import de.seven.search.domain.model.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "productId")
    @MapsId
    private ProductDTO product;
    String country;
    String city;

    public Address toDomainAddress() {
        return Address.builder()
                .city(city)
                .country(country)
                .build();
    }

    public static AddressDTO fromDomainAddress(Address domainAddress, ProductDTO product) {
        return AddressDTO.builder()
                .city(domainAddress.getCity())
                .country(domainAddress.getCountry())
                .product(product)
                .build();
    }
}
