package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.Guest;
import com.example.retrofit.databinding.GuestItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ItemViewHolder> {
    private List<Guest> guestList;
    private Context context;

    public GuestAdapter(Context context) {
        guestList = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<Guest> list) {
        this.guestList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GuestItemLayoutBinding guestItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.guest_item_layout, parent, false);
        return new ItemViewHolder(guestItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.guestItemLayoutBinding.setGuest(guestList.get(position));
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private GuestItemLayoutBinding guestItemLayoutBinding;

        public ItemViewHolder(GuestItemLayoutBinding guestItemLayoutBinding) {
            super(guestItemLayoutBinding.getRoot());
            this.guestItemLayoutBinding = guestItemLayoutBinding;
        }
    }
}
