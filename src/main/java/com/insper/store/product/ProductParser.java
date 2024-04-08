package com.insper.store.product;

public class ProductParser {

    public static Product to(ProductIn in) {
        return Product.builder()
            .name(in.name())
            .price(in.price())
            .build();
    }

    public static ProductOut to(Product product) {
        return ProductOut.builder()
            .id(product.id())
            .name(product.name())
            .price(product.price())
            .build();
    }
    
}