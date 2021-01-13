package com.example.cseat;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

public class QuickAccess extends AppCompatActivity {
Toolbar toolbar;

public StudentData studentData = StudentData.getInstance();
public Vid vid = Vid.getInstance();
public UserData userData = UserData.getInstance();
public Material material = Material.getInstance();

    List<VideoPlayer> allvideo=new ArrayList<VideoPlayer>();
    List<String> studentsname, studentclass, studentwork, studentpower;
    List<String> url=new ArrayList<String>(),name = new ArrayList<String>();
    TextView textView;
    ProgressDialog mLoadingBar;
    String fn = new String();
   // FirebaseStorage storage = FirebaseStorage.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_access);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.tabPelajar2, R.id.navigation_notifications, R.id.abu)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.tool);

        //ActivityNavigator.applyPopAnimationsToPendingTransition(this);

        mLoadingBar=new ProgressDialog(QuickAccess.this);

        mLoadingBar.setTitle("Setting Up");
        mLoadingBar.setMessage("Please Wait while we fetch your data from the server");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();
        //setActionBar(toolbar);
        //this.setSupportActionBar(toolbar);
        //setActionBar(toolbar);
        //setSupportActionBar(toolbar);
       // setSupportActionBar(toolbar);
//       NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> textView.setText(navController.getCurrentDestination().getLabel()));
        //toolbar.setForegroundGravity(C);
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        StorageReference reference = storageRef.child("Materials/");


       // reference.get

       reference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
           @Override
           public void onSuccess(ListResult listResult) {
               for(StorageReference fileRef : listResult.getItems()) {
                   // TODO: Download the file using its reference (fileRef)
                  // Log.d("filee", fileRef.getName());
                   fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                           url.add(uri.toString());
                           //Log.d("urll",url.toString());

                       }
                   });

                   //url.add(fileRef.getDownloadUrl().toString());
                   fn=fileRef.getName();
                   name.add(fn.substring(0, fn.lastIndexOf('.')));

               }

               material.setName(name);
               material.setUrl(url);
               //Toast.makeText(QuickAccess.this,material.getName().toString(),Toast.LENGTH_SHORT).show();
               mLoadingBar.dismiss();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {

           }
       });

        //userpic.getDownloadUrl();
        StorageReference userpic = storageRef.child("UserPicture/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        userpic.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    userData.setUrl(task.getResult().toString());
                }


            }
        });



        studentsname= new ArrayList<>();
        studentclass = new ArrayList<>();
        studentwork = new ArrayList<>();
        studentpower = new ArrayList<>();

        allvideo.add(new VideoPlayer("KAEDAH PENGGUNAAN “TOKEN BOARD”","https://www.youtube.com/watch?v=G-CBEVmJ3Ko"));
        allvideo.add(new VideoPlayer("KAEDAH BERCERITA “SOCIAL STORIES”","https://www.youtube.com/watch?v=fBTjo6Q_jCc"));
        allvideo.add(new VideoPlayer("KAEDAH MENUNGGU GILIRAN “WAIT”","https://www.youtube.com/watch?v=ChHO5TaoVHU"));
        allvideo.add(new VideoPlayer("RUTIN KE TANDAS “TOILET ROUTINE”","https://www.youtube.com/watch?v=NaMXypwkMjE"));
        allvideo.add(new VideoPlayer("SOKONGAN VISUAL “VISUAL AID”","https://www.youtube.com/watch?v=xB29qZRNOPQ"));
        allvideo.add(new VideoPlayer("KAEDAH PEMBELAJARAN “TEACHING METHODS”","https://www.youtube.com/watch?v=FgipMaVckbA"));

        vid.setAllvideo(allvideo);

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
                            studentwork.add(ds.child("Work").getValue(String.class).replaceAll(",","\n"));
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
                        Toast.makeText(QuickAccess.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

       DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userData.setUname(snapshot.child("Username").getValue(String.class));
                userData.setEmail(snapshot.child("Email").getValue(String.class));
                userData.setPhone(snapshot.child("Phone").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuickAccess.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });





    }



}