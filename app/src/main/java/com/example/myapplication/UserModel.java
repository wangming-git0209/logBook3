package com.example.myapplication;

public class UserModel {
    private int id;
    private String name;
    private String email;
    private String dateOfBirth;
    private String imageSelected;

    public UserModel() {}
    public UserModel(int id, String name, String email, String dateOfBirth, String imageSelected) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.imageSelected = imageSelected;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSelectedImageTag() {
        return imageSelected;
    }
}
