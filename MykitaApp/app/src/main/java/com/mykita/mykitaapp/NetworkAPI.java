package com.mykita.mykitaapp;

import com.google.gson.JsonObject;
import com.mykita.mykitaapp.model.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by shreyas13732 on 5/30/2017.
 */

public interface NetworkAPI {

    @POST("/addTransactions")
    Call<Result> addTransactions(@Body JsonObject inObj);
}
