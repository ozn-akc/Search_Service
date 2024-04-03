package de.seven.search.application.service;

import de.seven.search.application.adapter.secondary.ProductRepository;
import de.seven.search.application.model.FilterCriteria;
import de.seven.search.application.model.SearchCriteria;
import de.seven.search.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * @param search Suchparameter beinhalten den Zeitraum, den Ort und die Anzahl an Gästen
     * @return Eine Liste von Produkten die auf die Parameter zutreffen
     */
    public List<Product> searchProducts(SearchCriteria search){
        List<Product> result = productRepository.findAll();
        return result.stream()
                .filter(search::doesProductMatchSearchCriteria).toList();
    }

    /**
     * @param search Suchparameter beinhalten den Zeitraum, den Ort und die Anzahl an Gästen
     * @param filter Filter sind alle anderen Attribute die ein Produkt beschreiben
     * @return Eine Liste von Produkten die auf die Parameter zutreffen
     */
    public List<Product> searchProducts(SearchCriteria search, FilterCriteria filter){
        return searchProducts(search).stream()
                .filter(filter::doesProductMatchFilterCriteria).toList();
    }

}
