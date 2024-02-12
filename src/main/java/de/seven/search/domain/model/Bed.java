package de.seven.search.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Bed {
    Integer amount;
    BedType type;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bed other = (Bed) obj;
        return Objects.equals(amount, other.amount) &&
                type == other.type;
    }
}
