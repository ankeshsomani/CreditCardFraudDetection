package com.mykita.mykitaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mykita.mykitaapp.DataManager;
import com.mykita.mykitaapp.PaymentActivity;
import com.mykita.mykitaapp.ProductAdapter;
import com.mykita.mykitaapp.ProductDetailActivity;
import com.mykita.mykitaapp.R;

public class MainActivity extends AppCompatActivity {

    DataManager dataManager;
    ProductAdapter productAdapter;
    ListView listView;
    public static int cartCount = 0;
    public static int totalAmount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.recyclerView);
        dataManager = new DataManager();
        productAdapter = new ProductAdapter(MainActivity.this,R.layout.list_item_product, dataManager.productList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                intent.putExtra("product",dataManager.productList.get(position));
                startActivity(intent);
            }
        });
        listView.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.action_layout);
        View notifCount = item.getActionView();
        notifCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra("total", totalAmount);
                startActivity(intent);
            }
        });
        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText(cartCount+"");

        return true;


    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra("total", totalAmount);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cartCount=0;
        totalAmount = 0;
    }
}
