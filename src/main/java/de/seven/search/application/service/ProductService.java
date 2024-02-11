package de.seven.search.application.service;

import de.seven.search.application.adapter.secondary.ProductRepository;
import de.seven.search.domain.model.Product;

import java.util.List;
import java.util.Map;

public class ProductService {

    //TODO Hier den Adapter einsetzen
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public void deleteProduct(Integer productId){
        productRepository.delete(productId);
    }

    public Product findProductById(Integer productId){
        return productRepository.findById(productId);
    }

    public List<Product> findAll(Map<String, String> product){
        return productRepository.findAll();
    }

}
