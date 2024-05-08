package de.seven.search.adapter.secondary.mongodb;

import de.seven.search.adapter.secondary.mongodb.model.ProductDTO;
import de.seven.search.application.adapter.secondary.ProductRepository;
import de.seven.search.domain.model.Product;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongodb")
@RequiredArgsConstructor
public class MongoDBRepository  implements ProductRepository {

    private final ProductMongoRepository productMongoRepository;

    @Override
    @Transactional
    public Product save(Product domainProduct) {
        ProductDTO product = ProductDTO.fromDomainProduct(domainProduct);
        productMongoRepository.save(product);
        return product.toDomainProduct();
    }

    @Override
    public Product findById(String productId) {
        return productMongoRepository.findById(productId).orElse(ProductDTO.builder().build()).toDomainProduct();
    }

    @Override
    public List<Product> findAll() {
        return productMongoRepository.findAll().stream().map(ProductDTO::toDomainProduct).toList();
    }

    @Override
    public void delete(String productId) {
        productMongoRepository.deleteById(productId);
    }
}
