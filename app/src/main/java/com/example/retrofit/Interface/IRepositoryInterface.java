package com.example.retrofit.Interface;

import com.example.retrofit.model.Guest;

import java.util.List;

public interface IRepositoryInterface {
    void registerObserver(ActivityCallbackInterface callbackInterface);
    void unregisterObserver(ActivityCallbackInterface callbackInterface);
    void notifyObservers(List<Guest> guestList);
    void makeAPICall();
}
