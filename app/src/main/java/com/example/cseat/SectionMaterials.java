package com.example.cseat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cseat.Adapter.RecyclerMaterial;
import com.example.cseat.Adapter.RecyclerRPI;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionMaterials#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionMaterials extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recv;
    RecyclerMaterial recyclerMaterial;

    Material material = Material.getInstance();
    public SectionMaterials() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionMaterials.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionMaterials newInstance(String param1, String param2) {
        SectionMaterials fragment = new SectionMaterials();
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
       // Toast.makeText(getContext(), material.getName().toString(), Toast.LENGTH_LONG);
        Log.d("mattt",material.getName().toString());
        recyclerMaterial = new RecyclerMaterial(material.getUrl(),material.getName());
      //  Toast.makeText(getActivity().this,material.getUrl().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_materials, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recv = view.findViewById(R.id.recv);
        recv.setAdapter(recyclerMaterial);
       // Log.d(material.getName().toString(),"namee");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recv.addItemDecoration(dividerItemDecoration);

    }
}