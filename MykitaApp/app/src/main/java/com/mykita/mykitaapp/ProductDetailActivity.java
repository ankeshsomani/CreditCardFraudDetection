package com.mykita.mykitaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykita.mykitaapp.activities.MainActivity;
import com.mykita.mykitaapp.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    public TextView tvTitle, tvColor, tvOuterMaterial, tvIdealFor, tvOccasion, tvCost, tvCod, tvCredit, tvNetbanking;
    ImageView ivProduct;
    Button btPayNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initialise();
        Product product = getIntent().getParcelableExtra("product");
        setData(product);
    }

    private void initialise(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvColor = (TextView) findViewById(R.id.tvColor);
        tvOuterMaterial = (TextView) findViewById(R.id.tvOuterMaterial);
        tvIdealFor = (TextView) findViewById(R.id.tvIdealFor);
        tvOccasion = (TextView) findViewById(R.id.tvOccasion);
        tvCost = (TextView) findViewById(R.id.tvCost);
        ivProduct = (ImageView) findViewById(R.id.ivProduct);
        tvCod = (TextView) findViewById(R.id.tvCod);
        tvCredit = (TextView) findViewById(R.id.tvCredit);
        tvNetbanking = (TextView) findViewById(R.id.tvNetbanking);
        btPayNow = (Button) findViewById(R.id.btPayNow);
    }

    private void setData(final Product product){
        ivProduct.setImageResource(product.getImageRes());
        tvTitle.setText(product.getTitle());
        tvColor.setText("Colour: "+product.getColor());
        tvOuterMaterial.setText("Outer Material: "+product.getOuterMaterial());
        tvIdealFor.setText("Ideal for: "+product.getIdealFor());
        tvOccasion.setText("Occasion: "+product.getOccassion());
        tvCost.setText("Cost: "+product.getCost());

        tvCod.setText("Cash on delivery: "+(product.isCashOnDelivery()?"Yes":"No"));
        tvCredit.setText("Credit card: "+(product.isCreditCard()?"Yes":"No"));
        tvNetbanking.setText("Net banking: "+(product.isNetBanking()?"Yes":"No"));

        btPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.cartCount++;
                MainActivity.totalAmount+=product.getAmount();
                finish();
            }
        });
    }


}
