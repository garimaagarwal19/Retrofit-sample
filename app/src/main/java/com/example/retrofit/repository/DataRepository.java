package com.example.retrofit.repository;

import android.os.Handler;
import android.util.Log;

import com.example.retrofit.Interface.ActivityCallbackInterface;
import com.example.retrofit.Interface.IRepositoryInterface;
import com.example.retrofit.model.Guest;
import com.example.retrofit.retrofit.GuestService;
import com.example.retrofit.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository implements IRepositoryInterface {

    private static DataRepository mInstance;

    private List<ActivityCallbackInterface> callbackInterfaceList;
    private GuestService guestService;
    private Handler handler;

    private DataRepository() {
        callbackInterfaceList = new ArrayList<>();
    }

    public static DataRepository getInstance() {
        if(mInstance == null) {
            mInstance = new DataRepository();
        }
        return mInstance;
    }

    @Override
    public void makeAPICall() {
        guestService = RetrofitClient.getRetrofitInsatnce().create(GuestService.class);
        final Call<List<Guest>> call = guestService.getGuest();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback<List<Guest>>() {
                    @Override
                    public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                        notifyObservers(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Guest>> call, Throwable t) {
                        Log.e("Error","Error while fetching data");
                    }
                });

            }
        },10000);
    }

    @Override
    public void registerObserver(ActivityCallbackInterface callbackInterface) {
        if(!callbackInterfaceList.contains(callbackInterface)) {
            callbackInterfaceList.add(callbackInterface);
        }
    }

    @Override
    public void unregisterObserver(ActivityCallbackInterface callbackInterface) {
        if(callbackInterfaceList.contains(callbackInterface)) {
            callbackInterfaceList.remove(callbackInterface);
        }
    }

    @Override
    public void notifyObservers(List<Guest> guestList) {
        for(ActivityCallbackInterface callbackInterface : callbackInterfaceList) {
            callbackInterface.onDataUpdate(guestList);
        }
    }
}
