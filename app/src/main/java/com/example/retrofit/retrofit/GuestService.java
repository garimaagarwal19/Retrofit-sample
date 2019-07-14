package com.example.retrofit.retrofit;

import com.example.retrofit.model.Guest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GuestService {
    @Headers("Content-Type: application/json")
    @GET("/guestData")
    Call<List<Guest>> getGuest();
}
