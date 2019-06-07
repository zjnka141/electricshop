package com.vuidoan.electricshop.repository;

import com.vuidoan.electricshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Long countByName(String name);
    List<Product> findByCategoryId(int id);
    List<Product> findByProducerId(int id);
    List<Product> findByNameContainingIgnoreCase(String name);
}
