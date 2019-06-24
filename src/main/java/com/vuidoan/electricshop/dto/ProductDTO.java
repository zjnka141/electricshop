package com.vuidoan.electricshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vuidoan.electricshop.controller.ProductController;
import com.vuidoan.electricshop.model.Category;
import com.vuidoan.electricshop.model.Producer;
import com.vuidoan.electricshop.validation.Create;
import com.vuidoan.electricshop.validation.UniqueProductName;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProductDTO {
    private int id;
    @UniqueProductName()
    @NotBlank(message = "Vui lòng nhập trường này")
    private String name;
    @Min(value = 100,message = "Giá tiền chưa hợp lệ")
    private double price;
    private Category category;
    private Producer producer;
}
