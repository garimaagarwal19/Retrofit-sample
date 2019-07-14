package com.example.retrofit.Interface;

import com.example.retrofit.model.Guest;

import java.util.List;

public interface ActivityCallbackInterface {
    void onDataUpdate(List<Guest> guestList);
}
