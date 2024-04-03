package de.seven.search.application.adapter.secondary;

import de.seven.search.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    Product findById(String productId);

    List<Product> findAll();

    void delete(String productId);
}
