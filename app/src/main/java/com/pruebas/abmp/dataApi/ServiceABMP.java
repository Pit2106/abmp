package com.pruebas.abmp.dataApi;

import com.pruebas.abmp.models.Animal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceABMP {
    // llama Amfibios
    @GET("x2si-vbyw.json")
    Call<ArrayList<Animal>> getListAmphibians(@Query("$limit") int limit, @Query("$offset")int offset);
    // llama Aves
    @GET("dbhn-gczt.json")
    Call<ArrayList<Animal>> getListBirds(@Query("$limit") int limit, @Query("$offset")int offset);
    // llama Mamiferos
    @GET("9sb8-xicv.json")
    Call<ArrayList<Animal>> getListMammals(@Query("$limit") int limit, @Query("$offset")int offset);
}
