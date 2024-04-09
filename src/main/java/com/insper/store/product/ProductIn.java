package com.insper.store.product;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder @Accessors(fluent = true, chain = true)
public record ProductIn (
    String name,
    String description,
    Double price,
    Integer stock
) { }