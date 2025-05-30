package com.data.sesson15_webjava.service;

import com.data.sesson15_webjava.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartByUserId(int userId);
    void addOrUpdateCart(int userId, int productId, int quantity);
    void removeCart(int idCart);
}
