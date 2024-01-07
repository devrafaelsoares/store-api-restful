package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.category.dto.CategoryCreateRequest;
import br.devrafaelsoares.storeapirestful.domain.category.dto.CategoryUpdate;
import br.devrafaelsoares.storeapirestful.exceptions.ForeignKeyAssociationException;
import br.devrafaelsoares.storeapirestful.repositories.CategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable(value = "categories")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Cacheable(value = "category", key = "#id")
    public Category findById(
            @NotNull UUID id
    ) {

        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possível encontrar uma categoria com o id '%s'", id)));
    }

    public Category findByName(
            @NotNull String name
    ) {

        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possível encontrar uma categoria com o nome '%s'", name)));
    }

    private boolean isExistsCategoryByName(
            @NotNull String name
    ) {
        return categoryRepository
                .findByName(name)
                .isPresent();
    }

    @CacheEvict(value = "categories", allEntries = true)
    public Category save(
            @NotNull CategoryCreateRequest categoryCreateRequest
    ) {

        if (isExistsCategoryByName(categoryCreateRequest.name())) {
            throw new EntityExistsException("Já existe uma categoria com esse nome cadastrada no sistema");
        }

        return categoryRepository.save(Category
                .builder()
                    .name(categoryCreateRequest.name())
                .build());
    }

    @CacheEvict(value = "categories", key = "#id", allEntries = true)
    public Category update(
            @NotNull UUID id,
            @NotNull CategoryUpdate categoryUpdateRequest
    ) {

        Category foundCategory = findById(id);

        updateCategoryData(categoryUpdateRequest, foundCategory);

        categoryRepository.save(foundCategory);

        return foundCategory;

    }

    @CacheEvict(value = "categories", key = "#id")
    public void delete(
            @NotNull UUID id
    ) {

        Category foundCategory = findById(id);

        if (!foundCategory.getProducts().isEmpty()) {
            throw new ForeignKeyAssociationException("Existem produtos vinculados a essa categoria");
        }

        categoryRepository.delete(foundCategory);
    }

    private void updateCategoryData(
            @NotNull CategoryUpdate categoryUpdateRequest,
            @NotNull Category category
    ) {
        category.setName(categoryUpdateRequest.getName() != null ? categoryUpdateRequest.getName() : category.getName());
    }
}
