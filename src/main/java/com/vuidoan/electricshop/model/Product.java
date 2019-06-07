package com.vuidoan.electricshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vuidoan.electricshop.validation.UniqueProductName;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="producer_id")
    private Producer producer;
}
