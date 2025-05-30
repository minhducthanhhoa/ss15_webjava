package com.data.sesson15_webjava.service;

import com.data.sesson15_webjava.model.Review;
import com.data.sesson15_webjava.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository repo = new ReviewRepository();

    public void addReview(Review review) {
        repo.addReview(review);
    }

    public List<Review> getReviewsByProduct(int productId) {
        return repo.getReviewsByProductId(productId);
    }
}
