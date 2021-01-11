package com.example.cseat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

public class QuickAccess extends AppCompatActivity {
Toolbar toolbar;
public StudentData studentData = StudentData.getInstance();
    List<String> studentsname, studentclass, studentwork, studentpower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_access);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.tabPelajar2, R.id.navigation_notifications,R.id.tb, R.id.abu)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        toolbar = findViewById(R.id.toolbar);
        //setActionBar(toolbar);
        //this.setSupportActionBar(toolbar);
        //setActionBar(toolbar);
        //setSupportActionBar(toolbar);
       // setSupportActionBar(toolbar);
//       NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> toolbar.setTitle(navController.getCurrentDestination().getLabel()));

        studentsname= new ArrayList<>();
        studentclass = new ArrayList<>();
        studentwork = new ArrayList<>();
        studentpower = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Students");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                       // studentData.setStudentsname(dataSnapshot.c);
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            studentsname.add(ds.getKey());
                            studentclass.add(ds.child("Class").getValue(String.class));
                            studentpower.add(ds.child("Problem").getValue(String.class));
                            studentwork.add(ds.child("Work").getValue(String.class).replaceAll(",","/n"));
                            // dataSnapshot.child("Class");
                        }

                        studentData.setStudentsname(studentsname);
                        studentData.setStudentclass(studentclass);
                        studentData.setStudentpower(studentpower);
                        studentData.setStudentwork(studentwork);
                        //studentsname.add(dataSnapshot.getChildren().toString());
                        //  Log.d(studentclass.toString(), "stu");
                        // collect((Map<String,Object>) dataSnapshot.getChildren());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });



    }



}