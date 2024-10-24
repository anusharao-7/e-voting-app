package com.example.votingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateVoterIdActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADDRESS_PROOF = 1;
    private static final int REQUEST_CODE_AGE_PROOF = 2;
    private static final int REQUEST_CODE_IDENTITY_PROOF = 3;
    private static final int REQUEST_CODE_PHOTO = 4;

    private EditText nameInput, fathersNameInput, dobInput, sexInput, addressProofInput, ageProofInput, identityProofInput;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_voter_id);

        nameInput = findViewById(R.id.nameInput);
        fathersNameInput = findViewById(R.id.fathersNameInput);
        dobInput = findViewById(R.id.dobInput);
        sexInput = findViewById(R.id.sexInput);
        addressProofInput = findViewById(R.id.addressProofInput);
        ageProofInput = findViewById(R.id.ageProofInput);
        identityProofInput = findViewById(R.id.identityProofInput);
        Button uploadAddressProofButton = findViewById(R.id.uploadAddressProofButton);
        Button uploadAgeProofButton = findViewById(R.id.uploadAgeProofButton);
        Button uploadIdentityProofButton = findViewById(R.id.uploadIdentityProofButton);
        Button uploadPhotoButton = findViewById(R.id.uploadPhotoButton);
        Button updateButton = findViewById(R.id.updateButton);

        // Pre-fill inputs with existing data
        Intent intent = getIntent();
        nameInput.setText(intent.getStringExtra("name"));
        fathersNameInput.setText(intent.getStringExtra("fathersName"));
        dobInput.setText(intent.getStringExtra("dob"));
        sexInput.setText(intent.getStringExtra("sex"));
        photoUri = Uri.parse(intent.getStringExtra("photoUri"));

        uploadAddressProofButton.setOnClickListener(v -> selectDocument(REQUEST_CODE_ADDRESS_PROOF));
        uploadAgeProofButton.setOnClickListener(v -> selectDocument(REQUEST_CODE_AGE_PROOF));
        uploadIdentityProofButton.setOnClickListener(v -> selectDocument(REQUEST_CODE_IDENTITY_PROOF));
        uploadPhotoButton.setOnClickListener(v -> selectPhoto());

        updateButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String fathersName = fathersNameInput.getText().toString().trim();
            String dob = dobInput.getText().toString().trim();
            String sex = sexInput.getText().toString().trim();

            if (name.isEmpty() || fathersName.isEmpty() || dob.isEmpty() || sex.isEmpty() || photoUri == null) {
                Toast.makeText(UpdateVoterIdActivity.this, "Please fill all fields and upload a photo", Toast.LENGTH_SHORT).show();
            } else {
                Intent updateIntent = new Intent(UpdateVoterIdActivity.this, VoterIdCardActivity.class);
                updateIntent.putExtra("name", name);
                updateIntent.putExtra("fathersName", fathersName);
                updateIntent.putExtra("dob", dob);
                updateIntent.putExtra("sex", sex);
                updateIntent.putExtra("photoUri", photoUri.toString());
                startActivity(updateIntent);
            }
        });
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
            Uri uri = data.getData();
            switch (requestCode) {
                case REQUEST_CODE_ADDRESS_PROOF:
                    addressProofInput.setText(uri.toString());
                    break;
                case REQUEST_CODE_AGE_PROOF:
                    ageProofInput.setText(uri.toString());
                    break;
                case REQUEST_CODE_IDENTITY_PROOF:
                    identityProofInput.setText(uri.toString());
                    break;
                case REQUEST_CODE_PHOTO:
                    photoUri = uri;
                    break;
            }
        }
    }
}
