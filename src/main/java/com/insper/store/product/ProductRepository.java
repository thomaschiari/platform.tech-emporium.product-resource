package com.insper.store.product;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Integer> { // Changed ID type to Integer

    Optional<ProductModel> findByName(String name);
}