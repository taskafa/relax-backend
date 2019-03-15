package team1.ge401.bilkent.edu.tr.birdie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HowStressedTestActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables
    private Button submitButton;
    private Button returnToMainActivity;
    private EditText editAge;
    private EditText editPulseRate;

    // Variables - Radio buttons for gender
    private RadioGroup radioGroup;
    private RadioButton radioButtonFemale;
    private RadioButton radioButtonMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_stressed_test);

        // Initialize widget objects
        submitButton = findViewById(R.id.buttonSubmitStressedTest);
        returnToMainActivity = findViewById(R.id.buttonMainActivityFromStressed);
        radioGroup = findViewById(R.id.radioGroupGender);
        radioButtonFemale = findViewById(R.id.buttonFemale);
        radioButtonMale = findViewById(R.id.buttonMale);
        editAge = findViewById(R.id.editAge);
        editPulseRate = findViewById(R.id.editPulseRate);

        // Listen to the buttons
        submitButton.setOnClickListener(this);
        returnToMainActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == submitButton) {
            Toast.makeText(this, "Not implemented yet.", Toast.LENGTH_SHORT).show();
        }
        else if (v == returnToMainActivity) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
