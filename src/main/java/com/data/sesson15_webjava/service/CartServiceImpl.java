package com.data.sesson15_webjava.service;

import com.data.sesson15_webjava.model.Cart;
import com.data.sesson15_webjava.repository.CartRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void addOrUpdateCart(int userId, int productId, int quantity) {
        Cart existingCart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (existingCart != null) {
            // Cập nhật số lượng
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            cartRepository.update(existingCart);
        } else {
            // Thêm mới
            Cart cart = new Cart();
            cart.setIdUser(userId);
            cart.setIdProduct(productId);
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    @Override
    public void removeCart(int idCart) {
        cartRepository.delete(idCart);
    }
}
