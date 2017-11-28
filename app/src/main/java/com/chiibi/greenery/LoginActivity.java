package com.chiibi.greenery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity{

    private static final String TAG = "LoginActivity";

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @BindView(R.id.rootview_login) View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        initialFacebookLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            redirectTo(MainActivity.class);
        }
    }

    public void initialFacebookLogin(){
        mCallbackManager = CallbackManager.Factory.create();

        final LoginButton loginButton = findViewById(R.id.facebook_login_button) ;
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                mAuth = FirebaseAuth.getInstance();
                AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
                loginButton.setClickable(false);
                handleFacebookAccessToken(credential);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                Toast.makeText(LoginActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
                // ...
            }
        });
    }

    private void handleFacebookAccessToken(AuthCredential credential) {
        Log.d(TAG, "handleFacebookAccessToken:" + credential);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, user.getEmail());
                            redirectTo(MainActivity.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void redirectTo(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
