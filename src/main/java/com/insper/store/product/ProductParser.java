package com.insper.store.product;

public class ProductParser {

    public static Product from(ProductIn in) {
        return Product.builder()
            .name(in.name())
            .description(in.description())
            .price(in.price())
            .stock(in.stock())
            .build();
    }

    public static ProductOut toOut(ProductModel model) {
        return ProductOut.builder()
            .id(model.id())
            .name(model.name())
            .description(model.description())
            .price(model.price())
            .stock(model.stock())
            .build();
    }

    public static ProductModel toModel(ProductIn in) {
        return ProductModel.builder()
            .name(in.name())
            .description(in.description())
            .price(in.price())
            .stock(in.stock())
            .build();
    }

}