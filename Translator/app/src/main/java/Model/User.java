package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private ArrayList<String> languages;
    private String charge;
    private String profile;
    private String city;
    private String state;
    private boolean verify;
    private String email;
    private String phoneNumber;
    private String rating;
    private String userType;
    //private boolean typeTranslator;
    //private boolean typeClient;

    public User() {
    }


    public User(String userId, String firstName, String lastName, ArrayList<String> languages, String charge, String profile, String city, String state, boolean verify, String email, String phoneNumber, String rating, String userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languages = languages;
        this.charge = charge;
        this.profile = profile;
        this.city = city;
        this.state = state;
        this.verify = verify;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.userType = userType;
        //this.typeTranslator = typeTranslator;
        //this.typeClient = typeClient;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUserType() { return userType; }

    public void setUserType(String userType) { this.userType = userType; }

    /*public boolean getTypeTranslator() { return typeTranslator; }

    public void setTypeTranslator(boolean typeTranslator) { this.typeTranslator = typeTranslator; }

    public boolean getTypeClient() { return typeClient; }

    public void setTypeClient(boolean typeClient) { this.typeClient = typeClient; }*/


}