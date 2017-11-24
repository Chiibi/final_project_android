package com.chiibi.greenery.model;

import com.facebook.Profile;
import com.google.firebase.auth.FirebaseAuth;

public class UserProfile {

    private String displayName;
    private String firstName;
    private String lastName;
    private String email;

    private String profileID;

    public UserProfile() {
        Profile profile = Profile.getCurrentProfile();
        if(profile != null){
            this.firstName   = profile.getFirstName();
            this.lastName    = profile.getLastName();
            this.displayName = this.firstName + " " + this.lastName;
            this.email       = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            this.profileID   = profile.getId();
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileID() {
        return profileID;
    }
}
