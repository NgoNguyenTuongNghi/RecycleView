package vn.edu.ntu.tuongnghi.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.tuongnghi.model.Product;

public class CartController extends Application implements ICartController {
    List<Product> productList = new ArrayList<>();
    List<Product> shoppingCart = new ArrayList<>();

    public CartController() {
        productList.add(new Product("Chuối đá Nha Trang", 25000, "Chuối to"));
        productList.add(new Product("Sầu riêng Khánh Sơn", 95000, "Hạt lép"));
        productList.add(new Product("Khoai lang", 35000, "Tiêu chuẩn châu Âu"));
        productList.add(new Product("Xoài cát Hòa Lộc", 55000, "Xoài loại 1"));
        productList.add(new Product("Nho không hạt", 65000, "Nho Úc"));
        productList.add(new Product("Táo Bắc Môn", 55000, "Táo Trung nội địa"));
    }

    @Override
    public List<Product> getAllProduct() {
        return productList;
    }

    @Override
    public boolean addToCart(Product product) {
        if(shoppingCart.contains(product))
            return false;
        shoppingCart.add(product);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }
}
