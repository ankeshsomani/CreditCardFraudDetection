package com.mykita.mykitaapp;

import com.mykita.mykitaapp.model.Product;

import java.util.ArrayList;

/**
 * Created by shreyas13732 on 5/29/2017.
 */

public class DataManager {

    public ArrayList<Product> productList;
    Product product;
    public DataManager() {
        productList = new ArrayList<>();

        product= new Product();
        product.setTitle("Knight Ace True Casuals (Tan)");
        product.setColor("Brown");
        product.setOuterMaterial("Synthetic Leather");
        product.setIdealFor("Men");
        product.setOccassion("Sports");
        product.setCost("GBP 4999");
        product.setCashOnDelivery(true);
        product.setCreditCard(true);
        product.setNetBanking(true);
        product.setImageRes(R.drawable.p1);
        product.setAmount(4999);
        productList.add(product);

        product= new Product();
        product.setTitle("Puma Men Running Shoes");
        product.setColor("White");
        product.setOuterMaterial("Synthetic Leather");
        product.setIdealFor("Men");
        product.setOccassion("Sports");
        product.setCost("GBP 10999.00");
        product.setCashOnDelivery(false);
        product.setCreditCard(true);
        product.setNetBanking(true);
        product.setImageRes(R.drawable.p2);
        product.setAmount(10999);
        productList.add(product);

        product= new Product();
        product.setTitle("Aero Power Play Running Shoes (Navy)");
        product.setColor("Blue");
        product.setOuterMaterial("Synthetic Leather");
        product.setIdealFor("Men");
        product.setOccassion("Sports");
        product.setCost("GBP 74999.00");
        product.setCashOnDelivery(false);
        product.setCreditCard(true);
        product.setNetBanking(true);
        product.setImageRes(R.drawable.p3);
        product.setAmount(74999);
        productList.add(product);
    }
}
