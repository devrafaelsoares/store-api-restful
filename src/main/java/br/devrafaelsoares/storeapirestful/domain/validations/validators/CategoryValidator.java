package br.devrafaelsoares.storeapirestful.domain.validations.validators;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.validations.constraints.Categories;
import br.devrafaelsoares.storeapirestful.services.CategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoryValidator implements ConstraintValidator<Categories, String> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {

        if(category == null || category.isEmpty()) return true;

        return categoryService.findAll()
                .stream()
                .map(Category::getName)
                .toList()
                .contains(category);
    }
}
