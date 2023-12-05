package com.ra.controller;

import com.ra.model.Categories;
import com.ra.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categoriesController")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    private static final int SIZE = 5;
    private static String catalogNameDefault = "";
    private static String directionDefault = "ASC";
    private static String sortByDefault = "catalogId";
    private static int pageDefault = 1;

    @GetMapping("/findCatalog")
    public ModelAndView displayCatalog(Optional<String> catalogName, Optional<Integer> page, Optional<String> direction, Optional<String> sortBy) {
        ModelAndView mav = new ModelAndView("categories-test");
        catalogNameDefault = catalogName.orElse(catalogNameDefault);
        directionDefault = direction.orElse(directionDefault);
        sortByDefault = sortBy.orElse(sortByDefault);
        pageDefault = page.orElse(pageDefault);
        List<Categories> listCatalog = categoriesService.displayData(catalogNameDefault, pageDefault - 1, SIZE, directionDefault, sortByDefault);
        List<Integer> listPages = categoriesService.getListPage(catalogNameDefault, SIZE);
        mav.addObject("listCatalog", listCatalog);
        mav.addObject("listPages", listPages);
        mav.addObject("catalogName", catalogNameDefault);
        mav.addObject("sortBy", sortByDefault);
        mav.addObject("direction", directionDefault);
        return mav;
    }

    @GetMapping(value = "/initUpdate")
    public String initCatalogUpdate(ModelMap modelMap, int catalogId){
        Categories catalogEdit = categoriesService.findById(catalogId);
        modelMap.addAttribute("catalogEdit",catalogEdit);
        return "updateCatalog";
    }

    @GetMapping(value = "/intCreate")
    public String createCatalog(ModelMap modelMap) {
        Categories catalogNew = new Categories();
        modelMap.addAttribute("catalogNew",catalogNew);
        return "newCatalog";
    }

    @PostMapping(value = "/create")
    public String saveCatalog(Categories categories){
        boolean result = categoriesService.save(categories);
        if (result) {
            return "redirect:findCatalog";
        }else {
            return "error";
        }
    }


    @PostMapping(value = "/update")
    public String updateCatalog(Categories categories){
        boolean result = categoriesService.update(categories);
        if (result) {
            return "redirect:findCatalog";
        }else {
            return "error";
        }
    }
    @GetMapping("/delete")
    public String deleteCatalog(int catalogId){
        boolean result = categoriesService.delete(catalogId);
        if (result){
            return "redirect:findCatalog";
        }else{
            return "error";
        }
    }

}
