package com.example.dadouglas.hchealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;


public class ReportIssueActivity extends AppCompatActivity {
    Spinner spinner;
    JSONArray subjectsArray;

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
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);


            // add values to spinner
            for (int i = 0; i < subjectsArray.length(); i++) {
                String value = subjectsArray.getJSONObject(i).getString("Name");
                spinnerAdapter.add(value);

            }

            //update spinner
            spinnerAdapter.notifyDataSetChanged();

        }
        catch (InterruptedException e) { e.printStackTrace(); }
        catch (ExecutionException e) { e.printStackTrace(); }
        catch (JSONException e) { e.printStackTrace(); }
    }
}
