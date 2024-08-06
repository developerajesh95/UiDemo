package com.ui.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ui.demo.R;
import com.ui.demo.model.UserInfo;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    
    private List<UserInfo> userInfos;
    
    public MyRecyclerAdapter(Context context, List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_ui, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserInfo userInfo = userInfos.get(position);
        holder.tvName.setText(userInfo.getName());
        holder.tvAge.setText(userInfo.getAge());
    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
        }
    }
}
