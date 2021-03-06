package com.example.dadouglas.hchealth;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class ReportIssueActivity extends AppCompatActivity {

    Spinner spinner;
    JSONArray subjectsArray;
    Button submitIssueButton;
    EditText firstNameField, lastNameField, emailField, placeField, descriptionField, contactNumberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_issue);

        // Call service to get Issue subjects and populate dropdown menu with subjects
        GetIssueSubjects getIssuesRequest = new GetIssueSubjects();

        try {
            // Call GetIssueSubject service
            subjectsArray = getIssuesRequest.execute().get();

            // Get spinner item
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);


            // add values to spinner
            for (int i = 0; i < subjectsArray.length(); i++) {
                String value = subjectsArray.getJSONObject(i).getString("Name");
                spinnerAdapter.add(value);

            }

            //update spinner
            spinnerAdapter.notifyDataSetChanged();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        submitIssueButton = (Button) findViewById(R.id.submitIssueButton);
        submitIssueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SubmitRequest();
            }
        });
    }

    private void SubmitRequest() {
        ServiceRequest serviceRequest;
        String firstName;
        String lastName;
        String email;
        String place;
        String subject;
        String description;
        String contactNumber;

        String receivedDevice = "1";
        String imageBytes = "";
        String fileName = "";

        // Get values from fields on form
        firstNameField = (EditText) findViewById(R.id.firstNameField);
        firstName = getFieldValue(firstNameField);

        lastNameField = (EditText) findViewById(R.id.lastNameField);
        lastName = getFieldValue(lastNameField);

        emailField = (EditText) findViewById(R.id.emailAddressField);
        email = getFieldValue(emailField);

        placeField = (EditText) findViewById(R.id.enter_business_field);
        place = getFieldValue(placeField);

        spinner = (Spinner) findViewById(R.id.spinner);
        subject = spinner.getSelectedItem().toString();

        descriptionField = (EditText) findViewById(R.id.descriptionTextField);
        description = getFieldValue(descriptionField);

        contactNumberField = (EditText) findViewById(R.id.contactNumberField);
        contactNumber = getFieldValue(contactNumberField);

        // Create ServiceRequest object
        serviceRequest = new ServiceRequest(contactNumber, description, email, firstName, lastName, imageBytes, place, receivedDevice, subject, fileName);
        JSONObject serviceRequestAsJSONObject = serviceRequest.toJsonObject();

        // Submit Request
        UploadServiceRequest submitServiceRequest = new UploadServiceRequest();
        submitServiceRequest.execute(serviceRequestAsJSONObject);
    }

    // Get the value of EditText Fields
    public String getFieldValue(EditText fieldName) {
        return fieldName.getText().toString();
    }


    // Choose to either select photo from gallery or take new photo
    public void ChooseGetPhotoOption(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReportIssueActivity.this);

        builder.setTitle("Upload Photo");

        builder.setPositiveButton("Select from Gallery", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent choosePhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(choosePhoto, 1);
            }
        });
        builder.setNegativeButton("Take Photo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhoto, 0);
            }
        });
        builder.show();
    }
}

