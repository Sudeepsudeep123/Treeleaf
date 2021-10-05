package com.example.sudeepbajracharya.treeleaftask.shared.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sudeepbajracharya.treeleaftask.R;
import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    ArrayList<UserDetailModel> data;
    UserDetailModel udm;

    public UserListAdapter(ArrayList<UserDetailModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_userlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        udm = data.get(position);
        byte[] bitmapdata = udm.getImageUrl();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        holder.imvUserPic.setImageBitmap(bitmap);
        holder.txvDate.setText(String.valueOf(udm.getDate()));
        holder.txvName.setText(udm.getName());
        holder.txvMailId.setText(udm.getMail());
        holder.txvPhoneNumber.setText(udm.getPhone());
        if (udm.getDevice().equalsIgnoreCase("Mac")) {
            holder.imvDevice.setImageResource(R.drawable.ic_baseline_laptop_mac_24);
        } else if (udm.getDevice().equalsIgnoreCase("Android")) {
            holder.imvDevice.setImageResource(R.drawable.ic_baseline_android_24);
        } else {
            holder.imvDevice.setImageResource(R.drawable.ic_baseline_computer_24);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvUserPic;
        ImageView imvDevice;
        TextView txvName;
        TextView txvMailId;
        TextView txvDate;
        TextView txvPhoneNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            imvUserPic = itemView.findViewById(R.id.imvUserPic);
            imvDevice = itemView.findViewById(R.id.imvDevice);
            txvName = itemView.findViewById(R.id.txvName);
            txvMailId = itemView.findViewById(R.id.txvMailId);
            txvDate = itemView.findViewById(R.id.txvDate);
            txvPhoneNumber = itemView.findViewById(R.id.txvPhoneNumber);
        }
    }
}
