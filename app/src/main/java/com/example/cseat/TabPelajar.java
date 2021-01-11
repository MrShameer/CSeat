package com.example.cseat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cseat.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cseat.ui.main.SectionsPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabPelajar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabPelajar extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabPelajar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabPelajar.
     */
    // TODO: Rename and change types and number of parameters
    public static TabPelajar newInstance(String param1, String param2) {
        TabPelajar fragment = new TabPelajar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
      //  setContentView(R.layout.activity_pelajar);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_tab_pelajar2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getChildFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

     viewPager.setOffscreenPageLimit(3);

       // viewPager.setCurrentItem(0);
        TabLayout tabs =view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //viewPager.refreshDrawableState();
        //viewPager.setCurrentItem(2);
       // viewPager.setCurrentItem(0);
        //Toast.makeText(getActivity(),"dfgdfgdf",Toast.LENGTH_SHORT).show();

    }
}