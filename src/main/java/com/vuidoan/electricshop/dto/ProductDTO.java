package com.vuidoan.electricshop.dto;

import com.vuidoan.electricshop.controller.ProductController;
import com.vuidoan.electricshop.model.Category;
import com.vuidoan.electricshop.model.Producer;
import com.vuidoan.electricshop.validation.Create;
import com.vuidoan.electricshop.validation.UniqueProductName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ProductDTO {
    private int id;
    @UniqueProductName()
    @NotBlank(message = "Vui lòng nhập trường này")
    @Pattern(regexp = "(^\\w+ \\d+)",message = "Tên sản phẩm không hợp lệ")
    private String name;
    private double price;
    private Category category;
    private Producer producer;
}
