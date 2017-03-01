package com.example.dadouglas.hchealth;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DaDouglas on 2/27/2017.
 */

public class UploadServiceRequest extends AsyncTask<JSONObject, Integer, Void> {
    @Override
    protected Void doInBackground(JSONObject... params) {
        try {
            URL url = new URL("http://appsqa.harriscountytx.gov/PublicHealthMobile/publichealth.svc/UploadServiceRequest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            connection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            connection.setDoOutput(true);
            connection.connect();

            // Get the JSON object that was sent with the execute request (ReportIssueActivity.java)
            JSONObject serviceRequest = params[0];

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(serviceRequest.toString());
            wr.flush();
            wr.close();

            // Log Response Status
            int code = connection.getResponseCode();
            String message = connection.getResponseMessage();
            Log.d(ReportIssueActivity.class.getSimpleName(), "########### passing" + code + "-" + message);

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
