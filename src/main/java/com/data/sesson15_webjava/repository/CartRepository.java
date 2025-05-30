package com.data.sesson15_webjava.repository;

import com.data.sesson15_webjava.model.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> findByUserId(int userId);
    Cart findByUserIdAndProductId(int userId, int productId);
    void save(Cart cart);
    void update(Cart cart);
    void delete(int idCart);
}
