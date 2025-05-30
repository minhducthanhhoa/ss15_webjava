package com.data.sesson15_webjava.controller;

import com.data.sesson15_webjava.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {
    private List<Product> productList = new ArrayList<>(Arrays.asList(
            new Product("P001", "iPhone 14", 999),
            new Product("P002", "Samsung S23", 899),
            new Product("P003", "Xiaomi Note", 499)
    ));

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("keyword", "");
        model.addAttribute("products", null);
        return "search";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("error", "Từ khóa không được để trống");
            model.addAttribute("products", null);
        } else {
            List<Product> result = productList.stream()
                    .filter(p -> p.getId().toLowerCase().contains(keyword.toLowerCase())
                            || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());

            model.addAttribute("products", result);
        }

        model.addAttribute("keyword", keyword);
        return "search";
    }
}
