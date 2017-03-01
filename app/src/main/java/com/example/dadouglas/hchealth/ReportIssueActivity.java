package com.example.dadouglas.hchealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
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
    /*
    http://appsqa.harriscountytx.gov/PublicHealthMobile/publichealth.svc/UploadServiceRequest
    string ImageBytes
    string fileName (if you decide to pass an image it needs to be Base64 string, if not pass an empty string)
    string Place
    string Subject
    string Email
    string FirstName
    string LastName
    string ContactNumber
    string Description
    string ReceivedDevice (You can pass 1”)
*/
    Spinner spinner;
    JSONArray subjectsArray;
    Button submitIssueButton;
    EditText firstNameField, lastNameField, emailField, placeField, subjectField, descriptionField, contactNumberField;

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
        //  EditText firstNameField, lastNameField, emailField, placeField, subjectField, descriptionField, contactNumberField;

            /*
    http://appsqa.harriscountytx.gov/PublicHealthMobile/publichealth.svc/UploadServiceRequest
    string ImageBytes
    string fileName (if you decide to pass an image it needs to be Base64 string, if not pass an empty string)
    string Place
    string Subject
    string Email
    string FirstName
    string LastName
    string ContactNumber
    string Description
    string ReceivedDevice (You can pass 1”)
*/
        String firstName;
        String lastName;
        String email;
        String place;
        String subject;
        String description;
        String contactNumber;

        String recievedDevice = "1";
        String imageBytes = "";
        String fileName = "";

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
        description = descriptionField.getText().toString();

        contactNumberField = (EditText) findViewById(R.id.contactNumberField);
        contactNumber = contactNumberField.getText().toString();
        serviceRequest = new ServiceRequest(contactNumber, description, email, firstName, lastName, imageBytes, place, recievedDevice, subject, fileName);
        JSONObject serviceRequestAsJSONObject = serviceRequest.toJsonObject();

        UploadServiceRequest submitServiceRequest = new UploadServiceRequest();
        submitServiceRequest.execute(serviceRequestAsJSONObject);

        Log.d(ReportIssueActivity.class.getSimpleName(), submitServiceRequest.getStatus()+"");
        Log.d(ReportIssueActivity.class.getSimpleName(), serviceRequestAsJSONObject + "");
    }

    public String getFieldValue(EditText fieldName) {
        return fieldName.getText().toString();
    }


}

