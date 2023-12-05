package com.ra.serviceImp;

import com.ra.model.Categories;
import com.ra.repository.CategoriesRepository;
import com.ra.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImp implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> displayData(String catalogName, int page, int size, String direction, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        List<Categories> listCatalog = categoriesRepository.findByCatalogName(catalogName, pageable).getContent();
        return listCatalog;
    }

    @Override
    public List<Integer> getListPage(String catalogName, int size) {
        int countCatalog = categoriesRepository.countByCatalogNameContains(catalogName);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < (int) Math.ceil((double) countCatalog / (double) size); i++) {
            listPage.add(i + 1);
        }
        return listPage;
    }

    @Override
    public Categories findById(int catalogId) {
        try {
            Categories catalogById = categoriesRepository.findById(catalogId).get();
            return catalogById;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Categories> getAllCatalog() {
        return categoriesRepository.findAll();
    }

    @Override
    public boolean save(Categories catalog) {
        try {
            categoriesRepository.save(catalog);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Categories catalog) {
        try {
            categoriesRepository.save(catalog);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int catalogId) {
        try {
            categoriesRepository.deleteById(catalogId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
