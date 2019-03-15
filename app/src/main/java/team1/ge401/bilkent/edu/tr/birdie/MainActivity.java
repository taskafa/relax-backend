package team1.ge401.bilkent.edu.tr.birdie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize layout objects
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewSignin = findViewById(R.id.TextViewSignIn);
        progressDialog = new ProgressDialog(this);

        // Go directly into profile page if user has been logged in
        if(firebaseAuth.getCurrentUser() != null) {
            // start profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            registerUser();
        }
        if (v == textViewSignin) {
            // will open sign in activity here
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void registerUser() {
        String textEmail = editTextEmail.getText().toString().trim();
        String textPassword = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(textEmail)) {
            // Text is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(textPassword)) {
            // Password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        // If validations are OK, it will first show a progress bar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        // Do the registration to Firebase database
        firebaseAuth.createUserWithEmailAndPassword(textEmail, textPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // User is successfully registered and logged in
                            // Profile activity will start here
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                        else {
                            // Registration is failed
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
