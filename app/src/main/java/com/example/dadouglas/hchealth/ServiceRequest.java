package com.example.dadouglas.hchealth;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DaDouglas on 3/1/2017.
 */

public class ServiceRequest {
    private String contactNumber;
    private String description;
    private String email;
    private String firstName;
    private String lastName;
    private String imageBytes;
    private String place;
    private String receivedDevice;
    private String subject;
    private String fileName;

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageBytes() {
        return imageBytes;
    }

    public String getPlace() {
        return place;
    }

    public String getreceivedDevice() {
        return receivedDevice;
    }

    public String getSubject() {
        return subject;
    }

    public String getFileName() {
        return fileName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImageBytes(String imageBytes) {
        this.imageBytes = imageBytes;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setreceivedDevice(String receivedDevice) {
        this.receivedDevice = receivedDevice;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ServiceRequest(JSONObject object) {
        try {
            this.contactNumber = object.getString("ContactNumber");
            this.description = object.getString("Description");
            this.email = object.getString("Email");
            this.firstName = object.getString("FirstName");
            this.lastName = object.getString("LastName");
            this.imageBytes = object.getString("ImageBytes");
            this.place = object.getString("Place");
            this.receivedDevice = object.getString("receivedDevice");
            this.subject = object.getString("Subject");
            this.fileName = object.getString("fileName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ServiceRequest(String contactNumber, String description, String email, String firstName, String lastName, String imageBytes, String place, String receivedDevice, String subject, String fileName) {
        this.contactNumber = contactNumber;
        this.description = description;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageBytes = imageBytes;
        this.place = place;
        this.receivedDevice = receivedDevice;
        this.subject = subject;
        this.fileName = fileName;
    }


    @Override
    public String toString() {
        return "ServiceRequest{" +
                "contactNumber='" + contactNumber + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", imageBytes='" + imageBytes + '\'' +
                ", place='" + place + '\'' +
                ", receivedDevice='" + receivedDevice + '\'' +
                ", subject='" + subject + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    // Return ServiceRequest object as JSON object
    public JSONObject toJsonObject() {
        JSONObject serviceRequestJsonObject = new JSONObject();
        try {
            serviceRequestJsonObject.put("ContactNumber", this.getContactNumber());
            serviceRequestJsonObject.put("Description", this.getDescription());
            serviceRequestJsonObject.put("Email", this.getEmail());
            serviceRequestJsonObject.put("FirstName", this.getFirstName());
            serviceRequestJsonObject.put("ImageBytes", this.getImageBytes());
            serviceRequestJsonObject.put("LastName", this.getLastName());
            serviceRequestJsonObject.put("Place", this.getPlace());
            serviceRequestJsonObject.put("ReceivedDevice", this.getreceivedDevice());
            serviceRequestJsonObject.put("Subject", this.getSubject());
            serviceRequestJsonObject.put("fileName", this.getFileName());

            return serviceRequestJsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
