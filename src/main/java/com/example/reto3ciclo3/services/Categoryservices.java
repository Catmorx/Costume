package com.example.reto3ciclo3.services;


import com.example.reto3ciclo3.Model.Category;
import com.example.reto3ciclo3.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Categoryservices {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getProduct(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category p) {
        if (p.getId() == null) {
            return categoryRepository.save(p);
        } else {
            Optional<Category> e = categoryRepository.getCategory(p.getId());
            if (e.isPresent()) {
                return p;
            } else {
                return categoryRepository.save(p);
            }
        }
    }

    public Category update(Category categoryModel) {
        if (categoryModel.getId() != null) {
            Optional<Category> specialty = categoryRepository.getCategory(categoryModel.getId());
            if (!specialty.isEmpty()) {
                if (categoryModel.getName() != null) {
                    specialty.get().setName(categoryModel.getName());
                }
                if (categoryModel.getDescription() != null) {
                    specialty.get().setDescription(categoryModel.getDescription());
                }
                if (categoryModel.getCostumes() != null) {
                    specialty.get().setCostumes(categoryModel.getCostumes());
                }
                categoryRepository.save(specialty.get());
                return specialty.get();
            } else {
                return categoryModel;
            }
        } else {
            return categoryModel;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Category> p = categoryRepository.getCategory(id);
        if (p.isPresent()) {
            categoryRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }
}


