package de.seven.search.application.model;

import de.seven.search.domain.model.Attribute;
import de.seven.search.domain.model.Bed;
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

    public boolean isInPriceRange(Double price){
        if( minPrice == null && maxPrice == null){
            return true;
        }else if(minPrice == null){
            return price < maxPrice;
        }else if(maxPrice == null){
            return minPrice < price;
        }
        return minPrice < price && price < maxPrice;
    }

    public boolean containsAllAttributes(List<Attribute> attributeList){
        if(attributes == null){
            return true;
        }
        return new HashSet<>(attributeList).containsAll(attributes);
    }

    public boolean containsAllBeds(List<Bed> bedList){
        if(attributes == null){
            return true;
        }
        return new HashSet<>(bedList).containsAll(beds);
    }

    public boolean hasSufficientRating(List<Integer> rating){
        if(minReviewRating == null){
            return true;
        }
        return rating.stream().anv;
    }
}