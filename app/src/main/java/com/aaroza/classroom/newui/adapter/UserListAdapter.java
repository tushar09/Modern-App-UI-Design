package com.aaroza.classroom.newui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aaroza.classroom.newui.R;
import com.aaroza.classroom.newui.activity.ChatActivity;
import com.aaroza.classroom.newui.databinding.RowUsersBinding;
import com.aaroza.classroom.newui.databinding.RowUsersOfflineBinding;
import com.aaroza.classroom.newui.dto.users.GetUsersResponseDto;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter{

    private List<GetUsersResponseDto> dtos;
    private Context context;

    public UserListAdapter(List<GetUsersResponseDto> dtos, Context context){
        this.dtos = dtos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        if(viewType == 1){
            //RowUsersOfflineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_users_offline, parent, false);
            return new ViewHolderOffline((RowUsersOfflineBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_users_offline, parent, false));
        }else {
            return new ViewHolder((RowUsersBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_users, parent, false));
            //RowUsersOfflineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_users_offline, parent, false)
        }
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position){
        if(getItemViewType(position) == 1){
            ViewHolderOffline viewHolderOffline = (ViewHolderOffline) holder;
            viewHolderOffline.binding.tvName.setText(dtos.get(position).getName());
            viewHolderOffline.binding.tvPoints.setText(dtos.get(position).getEmail());
            viewHolderOffline.binding.getRoot().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    context.startActivity(new Intent(context, ChatActivity.class)
                            .putExtra("email", dtos.get(position).getEmail())
                    );
                }
            });
        }else {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.binding.tvName.setText(dtos.get(position).getName());
            viewHolder.binding.tvPoints.setText(dtos.get(position).getEmail());
            viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    context.startActivity(new Intent(context, ChatActivity.class)
                            .putExtra("email", dtos.get(position).getEmail())
                    );
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position){
        return (dtos.get(position).getSocket_id() == null ? 1 : 0);
    }

    @Override
    public int getItemCount(){
        return dtos.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        RowUsersBinding binding;

        public ViewHolder(@NonNull RowUsersBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class ViewHolderOffline extends RecyclerView.ViewHolder{

        RowUsersOfflineBinding binding;

        public ViewHolderOffline(@NonNull RowUsersOfflineBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
