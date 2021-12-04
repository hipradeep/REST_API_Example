package com.hipradeep.rest_api_example.using_retrofit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hipradeep.rest_api_example.R;
import com.hipradeep.rest_api_example.using_retrofit.services.models.responses.ResponsePosts;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {
    List<ResponsePosts> list;
    Context context;

    public PostsAdapter(List<ResponsePosts> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_post_items,parent,false);
        return  new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //set background color for item
        if(position%2==0){
           holder.cl_post_item.setBackgroundColor(context.getResources().getColor(R.color.bg_color_1));
        }else {
            holder.cl_post_item.setBackgroundColor(context.getResources().getColor(R.color.bg_color_2));
        }
        holder.tv_data.setText(
               "ID => "+ list.get(position).getId() +"\n"
                +"User ID =>"+ list.get(position).getUserId() +"\n"
                +"Title => " + list.get(position).getTitle() +"\n"
                +"Body => " + list.get(position).getBody() +"\n"
                );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;
        ConstraintLayout cl_post_item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_data=itemView.findViewById(R.id.tv_data);
            cl_post_item=itemView.findViewById(R.id.cl_post_item);
        }
    }
}
