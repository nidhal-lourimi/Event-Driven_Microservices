package com.nidhallourimi.command.rest;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CreateProductRestModel {
    @NotBlank(message = "Product title is a required field")
    private String title;
    @Min(value = 1,message = "Price cannot be lower than 1 TND")
    private BigDecimal price;
    @Min(value = 1 ,message = "Quantity cannot be lower than 1")
    @Max(value = 1000,message = "Quantity cannot be higher than 1000")
    private Integer quantity;
}
