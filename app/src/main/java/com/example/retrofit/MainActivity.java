package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofit.Interface.ActivityCallbackInterface;
import com.example.retrofit.Interface.IRepositoryInterface;
import com.example.retrofit.model.Guest;
import com.example.retrofit.repository.DataRepository;
import com.example.retrofit.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCallbackInterface {

    private RecyclerView recyclerView;
    private IRepositoryInterface repositoryInterface;
    private GuestAdapter guestAdapter;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        guestAdapter = new GuestAdapter(this);

        mainBinding.recyclerView.setAdapter(guestAdapter);

        repositoryInterface = DataRepository.getInstance();

        repositoryInterface.registerObserver(this);

        repositoryInterface.makeAPICall();
    }

    @Override
    public void onDataUpdate(List<Guest> guestList) {
        guestAdapter.setData(guestList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repositoryInterface.unregisterObserver(this);
    }
}
