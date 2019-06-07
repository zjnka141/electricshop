package com.vuidoan.electricshop.validation;

import com.vuidoan.electricshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName,String> {

    @Autowired
    ProductRepository productRepository;
    @Override
    public void initialize(UniqueProductName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return productRepository.countByName(value)<=0;
    }
}
