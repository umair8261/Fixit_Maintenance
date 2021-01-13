package com.example.khawaja_maintenance;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class User_information extends AppCompatActivity
{
    EditText edit_date;
    int year,month,day;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth FA;
    User user;
    String st_name, st_city, st_date,st_time, st_address, st_mobile, st_instruction, st_moreservices;
    EditText edt_name, edt_city, edt_date,edt_time, edt_address, edt_mobile, edt_instruction, edt_moreservices;
    String value;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        value=getIntent().getStringExtra("selection");
        Log.i("TAG",value);

        edt_name=findViewById(R.id.edt_fullname);
        edt_city=findViewById(R.id.edt_city);
        edt_date=findViewById(R.id.edt_date);
        edt_time=findViewById(R.id.edt_usertime);
        edt_address=findViewById(R.id.edt_useraddress);
        edt_mobile=findViewById(R.id.edt_usernumber);
        edt_instruction=findViewById(R.id.edt_userInstruction);
        edt_moreservices=findViewById(R.id.edt_moreservices);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(value);

        edit_date=findViewById(R.id.edt_date);
        final Calendar calendar= Calendar.getInstance();
        edit_date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(User_information.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
                    {
                        edit_date.setText(SimpleDateFormat.getInstance().format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    public void submit_req(View view)
    {
        databaseReference=firebaseDatabase.getReference();
        user=new User();

        st_name=edt_name.getText().toString();
        st_city=edt_city.getText().toString();
        st_address=edt_address.getText().toString();
        st_date=edt_date.getText().toString();
        st_instruction=edt_instruction.getText().toString();
        st_mobile=edt_mobile.getText().toString();
        st_moreservices=edt_moreservices.getText().toString();
        st_time=edt_time.getText().toString();

        if ((edt_name.getText().toString()).equals("") || (edt_city.getText().toString()).equals("") || (edt_address.getText().toString()).equals("")
                || (edt_date.getText().toString()).equals("") || (edt_mobile.getText().toString()).equals("") || (edt_time.getText().toString()).equals(""))
        {
            if (TextUtils.isEmpty(st_name))
            {
                edt_name.setError("Email is Required");
            }
            if (TextUtils.isEmpty(st_city))
            {
                edt_city.setError("City is Required");
            }
            if (TextUtils.isEmpty(st_address))
            {
                edt_address.setError("Address is Required");
            }
            if (TextUtils.isEmpty(st_date))
            {
                edt_date.setError("Date is Required");
            }
            if (TextUtils.isEmpty(st_mobile))
            {
                edt_mobile.setError("Mobile Number is Required");
            }
            if (TextUtils.isEmpty(st_time))
            {
                edt_time.setError("Time is Required");
            }
        }
        else
        {
            user.setName(st_name);
            user.setCity(st_city);
            user.setAddress(st_address);
            user.setDate(st_date);
            user.setInstruction(st_instruction);
            user.setMobile(st_mobile);
            user.setMore_services(st_moreservices);
            user.setTime(st_time);

            int min = 1;
            int max = 1000000;
            Random r = new Random();
            int num = r.nextInt(max - min + 1) + min;

            user.setOrder_no(num);



            /*FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");

            myRef.setValue("Hello, World!");*/


            HashMap<String, String> data = new HashMap<>();
            data.put("Name", st_name);
            data.put("City", st_city);
            data.put("Address", st_address);
            data.put("Date", st_date);
            data.put("Instruction", st_instruction);
            data.put("Mobile", st_mobile);
            data.put("MoreServices", st_moreservices);
            data.put("Time", st_time);
            data.put("Order_No", String.valueOf(num));




            databaseReference.child("Orders").child(value).push().setValue(user);

            Log.i("TAG", String.valueOf(databaseReference));
            Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();

            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                    String namee=edt_name.getText().toString();
                    if (namee.isEmpty())
                    {
                        Toast.makeText(User_information.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        notification();
                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////

            Intent intent=new Intent(this,Order_Generate.class);
            intent.putExtra("Order",num);
            startActivity(intent);
        }
    }
    private void notification()
    {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            {
                NotificationChannel channel=new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager=getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"n").setContentText("Umair")
                .setSmallIcon(R.drawable.home_icon)
                .setAutoCancel(true)
                .setContentText("Neew Data is added");

        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());



    }
}