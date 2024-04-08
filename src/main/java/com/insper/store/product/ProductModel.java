package com.insper.store.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "product")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_product")
    private Integer id;

    @Column(name = "name_product")
    private String name;

    @Column(name = "description_product")
    private String description;

    @Column(name = "price_product")
    private Double price;

    @Column(name = "stock_product")
    private Integer stock;

    public ProductModel(Product o) {
        this.id = o.id();
        this.name = o.name();
        this.description = o.description();
        this.price = o.price();
        this.stock = o.stock();
    }
    
    public Account to() {
        return Account.builder()
            .id(id)
            .name(name)
            .description(description)
            .price(price)
            .stock(stock)
            .build();
    }
    
}
