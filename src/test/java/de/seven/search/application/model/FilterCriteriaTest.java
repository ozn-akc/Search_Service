package de.seven.search.application.model;

import de.seven.search.domain.model.Attribute;
import de.seven.search.domain.model.Bed;
import de.seven.search.domain.model.BedType;
import de.seven.search.domain.model.Review;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterCriteriaTest {

    @Test
    void priceIsInPriceRangeBothValuesPresentTest() {
        //Assign
        FilterCriteria toTest = FilterCriteria.builder().maxPrice(10.0).minPrice(4.0).build();

        //Act
        boolean inRangeResult = toTest.isInPriceRange(4.0);
        boolean outOfRangeOverResult = toTest.isInPriceRange(3.0);
        boolean outOfRangeUnderResult = toTest.isInPriceRange(11.0);

        //Assert
        assertTrue(inRangeResult);
        assertFalse(outOfRangeOverResult);
        assertFalse(outOfRangeUnderResult);
    }

    @Test
    void priceIsInPriceRangeMaxValuePresentTest() {
        //Assign
        FilterCriteria toTest = FilterCriteria.builder().maxPrice(10.0).build();
        boolean expected = true;

        //Act
        boolean inRangeResult = toTest.isInPriceRange(4.0);
        boolean outOfRangeResult = toTest.isInPriceRange(11.0);

        //Assert

        //Assert
        assertTrue(inRangeResult);
        assertFalse(outOfRangeResult);
    }

    @Test
    void priceIsInPriceRangeMinValuePresentTest() {
        //Assign
        FilterCriteria toTest = FilterCriteria.builder().maxPrice(10.0).minPrice(4.0).build();
        boolean expected = true;

        //Act
        boolean inRangeResult = toTest.isInPriceRange(4.0);
        boolean outOfRangeResult = toTest.isInPriceRange(11.0);

        //Assert
        assertTrue(inRangeResult);
        assertFalse(outOfRangeResult);
    }

    @Test
    void priceIsInPriceRangeNoValuePresentTest() {
        //Assign
        FilterCriteria toTest = FilterCriteria.builder().build();

        //Act
        boolean inRangeResult = toTest.isInPriceRange(4.0);
        boolean outOfRangeResult = toTest.isInPriceRange(11.0);

        //Assert
        assertTrue(inRangeResult);
        assertTrue(outOfRangeResult);
    }

    @Test
    void containsAllAttributesWithValuesTest() {
        //Assign
        List<Attribute> withAttributes = List.of(Attribute.TV, Attribute.KUECHE, Attribute.WLAN);
        List<Attribute> withoutAttributes = List.of(Attribute.TV, Attribute.TROCKNER, Attribute.WASCHMASCHINE);
        FilterCriteria toTest = FilterCriteria.builder().attributes(List.of(Attribute.TV, Attribute.WLAN)).build();

        //Act
        boolean withAttributesResult = toTest.containsAllAttributes(withAttributes);
        boolean withoutAttributeResult = toTest.containsAllAttributes(withoutAttributes);

        //Assert
        assertTrue(withAttributesResult);
        assertFalse(withoutAttributeResult);
    }

    @Test
    void containsAllAttributesNoValuesTest() {
        //Assign
        List<Attribute> withAttributes = List.of(Attribute.TV, Attribute.KUECHE, Attribute.WLAN);
        List<Attribute> withoutAttributes = List.of(Attribute.TV, Attribute.TROCKNER, Attribute.WASCHMASCHINE);
        FilterCriteria toTest = FilterCriteria.builder().build();

        //Act
        boolean withAttributesResult = toTest.containsAllAttributes(withAttributes);
        boolean withoutAttributeResult = toTest.containsAllAttributes(withoutAttributes);

        //Assert
        assertTrue(withAttributesResult);
        assertTrue(withoutAttributeResult);
    }

    @Test
    void containsAllAttributesEmptyTest() {
        //Assign
        List<Attribute> withAttributes = List.of(Attribute.TV, Attribute.KUECHE, Attribute.WLAN);
        List<Attribute> withoutAttributes = List.of(Attribute.TV, Attribute.TROCKNER, Attribute.WASCHMASCHINE);
        FilterCriteria toTest = FilterCriteria.builder().attributes(List.of()).build();

        //Act
        boolean withAttributesResult = toTest.containsAllAttributes(withAttributes);
        boolean withoutAttributeResult = toTest.containsAllAttributes(withoutAttributes);

        //Assert
        assertFalse(withAttributesResult);
        assertFalse(withoutAttributeResult);
    }

    @Test
    void containsAllBedsTest() {
        //Assign
        Bed queen = Bed.builder().amount(1).type(BedType.QUEENSIZE_DOPPELBETT).build();
        Bed couch = Bed.builder().amount(1).type(BedType.SCHLAFCOUCH).build();
        List<Bed> withBed = List.of(queen);
        List<Bed> withoutBed = List.of(couch);
        FilterCriteria toTest = FilterCriteria.builder().beds(List.of(queen)).build();

        //Act
        boolean withAttributesResult = toTest.containsAllBeds(withBed);
        boolean withoutAttributeResult = toTest.containsAllBeds(withoutBed);

        //Assert
        assertTrue(withAttributesResult);
        assertFalse(withoutAttributeResult);
    }

    @Test
    void hasSufficientRatingTest() {
        //Assign
        List<Review> badReviews = List.of(Review.builder().value(2).build(), Review.builder().value(4).build());
        List<Review> goodReviews = List.of(Review.builder().value(5).build(), Review.builder().value(4).build());
        FilterCriteria toTest = FilterCriteria.builder().minReviewRating(4).build();

        //Act
        boolean badResult = toTest.hasSufficientRating(badReviews);
        boolean goodResult = toTest.hasSufficientRating(goodReviews);

        //Assert
        assertTrue(goodResult);
        assertFalse(badResult );
    }

    @Test
    void hasSufficientBedsTest() {
        //Assign
        List<Bed> enoughBed = List.of(Bed.builder().build(), Bed.builder().build());
        List<Bed> lessBed = List.of(Bed.builder().build());
        FilterCriteria toTest = FilterCriteria.builder().minBeds(2).build();

        //Act
        boolean badResult = toTest.hasSufficientBeds(lessBed);
        boolean goodResult = toTest.hasSufficientBeds(enoughBed);

        //Assert
        assertTrue(goodResult);
        assertFalse(badResult);
    }

    @Test
    void hasSufficientBedroomsTestTest() {
        //Assign
        FilterCriteria toTest = FilterCriteria.builder().minBedrooms(4).build();

        //Act
        boolean badResult = toTest.hasSufficientBedrooms(2);
        boolean goodResult = toTest.hasSufficientBedrooms(5);

        //Assert
        assertTrue(goodResult);
        assertFalse(badResult);
    }

    @Test
    void hasSufficientBathroomsTest() {
        //Assign
        FilterCriteria toTest = FilterCriteria.builder().minBathrooms(4).build();

        //Act
        boolean badResult = toTest.hasSufficientBathrooms(2);
        boolean goodResult = toTest.hasSufficientBathrooms(5);

        //Assert
        assertTrue(goodResult);
        assertFalse(badResult);
    }
}