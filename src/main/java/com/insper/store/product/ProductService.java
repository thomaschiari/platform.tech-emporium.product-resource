package com.insper.store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductModel create(ProductIn in) {
        // Assuming ProductIn is a valid input class. If hashing/encoding is needed, implement here before saving.
        return repository.save(
                new ProductModel()
                        .name(in.name())
                        .description(in.description())
                        .price(in.price())
                        .stock(in.stock())
        );
    }

    public ProductModel read(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for this id :: " + id));
    }

    public ProductModel update(Integer id, ProductIn in) {
        ProductModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for this id :: " + id));
        return repository.save(
                model
                        .name(in.name())
                        .description(in.description())
                        .price(in.price())
                        .stock(in.stock())
        );
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<ProductModel> readAll() {
        Iterable<ProductModel> allProducts = repository.findAll();
        return StreamSupport.stream(allProducts.spliterator(), false)
                            .collect(Collectors.toList());
    }
    
}
