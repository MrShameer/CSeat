package com.example.cseat.ui.notifications;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.cseat.ItemListDialogFragment;
import com.example.cseat.LoginActivity;
import com.example.cseat.QuickAccess;
import com.example.cseat.R;
import com.example.cseat.RegisterActivity;
import com.example.cseat.StudentData;
import com.example.cseat.UserData;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_OK = 1;
    private NotificationsViewModel notificationsViewModel;
    Boolean editing=false;
    Button logout,edit,save;
    EditText uname,emil,phone;
   private FirebaseAuth mAuth;
   private  FirebaseDatabase database = FirebaseDatabase.getInstance();
   private  FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private  DatabaseReference myRef = database.getReference("Users/"+currentFirebaseUser.getUid());
    String u,e,p;
    UserData userData = UserData.getInstance();
    ImageView pic;

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
        pic = root.findViewById(R.id.pic);

        mAuth=FirebaseAuth.getInstance();
        uname.setText(userData.getUname());
        emil.setText(userData.getEmail());
        phone.setText(userData.getPhone());

        u=userData.getUname();
        e=userData.getEmail();
        p=userData.getPhone();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return root;
    }


   public void  RefreshProfilePic() {
       if(userData.getUrl()!= null && !TextUtils.isEmpty(userData.getUrl())){
           Glide.with(getContext()).load(userData.getUrl()).into(pic);
       }
       else{
           pic.setImageResource(R.drawable.allayance);
       }
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RefreshProfilePic();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setEnabled(false);
                save.setEnabled(true);
                editing=true;
                uname.setEnabled(true);
                phone.setEnabled(true);
                Toast.makeText(getActivity(),"You can edit now",Toast.LENGTH_SHORT).show();
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemListDialogFragment.newInstance(NotificationsFragment.this).show(getActivity().getSupportFragmentManager(), "dialog");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eu=uname.getText().toString(), ep=phone.getText().toString();
                if(u.length()>4){
                    if(!eu.equals(u)){
                        myRef.child("Username").setValue(eu);
                        userData.setUname(eu);
                    }
                }
                else{
                    uname.setError("Must not be empty & Must be more than 5 Character");
                }

                if(!ep.equals(p)){
                    myRef.child("Phone").setValue(ep);
                    userData.setPhone(ep);
                }
                edit.setEnabled(true);
                save.setEnabled(false);
                editing=false;
                uname.setEnabled(false);
                phone.setEnabled(false);
                Toast.makeText(getActivity(),"Information Updated",Toast.LENGTH_SHORT).show();
            }
        });

        if(!NotificationsFragment.this.isVisible() && !editing){
            edit.setEnabled(true);
            save.setEnabled(false);
            editing=false;
            uname.setEnabled(false);
            phone.setEnabled(false);
        }
    }

    public void change(){
        pic.setImageBitmap(userData.getBitmap());
    }
}