package vn.edu.ntu.tuongnghi.controller;

import java.util.List;

import vn.edu.ntu.tuongnghi.model.Product;

public interface ICartController {
    List<Product> getAllProduct();

    public boolean addToCart(Product product);

    public List<Product> getShoppingCart();

    public void clearShoppingCart();
}
