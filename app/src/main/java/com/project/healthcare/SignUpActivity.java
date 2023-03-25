package com.project.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText username, mobile, email, password;
    Button btnsignup;
    TextView login;

    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    String userName, userMobile, userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnsignup = findViewById(R.id.signup);
        login = findViewById(R.id.btnLogin);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = username.getText().toString();
                userMobile = mobile.getText().toString();
                userEmail = email.getText().toString();
                userPassword = password.getText().toString();

//                database = FirebaseDatabase.getInstance();
//                reference = database.getReference("UsersDetails");
//                StoreUserDetails storeUserDetails = new StoreUserDetails(userName, userMobile, userEmail);
//                reference.child(userMobile).setValue(storeUserDetails);

                checkUser(userEmail, userPassword);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void checkUser(String userEmail, String userPassword) {

        //check email already exist or not.
        auth.fetchSignInMethodsForEmail(userEmail)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                        if (isNewUser) {
                            signupNewUser(userEmail, userPassword);
                        } else {
                            Log.e("TAG", "Is Old User!");
                            Toast.makeText(SignUpActivity.this, "Email already registered.Try another email", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void signupNewUser(String userEmail, String userPassword) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this, "Your account has been Created", Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseuser = auth.getCurrentUser();

                            String userID = firebaseuser.getUid();

                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("UsersDetails");
                            StoreUserDetails storeUserDetails = new StoreUserDetails(userName, userMobile, userEmail);
                            reference.child(userID).setValue(storeUserDetails);

                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Error occured", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}