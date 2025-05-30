package com.data.sesson15_webjava.repository;

import com.data.sesson15_webjava.model.Product2;
import com.data.sesson15_webjava.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Product2Repository {
    public List<Product2> findAll() {
        List<Product2> products = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM product")) {
            while (rs.next()) {
                Product2 p = new Product2();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product2 findById(int id) {
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product2 p = new Product2();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
