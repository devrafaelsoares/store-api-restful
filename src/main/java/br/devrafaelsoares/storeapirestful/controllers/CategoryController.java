package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.category.dto.CategoryCreateRequest;
import br.devrafaelsoares.storeapirestful.domain.category.dto.CategoryPatchRequest;
import br.devrafaelsoares.storeapirestful.domain.category.dto.CategoryResponse;
import br.devrafaelsoares.storeapirestful.domain.category.dto.CategoryUpdateRequest;
import br.devrafaelsoares.storeapirestful.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> index() {

        List<CategoryResponse> categoryResponseList = categoryService
                .findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();

        return ResponseEntity.ok(categoryResponseList);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> show(
            @PathVariable @NotNull UUID id
    ) {

        Category foundCategory = categoryService.findById(id);

        CategoryResponse categoryResponse = new CategoryResponse(foundCategory);

        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> store(
            @RequestBody @Valid CategoryCreateRequest categoryRequest
    ) {

        Category categorySaved = categoryService.save(categoryRequest);

        CategoryResponse categoryResponse = new CategoryResponse(categorySaved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/category")
                .path("/{id}")
                .buildAndExpand(categorySaved.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoryResponse);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable @NotNull UUID id,
            @RequestBody @Valid CategoryUpdateRequest categoryRequest
    ) {

        CategoryResponse categoryResponse = new CategoryResponse(categoryService.update(id, categoryRequest));

        return ResponseEntity.ok(categoryResponse);
    }

    @PatchMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable @NotNull UUID id,
            @RequestBody @Valid CategoryPatchRequest categoryRequest
    ) {

        CategoryResponse categoryResponse = new CategoryResponse(categoryService.update(id, categoryRequest));

        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @NotNull UUID id
    ) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
