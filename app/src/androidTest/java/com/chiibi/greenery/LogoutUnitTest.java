package com.chiibi.greenery;


import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

import static junit.framework.Assert.assertNull;

public class LogoutUnitTest {

    @Test//test logout after login is success
    public void logoutFacebookTokenTest(){
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        assertNull(FirebaseAuth.getInstance().getCurrentUser());
    }
}
