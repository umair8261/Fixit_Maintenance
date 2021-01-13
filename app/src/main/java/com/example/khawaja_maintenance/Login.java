package com.example.khawaja_maintenance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edt_mail,edt_pass;
    private FirebaseAuth FA;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        FA=FirebaseAuth.getInstance();

        edt_mail=findViewById(R.id.login_edt);
        edt_pass=findViewById(R.id.login_pass);
        btn_signin=findViewById(R.id.btn_submit);

        btn_signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email,password;
                FirebaseUser FU = FA.getCurrentUser();
                if ((edt_mail.getText().toString()).equals("") || (edt_pass.getText().toString()).equals(""))
                {
                    Toast.makeText(Login.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                    Log.d("USER_DATA","Please Enter Credentials " );
                }
                else
                {
                    email=edt_mail.getText().toString();
                    password=edt_pass.getText().toString();
                    Log.d("USER_DATA","Email  : " + email + "  Pass : " + password  );
                    FA.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(Login.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task)
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
                                            Toast.makeText(Login.this, "Login As  " + u_email, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Login.this, Home_Screen.class);
                                            startActivity(intent);

                                        }
                                    });
                }
            }
        });



    }

    public void signup(View view)
    {
        Intent intent= new Intent(this, SignUp.class);
        startActivity(intent);
    }

}