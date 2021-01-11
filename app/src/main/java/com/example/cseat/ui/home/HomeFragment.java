package com.example.cseat.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cseat.Adapter.videoRecyclerView;
import com.example.cseat.R;
import com.example.cseat.StudentData;
import com.example.cseat.Vid;
import com.example.cseat.VideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Vid vid= Vid.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            //@Override
           // public void onChanged(@Nullable String s) {
                //textView.setText(s);
          //  }
       // });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //List<VideoPlayer> Videoplayerlist=getallVideoInfor();


        RecyclerView recyclerView=getView().findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        videoRecyclerView videoRecyclerView=new videoRecyclerView(getContext(),vid.getAllvideo());
        recyclerView.setAdapter(videoRecyclerView);
    }


    /*
    private List<VideoPlayer> getallVideoInfor() {
        List<VideoPlayer>allvideo=new ArrayList<VideoPlayer>();

        allvideo.add(new VideoPlayer("KAEDAH PENGGUNAAN “TOKEN BOARD”","https://www.youtube.com/watch?v=G-CBEVmJ3Ko"));
       allvideo.add(new VideoPlayer("KAEDAH BERCERITA “SOCIAL STORIES”","https://www.youtube.com/watch?v=fBTjo6Q_jCc"));
        allvideo.add(new VideoPlayer("KAEDAH MENUNGGU GILIRAN “WAIT”","https://www.youtube.com/watch?v=ChHO5TaoVHU"));
       allvideo.add(new VideoPlayer("RUTIN KE TANDAS “TOILET ROUTINE”","https://www.youtube.com/watch?v=NaMXypwkMjE"));
        allvideo.add(new VideoPlayer("SOKONGAN VISUAL “VISUAL AID”","https://www.youtube.com/watch?v=xB29qZRNOPQ"));
       allvideo.add(new VideoPlayer("KAEDAH PEMBELAJARAN “TEACHING METHODS”","https://www.youtube.com/watch?v=FgipMaVckbA"));

        return allvideo;
    }*/

    private String getPackageName() {
        return getPackageName();
    }


}