package com.example.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView seekText, progressLabel;
    EditText editText;
    Spinner spinner;
    CheckBox checkBox;
    RadioGroup radioGroup;
    Switch switchButton;
    Button submitButton;
    ProgressBar progressBar;

    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Views
        seekBar = findViewById(R.id.seekBarr);
        seekText = findViewById(R.id.SeekText);
        editText = findViewById(R.id.edit_text);
        spinner = findViewById(R.id.Spinnerr);
        checkBox = findViewById(R.id.checkbox11);
        radioGroup = findViewById(R.id.radio_groupp);
        switchButton = findViewById(R.id.switchh);
        submitButton = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        progressLabel = findViewById(R.id.progressLabel);
        RadioButton radio1 = findViewById(R.id.radio1);
        RadioButton radio2 = findViewById(R.id.radio2);
        RadioButton radio3 = findViewById(R.id.radio3);
        TextView textLabel = findViewById(R.id.text);
        TextView spinLabel = findViewById(R.id.spinText);
        TextView progressLabel = findViewById(R.id.progressLabel);
        TextView radioLabel = findViewById(R.id.text_view11);
        CheckBox checkBox11 = findViewById(R.id.checkbox11);
        Switch switchh = findViewById(R.id.switchh);
        Button button = findViewById(R.id.button);


        // Spinner setup
        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Spinner text color fix (for selected item)
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // SeekBar update
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float fontSize = Math.max(12, progress);  // minimum 12sp for readability

                seekText.setText("Text Size : " + progress);
                seekText.setTextSize(fontSize);
                editText.setTextSize(fontSize);
                editText.setHintTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                button.setTextSize(fontSize);
                radio1.setTextSize(fontSize);
                radio2.setTextSize(fontSize);
                radio3.setTextSize(fontSize);
                textLabel.setTextSize(fontSize);
                spinLabel.setTextSize(fontSize);
                progressLabel.setTextSize(fontSize);
                radioLabel.setTextSize(fontSize);
                checkBox11.setTextSize(fontSize);
                switchh.setTextSize(fontSize);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Submit button logic
        submitButton.setOnClickListener(v -> {

            int progress = 0;

            String name = editText.getText().toString().trim();
            String gender = spinner.getSelectedItem().toString();
            boolean isChecked = checkBox.isChecked();
            boolean isSwitched = switchButton.isChecked();
            int selectedRadioId = radioGroup.getCheckedRadioButtonId();

            String favoriteColor = "None";
            if (selectedRadioId != -1) {
                RadioButton selectedRadio = findViewById(selectedRadioId);
                favoriteColor = selectedRadio.getText().toString();
                progress += 20;
            }

            if (!name.isEmpty()) progress += 20;
            if (!gender.isEmpty()) progress += 20;
            if (isChecked) progress += 20;
            if (isSwitched) progress += 20;

            progressBar.setProgress(progress);
            progressLabel.setText("Completed % : " + progress);

            // Toast display
            String summary = "Name: " + name +
                    "\nGender: " + gender +
                    "\nSubscribed: " + (isChecked ? "Yes" : "No") +
                    "\nFavorite Color: " + favoriteColor +
                    "\nAccepted: " + (isSwitched ? "Yes" : "No");
            Toast.makeText(MainActivity.this, summary, Toast.LENGTH_LONG).show();
        });
    }
}

