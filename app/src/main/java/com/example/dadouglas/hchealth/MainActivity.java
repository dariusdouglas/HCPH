package com.example.dadouglas.hchealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Start report
    public void startReportIssueActivity(View view) {
        Intent intent = new Intent(this, ReportIssueActivity.class);
        startActivity(intent);
    }

    public void startAnimalServicesActivity(View view){
        Intent intent = new Intent(this, AnimalServicesActivity.class);
        startActivity(intent);
    }

    public void startMapsActivity(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
