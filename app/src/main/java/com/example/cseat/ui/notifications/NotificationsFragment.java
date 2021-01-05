package com.example.cseat.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cseat.LoginActivity;
import com.example.cseat.QuickAccess;
import com.example.cseat.R;
import com.example.cseat.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Boolean editing=false;
    Button logout,edit,save;
    EditText uname,emil,phone;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference myRef = database.getReference("Users/"+currentFirebaseUser.getUid());
    String u,e,p;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        logout = root.findViewById(R.id.logout);
        edit = root.findViewById(R.id.edit);
        save = root.findViewById(R.id.save);
        uname=root.findViewById(R.id.uname);
        emil=root.findViewById(R.id.editemail);
        phone=root.findViewById(R.id.phoneno);
        mAuth=FirebaseAuth.getInstance();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                u=snapshot.child("Username").getValue(String.class);
                uname.setText(u);
                e=snapshot.child("Email").getValue(String.class);
                emil.setText(e);
                p=snapshot.child("Phone").getValue(String.class);
                phone.setText(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        //String v = myRef.child("Username").getValue

       // uname.setText(v);
        //System.out.println(myRef);

        //Log.d("sd",v);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
     //   notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
         //   @Override
         //   public void onChanged(@Nullable String s) {
             //   textView.setText(s);
           // }
       // });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setEnabled(false);
                save.setEnabled(true);
                editing=true;
                uname.setEnabled(true);
                phone.setEnabled(true);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eu=uname.getText().toString(), ep=phone.getText().toString();
                //connect firebase laa
                if(!eu.equals(u) && !u.isEmpty() && u.length()>4){
                    myRef.child("Username").setValue(eu);
                }
                else{
                    uname.setError("Must not be empty & Must be more than 5 Character");
                }

                if(!ep.equals(p)){
                    myRef.child("Phone").setValue(ep);
                }


                edit.setEnabled(true);
                save.setEnabled(false);
                editing=false;
                uname.setEnabled(false);
                phone.setEnabled(false);
            }
        });

        if(!NotificationsFragment.this.isVisible() && !editing){
            //Toast.makeText(getActivity(),"Text!",Toast.LENGTH_LONG).show();
            edit.setEnabled(true);
            save.setEnabled(false);
            editing=false;
            uname.setEnabled(false);
            phone.setEnabled(false);
        }
    }

}