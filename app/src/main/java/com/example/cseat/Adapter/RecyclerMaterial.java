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
      // Log.d("fromrm", context.toString());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.material_row, parent, false);
        RecyclerMaterial.ViewHolder viewHolder = new RecyclerMaterial.ViewHolder(view);
       // Log.d("xreateview",name.toString());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rctv.setText(name.get(position));
       // Toast.makeText(QuickAccess.this,name.toString(),Toast.LENGTH_SHORT).show();
      //  Log.d("fileee",name.get(position));
        //holder.webView.loadUrl(url.get(position));
    }

    @Override
    public int getItemCount() {
       // Log.d("sizee", name.size()+"");
        return name.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView rctv;
        WebView webView;

        ConstraintLayout cl;
       // private final Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //context = itemView.getContext();
            rctv =(TextView) itemView.findViewById(R.id.rctv);
            webView = itemView.findViewById(R.id.webView);
            cl = itemView.findViewById(R.id.cl);

            cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent browserIntent = new Intent(Intent.ACTION_WEB_SEARCH, Uri.parse(url.get(getAdapterPosition())));
                    Intent browserIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                    //v.cont.startActivity(browserIntent);
                    browserIntent.putExtra(SearchManager.QUERY, Uri.parse(url.get(getAdapterPosition())).toString());
                    //Log.d("fromrm", context.toString());
                    context.startActivity(browserIntent);
                }
            });

        }
    }
}
