package de.seven.search.adapter.secondary.postgresql.model;

import de.seven.search.domain.model.Attribute;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Attribute")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productId;
    Attribute attribute;
}
