package vn.edu.ntu.tuongnghi.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.tuongnghi.controller.ICartController;
import vn.edu.ntu.tuongnghi.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {
    TextView txtShoppingCart;
    ICartController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        controller = (ICartController) getApplication();
        addView();
    }

    private void addView() {
        txtShoppingCart = findViewById(R.id.textCart);
        displayShoppingCart();
    }

    private void displayShoppingCart() {
        List<Product> shoppingCart;
        shoppingCart = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for(Product p:shoppingCart) {
            builder.append(p.getName()).append("\t\t").append(p.getPrice()).append("vnd\n");
        }
        if(builder.toString().length() > 0)
            txtShoppingCart.setText(builder.toString());
        else
            txtShoppingCart.setText("Không có mặt hàng nào trong giỏ hàng");
    }
}
