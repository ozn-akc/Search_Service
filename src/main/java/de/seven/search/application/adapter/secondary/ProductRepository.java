package de.seven.search.application.adapter.secondary;

import de.seven.search.domain.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Integer productId);

    List<Product> findAll();

    void delete(Product product);

    void delete(Integer productId);
}
