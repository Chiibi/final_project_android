package com.chiibi.greenery;


import com.facebook.Profile;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FackbookLoginUnitTest {
    @Test //when login success with my acc
    public void getFacebookUserProfileTest(){
        String Fname = "Chayapol";
        String Lname = "Chaimongkolnimit";
        String ProfileID = "1518207804913041";
        String DisplayName = "Chayapol  Chaimongkolnimit";

        Profile facebookProfile = Profile.getCurrentProfile();
        assertEquals(facebookProfile.getFirstName(), Fname);
        assertEquals(facebookProfile.getLastName(), Lname);
        assertEquals(facebookProfile.getId(), ProfileID.toString());
        assertEquals(facebookProfile.getFirstName()+"  "+facebookProfile.getLastName(), DisplayName);
    }

    @Test //because cant get email from facebook profile obj.
    public void getOAuhtEmailFromFirebaseTest(){
        String email = "jamezadoubleshot@gmail.com";
        assertEquals(FirebaseAuth.getInstance().getCurrentUser().getEmail(), email);
    }
}
