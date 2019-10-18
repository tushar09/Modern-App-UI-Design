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
import com.aaroza.classroom.newui.databinding.RowChatSelfBinding;
import com.aaroza.classroom.newui.databinding.RowChatSelfFrndBinding;
import com.aaroza.classroom.newui.databinding.RowUsersBinding;
import com.aaroza.classroom.newui.databinding.RowUsersOfflineBinding;
import com.aaroza.classroom.newui.dto.msg.MsgHistoryResponseDto;
import com.aaroza.classroom.newui.utils.Constants;

import java.util.List;

public class MsgHistoryAdapter extends RecyclerView.Adapter{

    private List<MsgHistoryResponseDto> dtos;
    private Context context;

    public MsgHistoryAdapter(List<MsgHistoryResponseDto> dtos, Context context){
        this.dtos = dtos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        if(viewType == 1){
            //RowUsersOfflineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_users_offline, parent, false);
            return new ViewHolderSelf((RowChatSelfBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_chat_self, parent, false));
        }else {
            return new ViewHolderFrnd((RowChatSelfFrndBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_chat_self_frnd, parent, false));
            //RowUsersOfflineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_users_offline, parent, false)
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        if(getItemViewType(position) == 1){
            ViewHolderSelf viewHolderSelf = (ViewHolderSelf) holder;
            viewHolderSelf.binding.tvText.setText(dtos.get(position).getText());
        }else {
            ViewHolderFrnd viewHolderFrnd = (ViewHolderFrnd) holder;
            viewHolderFrnd.binding.tvText.setText(dtos.get(position).getText());
        }
    }

    @Override
    public int getItemViewType(int position){
        return (dtos.get(position).getEmail().equals(Constants.getSPreferences().getEmail()) ? 1 : 0);
    }

    @Override
    public int getItemCount(){
        return dtos.size();
    }


    private class ViewHolderFrnd extends RecyclerView.ViewHolder{

        RowChatSelfFrndBinding binding;

        public ViewHolderFrnd(@NonNull RowChatSelfFrndBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class ViewHolderSelf extends RecyclerView.ViewHolder{

        RowChatSelfBinding binding;

        public ViewHolderSelf(@NonNull RowChatSelfBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
