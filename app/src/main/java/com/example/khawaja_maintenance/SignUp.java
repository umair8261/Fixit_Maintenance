package com.example.khawaja_maintenance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity
{
    FirebaseAuth FA;
    EditText edt_mail,edt_pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        FA = FirebaseAuth.getInstance();

        edt_mail = findViewById(R.id.edt_userEmail);
        edt_pass = findViewById(R.id.edt_userPassword);
        btn_signup=findViewById(R.id.user_sign_up_btn);

        btn_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ((edt_mail.getText().toString()).equals("") || (edt_pass.getText().toString()).equals(""))
                {
                    Toast.makeText(SignUp.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                    Log.d("USER_DATA","Please Enter Credentials " );
                }
                else
                {

                    FA.createUserWithEmailAndPassword(edt_mail.getText().toString(),edt_pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if(task.isSuccessful())
                                    {
                                        String u_email, uid;
                                        FirebaseUser firebaseUser = FA.getCurrentUser();
                                        u_email = firebaseUser.getEmail();
                                        uid = firebaseUser.getUid();
                                        Log.d("USER_DATA", "Email  : " + u_email + "  PAss : " + uid);
                                        HashMap<String, String> user = new HashMap<>();
                                        user.put("Email", u_email);
                                        user.put("Uid", uid);
                                        databaseReference.child(uid).setValue(user);

                                        Intent intent = new Intent(SignUp.this,Login.class);
                                        startActivity(intent);

                                    }else{
                                        Log.e("ERROR",task.getException().toString());
                                        Toast.makeText(SignUp.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                }
                            });


                }
            }
        });
    }
}