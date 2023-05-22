package com.example.tipcalculator;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    Activity activity;
    ArrayList<DataModel> userList;

    public MyRecyclerViewAdapter(Activity activity, ArrayList<DataModel> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View listItem = layoutInflater.inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userIdTv.setText(userList.get(position).getId().toString());
        holder.userNameTv.setText(userList.get(position).getName());
        holder.userAddressTv.setText(userList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setTasks(ArrayList<DataModel> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userIdTv, userNameTv, userAddressTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdTv = itemView.findViewById(R.id.userIdTv);
            userNameTv = itemView.findViewById(R.id.userNameTv);
            userAddressTv = itemView.findViewById(R.id.userAddressTv);
        }
    }
}
