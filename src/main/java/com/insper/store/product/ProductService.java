package com.insper.store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @CachePut(value = "products", key = "#result.id")
    public ProductModel create(ProductIn in) {
        return repository.save(
                new ProductModel()
                        .name(in.name())
                        .description(in.description())
                        .price(in.price())
                        .stock(in.stock())
        );
    }

    @Cacheable(value = "products", key = "#id")
    public ProductModel read(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for this id :: " + id));
    }

    @CachePut(value = "products", key = "#id")
    public ProductModel update(Integer id, ProductIn in) {
        ProductModel existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for this id :: " + id));
        existingProduct.name(in.name())
                        .description(in.description())
                        .price(in.price())
                        .stock(in.stock());
        return repository.save(existingProduct);
    }

    @CacheEvict(value = "products", key = "#id")
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "allProducts")
    public List<ProductModel> readAll() {
        Iterable<ProductModel> allProducts = repository.findAll();
        return StreamSupport.stream(allProducts.spliterator(), false)
                            .collect(Collectors.toList());
    }
}
