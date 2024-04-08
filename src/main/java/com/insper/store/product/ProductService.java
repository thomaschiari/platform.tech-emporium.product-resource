package com.insper.store.product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel create(Product in) {
        return productRepository.save(new ProductModel(in));
    }

    public Product read(@NonNull String id) {
        return productRepository.findById(id).map(ProductModel::to).orElse(null);
    }

    public Product update(@NonNull String id, Product in) {
        ProductModel model = productRepository.findById(id).orElseThrow();
        model.update(in);
        return productRepository.save(model).to();
    }

    public void delete(@NonNull String id) {
        productRepository.deleteById(id);
    }

}