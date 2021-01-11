package com.example.cseat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cseat.Adapter.RecyclerPelajar;
import com.example.cseat.Adapter.RecyclerRPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionPelajar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionPelajar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recv;
    RecyclerPelajar recyclerPelajar;

    StudentData studentData = StudentData.getInstance();
    //List<String> studentsname, studentclass, studentwork;

   // public List<String> studentsname, studentclass, studentproblem;

    public SectionPelajar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionPelajar.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionPelajar newInstance(String param1, String param2) {
        SectionPelajar fragment = new SectionPelajar();
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


     //   studentsname = new ArrayList<>();
     //   studentclass = new ArrayList<>();
     //   studentproblem = new ArrayList<>();




/*

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Students");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            studentsname.add(ds.getKey());
                            studentclass.add(ds.child("Class").getValue(String.class));
                            studentproblem.add(ds.child("Problem").getValue(String.class));
                            // dataSnapshot.child("Class");
                        }
                        //studentsname.add(dataSnapshot.getChildren().toString());
                      //  Log.d(studentclass.toString(), "stu");
                        // collect((Map<String,Object>) dataSnapshot.getChildren());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
*/

        //recyclerPelajar = new RecyclerPelajar(studentsname,studentclass,studentproblem);
        recyclerPelajar= new RecyclerPelajar(studentData.getStudentsname(),studentData.getStudentclass(),studentData.getStudentpower());





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_pelajar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // studentslist = new

        recv = view.findViewById(R.id.recv);

        recv.setAdapter(recyclerPelajar);
       // recv.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recv.addItemDecoration(dividerItemDecoration);


    }


}