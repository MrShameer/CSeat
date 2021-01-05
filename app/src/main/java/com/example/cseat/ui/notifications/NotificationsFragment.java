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
    Button logout,edit;
    EditText uname,emil;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        logout = root.findViewById(R.id.logout);
        edit = root.findViewById(R.id.edit);
        uname=root.findViewById(R.id.uname);
        emil=root.findViewById(R.id.editemail);
        mAuth=FirebaseAuth.getInstance();

        DatabaseReference myRef = database.getReference("Users/"+currentFirebaseUser.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uname.setText(snapshot.child("Username").getValue(String.class));
                emil.setText(snapshot.child("Email").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

}