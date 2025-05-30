package com.data.sesson15_webjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    private int idCart;
    private int idUser;
    private int idProduct;
    private int quantity;
}
