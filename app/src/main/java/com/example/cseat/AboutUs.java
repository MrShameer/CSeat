package com.example.cseat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cseat.Adapter.cseatRecyclerViewAdapter;
import com.example.cseat.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutUs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUs extends Fragment {
LinearLayoutManager linearLayoutManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutUs() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutUs.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUs newInstance(String param1, String param2) {
        AboutUs fragment = new AboutUs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<photocseat> getAllPhotoInfo() {
        List<photocseat>allPhoto=new ArrayList<photocseat>();
        allPhoto.add(new photocseat(R.drawable.photo1));
        allPhoto.add(new photocseat(R.drawable.photo2));
        allPhoto.add(new photocseat(R.drawable.photo3));
        allPhoto.add(new photocseat(R.drawable.photo4));
        allPhoto.add(new photocseat(R.drawable.photo5));
        allPhoto.add(new photocseat(R.drawable.photo6));
        allPhoto.add(new photocseat(R.drawable.photo7));
        allPhoto.add(new photocseat(R.drawable.photo8));
        allPhoto.add(new photocseat(R.drawable.photo9));
        //Toast.makeText(getActivity(),allPhoto.size()+"",Toast.LENGTH_SHORT).show();

        return allPhoto;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        //List<photocseat> allphotoinfo=getAllPhotoInfo();

        //cseatRecyclerViewAdapter cseatadapter=new cseatRecyclerViewAdapter(getActivity(),allphotoinfo);
        //Toast.makeText(getActivity(),allphotoinfo.size(),Toast.LENGTH_SHORT).show();






        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_about_us, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<photocseat>nea=getAllPhotoInfo();
        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        cseatRecyclerViewAdapter cs=new cseatRecyclerViewAdapter(getContext(),nea);
        recyclerView.setAdapter(cs);

       // Toast.makeText(getActivity(),"meow",Toast.LENGTH_SHORT).show();

    }



}