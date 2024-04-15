package de.seven.search.adapter.secondary.postgresql.model;

import de.seven.search.domain.model.Bed;
import de.seven.search.domain.model.BedType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Bed")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BedDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productId;
    Integer amount;
    BedType type;

    public Bed toDomainBed() {
        return Bed.builder()
                .amount(amount)
                .type(type)
                .build();
    }

    public static BedDTO fromDomainBed(Bed domainBed) {
        return BedDTO.builder()
                .amount(domainBed.getAmount())
                .type(domainBed.getType())
                .build();
    }
}
