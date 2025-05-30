package com.data.sesson15_webjava.controller;

import com.data.sesson15_webjava.model.Review;
import com.data.sesson15_webjava.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/add")
    public String addReview(@ModelAttribute Review review) {
        reviewService.addReview(review);
        return "redirect:/product" + review.getProductId();
    }
}
