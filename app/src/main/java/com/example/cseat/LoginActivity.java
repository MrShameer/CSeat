package com.example.cseat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

    TextView sgup,forgot;
    EditText email,password;
    Button login;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*if(user !=null) {
            // User is signed in
            Intent i = new Intent(LoginActivity.this, QuickAccess.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
            //Log.d(TAG, "onAuthStateChanged:signed_out");
        }*/

        sgup = findViewById(R.id.signup);
        forgot= findViewById(R.id.forgotpassword);
        email= findViewById(R.id.textemail);
        password= findViewById(R.id.password);

        login=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(LoginActivity.this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        sgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, forgotpassword.class);
                startActivity(intent);
            }
        });

    }

    private void checkCredentials() {
        //try {
            String em = email.getText().toString(), pass = password.getText().toString();

        if (em.isEmpty() || !em.contains("@")) {
            email.setError("Must be a valid email address");
        } else if (pass.isEmpty()) {
            password.setError("Must not be empty");
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Jap eh nk check. Sat nanti i bagitau");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(LoginActivity.this, QuickAccess.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                        mLoadingBar.dismiss();
                    }
                }
            });
        }
    //}
       // catch(Exception e){
           // return;
        //}
    }
}