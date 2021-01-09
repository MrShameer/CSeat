package com.example.cseat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.cseat.Adapter.cseatRecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
        (new Handler()).postDelayed(this::run, 3000);
        //startActivity(new Intent(MainActivity.this, ItemListDialogFragment.class));
        //startActivity(new Intent(MainActivity.this, Pelajar.class));

    }


    void run(){
//        Toast.makeText(MainActivity.this,user.getUid(),Toast.LENGTH_SHORT).show();
        Intent i;
        if(user !=null) {
            i = new Intent(MainActivity.this, QuickAccess.class);
        } else {
           // FirebaseAuth.getInstance().signOut();
            i = new Intent(MainActivity.this, LoginActivity.class);
        }

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}