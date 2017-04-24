package com.example.minky.wat_finish.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.minky.wat_finish.Model.RegisteredUsers;
import com.example.minky.wat_finish.Model.Standing;
import com.example.minky.wat_finish.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private Spinner userTypeSpinner;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("user");
    FirebaseAuth firebaseAuth;

    EditText nameEdit;
    EditText emailEdit;
    EditText passEdit;
    EditText passConfirm;

    Button submitBtn;
    Button cancelBtn;

    String email;
    String password;
    String passwordConfirm;

    private static final String TAG = "Registration :";

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][da-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);
        passConfirm = (EditText) findViewById(R.id.passConfirm);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.CancelBtn);

        userTypeSpinner = (Spinner) findViewById(R.id.userType);
        ArrayAdapter<Standing> adapter = new ArrayAdapter<Standing>(
                this, android.R.layout.simple_spinner_item,
                Arrays.asList(Standing.values()));
        userTypeSpinner.setAdapter(adapter);

        email = emailEdit.getText().toString();
        password = passEdit.getText().toString();
        passwordConfirm = passConfirm.getText().toString();

        if (!testValidEmail(email)) {
            Toast emailToast = Toast.makeText(getApplicationContext(),
                    "Provide valid email!", Toast.LENGTH_SHORT);
        } else if (password.length() < 6) {
            Toast passToast = Toast.makeText(getApplicationContext(),
                    "Provide longer password!", Toast.LENGTH_SHORT);
        } else if (!password.equals(passwordConfirm)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Password and Confirmation does not match!",
                    Toast.LENGTH_LONG);
        } else {
            Log.d(TAG, "Email and Password are both valid.");
            RegisteredUsers newUser = new RegisteredUsers(nameEdit.getText().toString(),
                    email, password, (Standing) userTypeSpinner.getSelectedItem());
            addUser(newUser);
        }
    }

    private void addUser(final RegisteredUsers newUser) {
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Registration.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail : " + task.isSuccessful());
                        if (task.isSuccessful()) {
                            String id = databaseReference.push().getKey();
                            databaseReference.child(id).setValue(newUser);
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Registration Successful!",
                                    Toast.LENGTH_SHORT);
//                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }
                });
    }

    public boolean testValidEmail(String email) {
        if (EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }
}
