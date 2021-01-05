package com.example.cseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
        (new Handler()).postDelayed(this::run, 3000);
        //startActivity(new Intent(MainActivity.this, ItemListDialogFragment.class));
        //startActivity(new Intent(MainActivity.this, Pelajar.class));
    }

    void run(){
        Intent i;
        if(user !=null) {
            i = new Intent(MainActivity.this, QuickAccess.class);
        } else {
            i = new Intent(MainActivity.this, LoginActivity.class);
        }

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}