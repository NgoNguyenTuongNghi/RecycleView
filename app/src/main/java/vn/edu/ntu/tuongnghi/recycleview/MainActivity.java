package vn.edu.ntu.tuongnghi.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.tuongnghi.controller.CartController;
import vn.edu.ntu.tuongnghi.controller.ICartController;
import vn.edu.ntu.tuongnghi.model.Product;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvListMH;
    Adapter adapter;
    List<Product> listProduct;
    ICartController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
    }

    private void addViews() {
        rvListMH = findViewById(R.id.rvMatHang);
        listProduct = new ArrayList<>();
        rvListMH.setLayoutManager(new LinearLayoutManager(this));
        controller = (ICartController) getApplication();
        listProduct = controller.getAllProduct();
        adapter = new Adapter(listProduct);
        rvListMH.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_close:
                finish();
            case R.id.menu_cart:
                hienThiGioHang();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hienThiGioHang() {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    //Không viết các phương thức của MainActivity ở phía dưới đây
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName, txtPrice, txtDesc;
        ImageView imvAddToCart;
        Product product;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtNameSP);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart = this.itemView.findViewById(R.id.imvAddToCart);
            imvAddToCart.setOnClickListener(this);
        }
        
        public void bind(Product p) {
            this.product = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
        }

        @Override
        public void onClick(View v) {
            if(controller.addToCart(product)) {
                Toast.makeText(MainActivity.this,
                        "Đã thêm: " + product.getName() + " vào giỏ hàng.",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        product.getName() + " đã có trong giỏ hàng.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Adapter extends RecyclerView.Adapter<ProductViewHolder> {
        List<Product> productList;

        public Adapter(List<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(productList.get(position));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }
}
