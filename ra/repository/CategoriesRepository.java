package com.ra.repository;

import com.ra.model.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
    @Query("select c from Categories c where c.catalogName like %?1%")
    Page<Categories> findByCatalogName(String catalogName, Pageable pageable);
    int countByCatalogNameContains(String catalogName);
}
