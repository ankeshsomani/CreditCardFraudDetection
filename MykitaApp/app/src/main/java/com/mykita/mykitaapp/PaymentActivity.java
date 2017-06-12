package com.mykita.mykitaapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mykita.mykitaapp.model.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    EditText etExpiryDate, etCardNumber, etCVV, etName;
    TextView tvAmount;
    Button btSubmit;
    ProgressBar progressBar;
    int totalAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        totalAmount = getIntent().getExtras().getInt("total");
        initialise();
    }

    private void initialise(){
        tvAmount = (TextView) findViewById(R.id.tvAmount);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        etExpiryDate = (EditText) findViewById(R.id.etExpiryDate);
        etCardNumber = (EditText) findViewById(R.id.etCardNumber);
        etCVV = (EditText) findViewById(R.id.etCVV);
        etName = (EditText) findViewById(R.id.etName);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    addTransactions(totalAmount);
                }
            }
        });

        tvAmount.setText("Total Amount: GBP "+totalAmount);
    }

    private void addTransactions(int totalAmount){
        if (isOnline()) {
            try {
                progressBar.setVisibility(View.VISIBLE);
                JSONObject outerObj = new JSONObject();
                JSONArray transactionArray = new JSONArray();
                JSONObject transactionObj = new JSONObject();
                transactionObj.put("accountId",100);
                transactionObj.put("transactionId", getEpoch());
                transactionObj.put("posid", 1234);
                transactionObj.put("transactionDate", formatCurrentDateTime());
                transactionObj.put("description", "Product Purchase");
                transactionObj.put("amount",totalAmount);

                transactionArray.put(transactionObj);
                outerObj.put("transactions", transactionArray);

                JsonParser jsonParser = new JsonParser();
                JsonObject gsonObject = (JsonObject)jsonParser.parse(outerObj.toString());

                NetworkAPI networkAPI = APIClient.getClient().create(NetworkAPI.class);
                Call<Result> call = networkAPI.addTransactions(gsonObject);
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Log.d("response", response.toString());
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(PaymentActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.d("error", call.toString());
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(PaymentActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private boolean isValid(){

        if (TextUtils.isEmpty(etExpiryDate.getText().toString())){
            etExpiryDate.setError("Please enter expiry date");
            return false;
        }
        if (TextUtils.isEmpty(etCardNumber.getText().toString())){
            etCardNumber.setError("Please enter card number");
            return false;
        }
        if (TextUtils.isEmpty(etCVV.getText().toString())){
            etCVV.setError("Please enter CVV number on card");
            return false;
        }
        if (TextUtils.isEmpty(etName.getText().toString())){
            etName.setError("Please enter name on card");
            return false;
        }
        return true;
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private static String formatCurrentDateTime(){
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return df.format(date);
        }catch (Exception e){
            return "";
        }
    }

    private static String getEpoch(){
        try {
            Date date = new Date();
            return date.getTime()+"";
        }catch (Exception e){
            return "";
        }
    }
}
