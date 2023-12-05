package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(
           UUID id
    ) {

        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possível encontrar uma categoria com o id '%s'", id)));
    }

    public Category findByName(
           String name
    ) {

        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possível encontrar uma categoria com o nome '%s'", name)));
    }
}
