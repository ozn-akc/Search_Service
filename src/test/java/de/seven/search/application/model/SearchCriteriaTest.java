package de.seven.search.application.model;

import de.seven.search.domain.model.Address;
import de.seven.search.domain.model.Bed;
import de.seven.search.domain.model.BedType;
import de.seven.search.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchCriteriaTest {

    private final String COUNTRY = "Country";
    private final List<Bed> BEDS = List.of(Bed.builder().amount(3).type(BedType.SCHLAFCOUCH).build());
    private final Integer MAX_GUESTS = 3;
    private final Integer DATE_INDEX = 0;
    private final Integer DATE_ITERATIONS = 4;
    private final Product product = createProduct();

    /**
     * Test der überprüft, dass die Produkt-Methode doesProductMatchCriteria ein Produkt außerhalb der gebuchten Zeiten verfügbar ist
     */
    @Test
    void productMatchesSearchCriteriaDateTest() {
        //Assign
        SearchCriteria toTest = SearchCriteria.builder()
                .country(COUNTRY)
                .guests(MAX_GUESTS)
                .dateRange(createDates(DATE_ITERATIONS, 2))
                .build();
        boolean expected = true;

        //Act
        boolean result = toTest.doesProductMatchSearchCriteria(product);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test der überprüft, dass die Produkt-Methode doesProductMatchCriteria ein Produkt zu einem bereits gemieteten Zeitpunkt nicht c ist
     */
    @Test
    void productDoesNotMatcheSearchCriteriaDateTest() {
        //Assign
        SearchCriteria toTest = SearchCriteria.builder()
                .country(COUNTRY)
                .guests(MAX_GUESTS)
                .dateRange(createDates(DATE_INDEX, 3))
                .build();
        boolean expected = false;

        //Act
        boolean result = toTest.doesProductMatchSearchCriteria(product);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test der überprüft, dass die Produkt-Methode doesProductMatchCriteria ein Produkt mit zu geringer Kapazität blockiert
     */
    @Test
    void productDoesNotMatcheSearchCriteriaGuestsTest() {
        //Assign
        SearchCriteria toTest = SearchCriteria.builder()
                .country(COUNTRY)
                .guests(MAX_GUESTS+1)
                .dateRange(createDates(DATE_ITERATIONS, 2))
                .build();
        boolean expected = false;

        //Act
        boolean result = toTest.doesProductMatchSearchCriteria(product);

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method to create a Product using the final Parameters at the top
     * @return Product with default Values
     */
    private Product createProduct(){
        return Product.builder()
                .rentedDays(createDates(DATE_INDEX, DATE_ITERATIONS))
                .address(Address.builder().country(COUNTRY).build())
                .beds(BEDS)
                .build();
    }

    /**
     * Create a List of Localdates based on the current time, an Index and a limit
     * @param index defines the starting point added to today
     * @param iterations defines the amount of Dates that should be generated
     * @return List of LocalDate based on the current Date, the index and the iterations
     */
    private List<LocalDate> createDates(Integer index, Integer iterations){
        List<LocalDate> dates = new ArrayList<>();
        for(int i = index; i<index+iterations; i++){
            dates.add(LocalDate.now().plusDays(i));
        }
        return dates;
    }
}