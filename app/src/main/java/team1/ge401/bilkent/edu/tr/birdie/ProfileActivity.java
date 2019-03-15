package team1.ge401.bilkent.edu.tr.birdie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables - Layout elements of profile activity
    private Button howStressed;
    private Button meditations;
    private Button signOut;
    private Button progress;
    private Button personal;
    private Button calendar;

    // Variable - Other variables
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize widget objects
        howStressed = findViewById(R.id.buttonHowStressedTest);
        meditations = findViewById(R.id.buttonMeditations);
        signOut = findViewById(R.id.buttonSignOut);
        personal = findViewById(R.id.buttonPersonal);
        progress = findViewById(R.id.buttonProgress);
        calendar = findViewById(R.id.buttonCalendar);

        // Connect to Firebase auth service so that app can sign out if user wants
        firebaseAuth = FirebaseAuth.getInstance();

        // Listen all of the buttons
        howStressed.setOnClickListener(this);
        meditations.setOnClickListener(this);
        signOut.setOnClickListener(this);
        progress.setOnClickListener(this);
        calendar.setOnClickListener(this);
        personal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signOut) { // Sign out
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if (v == howStressed) { // How stressed test activity
            finish();
            startActivity(new Intent(this, HowStressedTestActivity.class));
        }
        else if(v == calendar || v == personal || v == progress || v == meditations) { // Activities which are not implemented yet
            Toast.makeText(this, "Not implemented yet.", Toast.LENGTH_SHORT).show();
        }
    }
}
