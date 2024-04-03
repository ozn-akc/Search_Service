package de.seven.search.adapter.secondary.postgresql.model;

import de.seven.search.domain.model.BedType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "Bed")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productId;
    Integer amount;
    BedType type;

    public de.seven.search.domain.model.Bed toDomainBed() {
        return de.seven.search.domain.model.Bed.builder()
                .amount(amount)
                .type(type)
                .build();
    }

    public static Bed fromDomainBed(de.seven.search.domain.model.Bed domainBed) {
        return Bed.builder()
                .amount(domainBed.getAmount())
                .type(domainBed.getType())
                .build();
    }
}
