package com.example.cseat.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cseat.AboutUs;
import com.example.cseat.R;
import com.example.cseat.photocseat;

import java.util.List;

public class cseatRecyclerViewAdapter extends RecyclerView.Adapter<cseatRecyclerViewAdapter.cseatViewHolder> {
    public List<photocseat> cImages;
    public Context context;


    public cseatRecyclerViewAdapter( Context context,List<photocseat> cImages) {
        this.context=context;
        this.cImages=cImages;
       // Toast.makeText(context,"ape tew",Toast.LENGTH_SHORT).show();

    }


    @NonNull
    @Override
    public cseatRecyclerViewAdapter.cseatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View photo_row=LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_row,null);
        cseatViewHolder ch=new cseatViewHolder(photo_row);
        //Toast.makeText(context,"ape tewwwwwwwwwwwwwwwwww",Toast.LENGTH_SHORT).show();
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull cseatRecyclerViewAdapter.cseatViewHolder holder, int position) {
    holder.imageview.setImageResource(cImages.get(position).getImage());
        //Toast.makeText(context,"ape tewwwwwwwwwwwwwwwwww",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
       // Toast.makeText(context,"ape tewwwwwwwwwwwwwwwwww",Toast.LENGTH_SHORT).show();
        return cImages.size();
    }

    public class cseatViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public cseatViewHolder(@NonNull View itemView) {

            super(itemView);
            imageview=itemView.findViewById(R.id.image_cseat);


        }
    }
}

