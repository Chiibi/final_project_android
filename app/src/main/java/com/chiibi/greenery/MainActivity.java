package com.chiibi.greenery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chiibi.greenery.adapters.PotCardAdapter;
import com.chiibi.greenery.database.entity.PotCard;
import com.chiibi.greenery.model.UserProfile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.rcv_potCard) RecyclerView rvPotCard;

    private TextView displayName;
    private TextView displayEmail;
    private ProfilePictureView displayImg;
    private PotCardAdapter potCardAdapter;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        ButterKnife.bind(this);

        initialMainActivityView();
        setupView();

        UserProfile userProfile = new UserProfile();
        displayName.setText(userProfile.getDisplayName());
        displayEmail.setText(userProfile.getEmail());
        displayImg.setProfileId(userProfile.getProfileID());

    }

    private void setupView() {
        rvPotCard.setLayoutManager(new LinearLayoutManager(this));
        potCardAdapter = new PotCardAdapter(this);
        rvPotCard.setAdapter(potCardAdapter);
    }

    public void initialMainActivityView(){
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditPot(null, EditActivity.class);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.displayName = navigationView.getHeaderView(0).findViewById(R.id.tViewName);
        this.displayEmail = navigationView.getHeaderView(0).findViewById(R.id.tViewEmail);
        this.displayImg = navigationView.getHeaderView(0).findViewById(R.id.profilePicture);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        }else if (id == R.id.nav_logout) {
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            redirectTo(LoginActivity.class);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void redirectTo(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }

    private void EditPot(@Nullable PotCard potCard, Class activity){
        Intent intent = new Intent(MainActivity.this, activity);
//        intent.putExtra(PotCard.class.getSimpleName(), potCard);
        startActivity(intent);
    }
}
