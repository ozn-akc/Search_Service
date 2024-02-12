package de.seven.search.application.model;

import de.seven.search.domain.model.Attribute;
import de.seven.search.domain.model.Bed;
import de.seven.search.domain.model.Product;
import de.seven.search.domain.model.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;

@Getter
@Builder
public class FilterCriteria {
    Double minPrice;
    Double maxPrice;
    List<Attribute> attributes;
    List<Bed> beds;
    Integer minReviewRating;
    Integer minBeds;
    Integer minBedrooms;
    Integer minBathrooms;

    public boolean doesProductMatchFilterCriteria(Product product){
        return isInPriceRange(product.getPrice()) &&
                containsAllAttributes(product.getAttributes()) &&
                containsAllBeds(product.getBeds()) &&
                hasSufficientRating(product.getReviews()) &&
                hasSufficientBeds(product.getBeds()) &&
                hasSufficientBedrooms(product.getBedrooms()) &&
                hasSufficientBathrooms(product.getBathrooms());
    }

    public boolean isInPriceRange(Double price){
        if( minPrice == null && maxPrice == null){
            return true;
        }else if(minPrice == null){
            return price <= maxPrice;
        }else if(maxPrice == null){
            return minPrice <= price;
        }
        return minPrice <= price && price <= maxPrice;
    }

    public boolean containsAllAttributes(List<Attribute> attributeList){
        return attributes == null || (new HashSet<>(attributeList).containsAll(attributes) && !attributes.isEmpty());
    }

    public boolean containsAllBeds(List<Bed> bedList){
        return beds == null || new HashSet<>(bedList).containsAll(beds);
    }

    public boolean hasSufficientRating(List<Review> rating){
        return minReviewRating == null || minReviewRating <= rating.stream().mapToInt(Review::getValue).average().orElse(0) ;
    }

    public boolean hasSufficientBeds(List<Bed> beds){
        return minBeds == null || minBeds <= beds.size();
    }

    public boolean hasSufficientBedrooms(Integer amountBedrooms){
        return minBedrooms == null || minBedrooms <= amountBedrooms;
    }

    public boolean hasSufficientBathrooms(Integer amountBathrooms){
        return minBathrooms == null || minBathrooms <= amountBathrooms;
    }
}