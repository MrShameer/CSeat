package com.example.cseat.Adapter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cseat.QuickAccess;
import com.example.cseat.R;
import com.example.cseat.SectionRPI;
import com.example.cseat.TabPelajar;

import java.util.List;

public class RecyclerMaterial extends RecyclerView.Adapter<RecyclerMaterial.ViewHolder>{
    List<String> url,name;
private Context context;
    public RecyclerMaterial(List<String> url, List<String> name, Context context) {
        this.url = url;
        this.name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.material_row, parent, false);
        RecyclerMaterial.ViewHolder viewHolder = new RecyclerMaterial.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rctv.setText(name.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView rctv;
        ConstraintLayout cl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rctv =(TextView) itemView.findViewById(R.id.rctv);
            cl = itemView.findViewById(R.id.cl);

            cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                    browserIntent.putExtra(SearchManager.QUERY, Uri.parse(url.get(getAdapterPosition())).toString());
                    context.startActivity(browserIntent);
                }
            });
        }
    }
}
