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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";
    TextView sgup,forgot;
    EditText email,password;
    Button login,google,facebbok;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
   // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseUser currentUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;

    Intent intent;
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

        google=findViewById(R.id.google);
        facebbok=findViewById(R.id.facebook);

        login=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(LoginActivity.this);

        intent = new Intent(LoginActivity.this, QuickAccess.class);


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        mAuth = FirebaseAuth.getInstance();

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
                intent.putExtra("enable", "yes");
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

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        facebbok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
               // Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken(),account);

               // Toast.makeText(LoginActivity.this,"masuk task",Toast.LENGTH_SHORT).show();


                if(task.isSuccessful()){
                    // Intent intent = new Intent(LoginActivity.this, QuickAccess.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                     //startActivity(intent);
                }

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
               // Log.w(TAG, "Google sign in failed", e);

                // [START_EXCLUDE]
               // updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken, GoogleSignInAccount acc) {
        // [START_EXCLUDE silent]
        // [END_EXCLUDE]
        //Toast.makeText(LoginActivity.this,idToken+"iddd",Toast.LENGTH_SHORT).show();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        // mAuth.signInWi
        mLoadingBar.setTitle("Login");
        mLoadingBar.setMessage("Jap eh nk check. Sat nanti i bagitau");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           //   Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            currentUser = mAuth.getCurrentUser();
                            // Toast.makeText(LoginActivity.this,"Successfully Regisfdgdftered",Toast.LENGTH_SHORT).show();
                            //mLoadingBar.dismiss();
                            DatabaseReference myRef = database.getReference("Users/"+currentUser.getUid());
                            //DatabaseReference use = myRef.child(currentFirebaseUser.getUid());
                            myRef.child("Email").setValue(acc.getEmail());
                            myRef.child("Username").setValue(acc.getDisplayName());
                            myRef.child("Phone").setValue("");

                            Toast.makeText(LoginActivity.this,"Sign In Successful",Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });


//prob siniiiiiiii tak bole intent and database satu masa

        if(currentUser!=null){
            mLoadingBar.dismiss();
            startActivity(intent);
        }





    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

        //Toast.makeText(LoginActivity.this, "sign in" ,Toast.LENGTH_LONG).show();
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