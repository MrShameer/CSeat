package com.example.cseat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cseat.Adapter.cseatRecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WifiManager wifiManager;
    Boolean On;
    ConnectivityManager connectivityManager;
    FirebaseUser user;
    LinearLayoutManager linearLayoutManager;
    private int Wifirequest;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWifi();
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

        On=false;
        wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        boolean connected = false;
        connectivityManager = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            On=true;
            startActivity(i);

        }
        else
            connected = false;
        On=false;

        if (connected){Toast.makeText(MainActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();}
        else{
            showExplanation(i);

            Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_SHORT).show();}



        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void showExplanation(Intent I) {

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Wifi/Data Permission");
        builder.setMessage("Most of the Functionalities in this Application Requires Internet Access");
        builder.setPositiveButton("ON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wifiManager.setWifiEnabled(true);
                startActivity(I);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Requires Internet Access", Toast.LENGTH_SHORT).show();
                showExplanation(I);
            }
        });

        builder.create();
        builder.show();





    }

}