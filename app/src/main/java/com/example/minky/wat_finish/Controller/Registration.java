package com.example.minky.wat_finish.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.minky.wat_finish.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private Spinner UserTypeSpinner;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("user");
    FirebaseAuth firebaseAuth;

    EditText nameEdit;
    EditText emailEdit;
    EditText passEdit;
    EditText passConfirm;

    private static final String TAG = "Registration :";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


    }

    private boolean addUser() {
        firebaseAuth = FirebaseAuth.getInstance();

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);
        
    }
}
