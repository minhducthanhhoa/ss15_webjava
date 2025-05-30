package com.data.sesson15_webjava.repository;

import com.data.sesson15_webjava.model.Cart;
import com.data.sesson15_webjava.util.ConnectionDB;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {
    @Override
    public List<Cart> findByUserId(int userId) {
        List<Cart> carts = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE idUser = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setIdCart(rs.getInt("idCart"));
                cart.setIdUser(rs.getInt("idUser"));
                cart.setIdProduct(rs.getInt("idProduct"));
                cart.setQuantity(rs.getInt("quantity"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public Cart findByUserIdAndProductId(int userId, int productId) {
        String sql = "SELECT * FROM cart WHERE idUser = ? AND idProduct = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setIdCart(rs.getInt("idCart"));
                cart.setIdUser(rs.getInt("idUser"));
                cart.setIdProduct(rs.getInt("idProduct"));
                cart.setQuantity(rs.getInt("quantity"));
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Cart cart) {
        if (cart.getIdCart() == 0) {
            String sql = "INSERT INTO cart (idUser, idProduct, quantity) VALUES (?, ?, ?)";
            try (Connection conn = ConnectionDB.openConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, cart.getIdUser());
                ps.setInt(2, cart.getIdProduct());
                ps.setInt(3, cart.getQuantity());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    cart.setIdCart(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            update(cart);
        }
    }

    @Override
    public void update(Cart cart) {
        String sql = "UPDATE cart SET quantity = ? WHERE idCart = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cart.getQuantity());
            ps.setInt(2, cart.getIdCart());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idCart) {
        String sql = "DELETE FROM cart WHERE idCart = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCart);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
