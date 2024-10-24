package com.example.votingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewVoterIdActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADDRESS_PROOF = 1;
    private static final int REQUEST_CODE_AGE_PROOF = 2;
    private static final int REQUEST_CODE_IDENTITY_PROOF = 3;
    private static final int REQUEST_CODE_PHOTO = 4;

    private EditText nameInput;
    private EditText fathersNameInput;
    private EditText dobInput;
    private RadioGroup sexGroup;
    private RadioButton sexMale;
    private RadioButton sexFemale;
    private EditText addressProofInput;
    private EditText ageProofInput;
    private EditText identityProofInput;
    private Uri photoUri;

    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_voter_id);

        nameInput = findViewById(R.id.nameInput);
        fathersNameInput = findViewById(R.id.fathersNameInput);
        dobInput = findViewById(R.id.dobInput);
        sexGroup = findViewById(R.id.sexGroup);
        sexMale = findViewById(R.id.sexMale);
        sexFemale = findViewById(R.id.sexFemale);
        addressProofInput = findViewById(R.id.addressProofInput);
        ageProofInput = findViewById(R.id.ageProofInput);
        identityProofInput = findViewById(R.id.identityProofInput);
        Button uploadAddressProofButton = findViewById(R.id.uploadAddressProofButton);
        Button uploadAgeProofButton = findViewById(R.id.uploadAgeProofButton);
        Button uploadIdentityProofButton = findViewById(R.id.uploadIdentityProofButton);
        Button uploadPhotoButton = findViewById(R.id.uploadPhotoButton);
        Button submitButton = findViewById(R.id.submitButton);

        uploadAddressProofButton.setOnClickListener(v -> selectDocument(REQUEST_CODE_ADDRESS_PROOF));
        uploadAgeProofButton.setOnClickListener(v -> selectDocument(REQUEST_CODE_AGE_PROOF));
        uploadIdentityProofButton.setOnClickListener(v -> selectDocument(REQUEST_CODE_IDENTITY_PROOF));
        uploadPhotoButton.setOnClickListener(v -> selectPhoto());

        dobInput.setOnClickListener(v -> showDatePickerDialog());

        submitButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String fathersName = fathersNameInput.getText().toString().trim();
            String dob = dobInput.getText().toString().trim();
            int selectedSexId = sexGroup.getCheckedRadioButtonId();
            String sex = selectedSexId == R.id.sexMale ? "Male" : selectedSexId == R.id.sexFemale ? "Female" : "";

            if (name.isEmpty() || fathersName.isEmpty() || dob.isEmpty() || sex.isEmpty()) {
                Toast.makeText(NewVoterIdActivity.this, "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
            } else {
                if (isAbove18(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))) {
                    Intent intent = new Intent(NewVoterIdActivity.this, VoterIdCardActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("fathersName", fathersName);
                    intent.putExtra("dob", dob);
                    intent.putExtra("sex", sex);
                    if (photoUri != null) {
                        intent.putExtra("photoUri", photoUri.toString());
                    }

                    // Optional document URIs
                    if (!addressProofInput.getText().toString().isEmpty()) {
                        intent.putExtra("addressProofUri", addressProofInput.getText().toString());
                    }
                    if (!ageProofInput.getText().toString().isEmpty()) {
                        intent.putExtra("ageProofUri", ageProofInput.getText().toString());
                    }
                    if (!identityProofInput.getText().toString().isEmpty()) {
                        intent.putExtra("identityProofUri", identityProofInput.getText().toString());
                    }

                    startActivity(intent);
                } else {
                    Toast.makeText(NewVoterIdActivity.this, "You must be above 18 to register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NewVoterIdActivity.this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDobInput();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDobInput() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dobInput.setText(sdf.format(calendar.getTime()));
    }

    private void selectDocument(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, requestCode);
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedUri = data.getData();

            switch (requestCode) {
                case REQUEST_CODE_ADDRESS_PROOF:
                    addressProofInput.setText(selectedUri.toString());
                    break;
                case REQUEST_CODE_AGE_PROOF:
                    ageProofInput.setText(selectedUri.toString());
                    break;
                case REQUEST_CODE_IDENTITY_PROOF:
                    identityProofInput.setText(selectedUri.toString());
                    break;
                case REQUEST_CODE_PHOTO:
                    photoUri = selectedUri;
                    break;
            }
        }
    }

    private boolean isAbove18(int year, int month, int day) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - year;

        if (today.get(Calendar.MONTH) < month || (today.get(Calendar.MONTH) == month && today.get(Calendar.DAY_OF_MONTH) < day)) {
            age--;
        }
        return age >= 18;
    }
}
