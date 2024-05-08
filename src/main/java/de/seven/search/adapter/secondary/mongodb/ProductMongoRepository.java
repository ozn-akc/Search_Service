package de.seven.search.adapter.secondary.mongodb;

import de.seven.search.adapter.secondary.mongodb.model.ProductDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductDTO, String> {
}
