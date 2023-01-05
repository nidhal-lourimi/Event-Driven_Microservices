package com.nidhallourimi.command.rest;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data

public class CreateRestOrderModel {
    @NotBlank(message = "productId should not be empty")
    private String productId;
    @Min(value = 1,message = "quantity should be superior or equals to 1")
    @Max(value = 1000,message = "quantity should be inferior or equals to 1000")
    private Integer quantity;
    @NotBlank
    private String addressId;
}
