package com.data.sesson15_webjava.controller;


import com.data.sesson15_webjava.model.Cart;
import com.data.sesson15_webjava.model.Product2;
import com.data.sesson15_webjava.service.CartService;
import com.data.sesson15_webjava.service.Product2Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final Product2Service productService;

    public CartController(CartService cartService, Product2Service productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        // Giả sử user đã đăng nhập, idUser lấy từ session
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";  // Chưa đăng nhập thì chuyển đến trang đăng nhập
        }

        cartService.addOrUpdateCart(userId, productId, quantity);
        return "redirect:/cart/view";
    }

    // Xem giỏ hàng
    @GetMapping("/view")
    public String viewCart(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        List<Cart> cartItems = cartService.getCartByUserId(userId);

        // Lấy chi tiết sản phẩm để hiển thị
        List<Product2> productsInCart = new java.util.ArrayList<>();
        double totalPrice = 0;
        for (Cart c : cartItems) {
            Product2 p = Product2Service.findById(c.getIdProduct());
            if (p != null) {
                productsInCart.add(p);
                totalPrice += p.getPrice() * c.getQuantity();
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("productsInCart", productsInCart);
        model.addAttribute("totalPrice", totalPrice);

        return "cartView.html";
    }
}
