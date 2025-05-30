package com.data.sesson15_webjava.controller;

import com.data.sesson15_webjava.service.Product2Service;
import com.data.sesson15_webjava.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Product2Controller {
    @Autowired
    private Product2Service productService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product2")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "listProduct";
    }

    @GetMapping("/product2/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("reviews", reviewService.getReviewsByProduct(id));
        return "productDetail";
    }
}
