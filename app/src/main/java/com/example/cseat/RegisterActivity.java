package com.example.cseat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    TextView acc,info;
    private EditText username, email, password, conpass;
    Button register;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog mLoadingBar;
    int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        acc = findViewById(R.id.ha);
        username=findViewById(R.id.username);
        email=findViewById(R.id.textemail);
        password=findViewById(R.id.password);
        conpass=findViewById(R.id.conpassword);
        register=findViewById(R.id.register);

        info=findViewById(R.id.info);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(RegisterActivity.this);

        if(getIntent().getStringExtra("enable")!="no"){
            email.setEnabled(false);
            email.setText(getIntent().getStringExtra("enable"));
            username.setText(getIntent().getStringExtra("name"));

            info.setVisibility(View.INVISIBLE);
            acc.setVisibility(View.INVISIBLE);

        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void checkCredentials() {
        String un=username.getText().toString(), em=email.getText().toString(), ps=password.getText().toString(), cps=conpass.getText().toString();
        if(un.isEmpty() || un.length()<5){
            username.setError("Must not be empty & Must be more than 5 Character");
        }
        else if(em.isEmpty() || !em.contains("@")){
            email.setError("Must be a valid email address");
        }
        else if(ps.isEmpty() || ps.length()<7){
            password.setError("Must not be empty & Must be more than 7 Character");
        }
        else if(cps.isEmpty() || !cps.equals(ps)){
            conpass.setError("Password does not match");
        }
        else{
            //register laa
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Jap eh awak");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(em,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(RegisterActivity.this,task + "",Toast.LENGTH_SHORT).show();
                    if(task.isSuccessful()){
                        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                        Toast.makeText(RegisterActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                        DatabaseReference myRef = database.getReference("Users/"+currentFirebaseUser.getUid());
                        //DatabaseReference use = myRef.child(currentFirebaseUser.getUid());
                        myRef.child("Email").setValue(em);
                        myRef.child("Username").setValue(un);
                        myRef.child("Phone").setValue("");
                        //.setValue(un);

                        /*UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .build();*/

                        Intent intent = new Intent(RegisterActivity.this, QuickAccess.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{

                        Toast.makeText(RegisterActivity.this, task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                        mLoadingBar.dismiss();
                    }
                }
            });
        }
    }
}