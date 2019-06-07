package com.vuidoan.electricshop.repository;

import com.vuidoan.electricshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
