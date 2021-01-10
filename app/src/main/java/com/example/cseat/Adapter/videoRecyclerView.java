package com.example.cseat.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cseat.R;
import com.example.cseat.VideoPlayer;

import java.util.List;

public class videoRecyclerView extends RecyclerView.Adapter<videoRecyclerView.videoholder> {
    public List<VideoPlayer> videoPlayerslist;
    public Context context;

    public videoRecyclerView(Context context, List<VideoPlayer> videoPlayerslist){
        this.context=context;
        this.videoPlayerslist=videoPlayerslist;
    }

    @NonNull
    @Override
    public videoholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View video_row=LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row,null);
        videoholder videoholder=new videoholder(video_row);
        return videoholder;
    }

    @Override
    public void onBindViewHolder(@NonNull videoholder holder, int position) {
        holder.videotitle.setText(videoPlayerslist.get(position).getTitle());
        Uri url= Uri.parse(videoPlayerslist.get(position).getUrl());
        holder.videoplayer.loadUrl(String.valueOf(url));


    }

    @Override
    public int getItemCount() {

        return videoPlayerslist.size();
    }


    public class videoholder extends RecyclerView.ViewHolder {
        public TextView videotitle;
        public WebView videoplayer;
        public videoholder(@NonNull View itemView) {

            super(itemView);
            videotitle=itemView.findViewById(R.id.txt_title);
            videoplayer=itemView.findViewById(R.id.web_view);
            videoplayer.setWebViewClient(new WebViewClient());
            videoplayer.setWebChromeClient(new WebChromeClient());
            videoplayer.getSettings().setJavaScriptEnabled(true);


        }
    }
}
