package com.nidhallourimi.query.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.nidhallourimi.core.data.ProductEntity} entity
 */
@Data
public class ProductRestModule {
    private String productId;
    private  String title;
    private BigDecimal price;
    private Integer quantity;
}