package com.example.dadouglas.hchealth;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DaDouglas on 2/27/2017.
 */


public class GetIssueSubjects extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String link = "http://appsqa.harriscountytx.gov/PublicHealthMobile/publichealth.svc/ReportIssueSubject";
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String webPage = "", data = "";

            while ((data = reader.readLine()) != null) {
                webPage += data + "\n";
            }

            try {
                JSONObject jObject = new JSONObject(webPage);
                JSONArray jsonArray = jObject.getJSONArray("GetReportIssueSubjectResult");
                Log.d(ReportIssueActivity.class.getSimpleName(), jsonArray.get(0) + "taggggggggggggggg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(ReportIssueActivity.class.getSimpleName(), webPage);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}