package de.seven.search.adapter.secondary.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Review")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productId;
    @Min(0)
    @Max(5)
    Integer score;
    String userId;

    public de.seven.search.domain.model.Review toDomainReview() {
        return de.seven.search.domain.model.Review.builder()
                .score(score)
                .userId(userId)
                .build();
    }

    public static Review fromDomainReview(de.seven.search.domain.model.Review domainReview) {
        return Review.builder()
                .userId(domainReview.getUserId())
                .score(domainReview.getScore())
                .build();
    }
}
