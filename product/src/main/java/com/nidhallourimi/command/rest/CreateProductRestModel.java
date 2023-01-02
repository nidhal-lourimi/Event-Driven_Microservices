package com.nidhallourimi.command.rest;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class CreateProductRestModel {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
