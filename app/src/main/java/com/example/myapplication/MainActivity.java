package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextDob;
    private RadioGroup radioGroupImages;
    private Button btnSave, btnViewDetails;
    DatabaseHelper dbHelper; // 'this' refers to your current activity or context

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextDob = findViewById(R.id.editTextDob);
        radioGroupImages = findViewById(R.id.radioGroupImages);
        btnSave = findViewById(R.id.buttonSave);
        btnViewDetails = findViewById(R.id.buttonViewDetails);

        // Set the current date without the time to the "editTextDob" field
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        editTextDob.setText(currentDate);

        // Initialize your dbHelper here
        dbHelper = new DatabaseHelper(this);

        // initialize the datepicker with the current date
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, null, currentYear, currentMonth, currentDay
        );

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update EditText with the selected date
                editTextDob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        });

        editTextDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the DatePicker when the EditText is clicked
                datePickerDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from input fields
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String dateOfBirth = editTextDob.getText().toString(); // Use the value from EditText

                // Get selected RadioButton text
                int selectedRadioButtonId = radioGroupImages.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedImage = selectedRadioButton.getTag().toString();
                RadioButton radioButton1 = findViewById(R.id.radioButtonImage1);
                radioButton1.setTag("image1");
                RadioButton radioButton2 = findViewById(R.id.radioButtonImage2);
                radioButton2.setTag("image2");
                RadioButton radioButton3 = findViewById(R.id.radioButtonImage3);
                radioButton3.setTag("image3");

                long newRowId = dbHelper.addUserData(name, dateOfBirth, email, selectedRadioButton.getTag().toString());

                if (newRowId != -1) {
                    // Insertion successful
                    // Display a success message with the saved data
                    displayAlertDialog(name, email, dateOfBirth, selectedImage);
                }
            }
        });

        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to the ViewDetailsActivity
                Intent intent = new Intent(MainActivity.this, ViewDetail.class);

                // Pass relevant data as extras
                intent.putExtra("name", editTextName.getText().toString());
                intent.putExtra("email", editTextEmail.getText().toString());
                intent.putExtra("dob", editTextDob.getText().toString());

                // Get the selected image tag from the checked RadioButton
                int selectedRadioButtonId = radioGroupImages.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedImage = selectedRadioButton.getTag().toString();
                intent.putExtra("imageSelected", selectedImage);

                startActivity(intent);
            }
        });
    }

    private void displayAlertDialog(String name, String email, String dateOfBirth, String selectedImage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        // Create a message with the saved data
        StringBuilder message = new StringBuilder();
        message.append("Name: ").append(name).append("\n");
        message.append("Email: ").append(email).append("\n");
        message.append("Date of Birth: ").append(dateOfBirth).append("\n");
        message.append("Selected Image: ").append(selectedImage);
        builder.setMessage(message.toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}