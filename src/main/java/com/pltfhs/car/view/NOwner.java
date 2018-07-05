package com.pltfhs.car.view;

import com.pltfhs.car.entity.Users;

public class NOwner {

    private String id;
    private String firstName;
    private String lastName;
    private String profilePic;
    private String email;
    private String mobile;

    public NOwner() {
    }

    public NOwner(Users users) {
        this.id = users.getUserId().toString();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.profilePic = users.getProfilePic();
        this.email = users.getEmail();
        this.mobile = users.getMobile();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
