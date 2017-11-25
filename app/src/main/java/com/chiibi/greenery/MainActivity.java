package com.chiibi.greenery;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chiibi.greenery.adapters.PotCardAdapter;
import com.chiibi.greenery.adapters.RecyclerItemOnClickAdapter;
import com.chiibi.greenery.database.GreeneryDB;
import com.chiibi.greenery.database.entity.PotCard;
import com.chiibi.greenery.model.UserProfile;
import com.chiibi.greenery.task.FetchPotCardTask;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.rcv_potCard) RecyclerView rvPotCard;

    private static final String DB_NAME = "Greeney";

    private TextView displayName;
    private TextView displayEmail;
    private ProfilePictureView displayImg;
    private PotCardAdapter potCardAdapter;
    private GreeneryDB greeneryDB;
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        ButterKnife.bind(this);
        setupView();
        buildDb();
        setupData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPotCard();
    }

    private void setupView() {
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

    private void buildDb() {
        greeneryDB = Room.databaseBuilder(this, GreeneryDB.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private void setupData() {
        userProfile = new UserProfile();
        displayImg.setProfileId(userProfile.getProfileID());
        displayName.setText(userProfile.getDisplayName());
        displayEmail.setText(userProfile.getEmail());

        potCardAdapter = new PotCardAdapter(this);
        rvPotCard.setLayoutManager(new LinearLayoutManager(this));
        rvPotCard.setAdapter(potCardAdapter);
        rvPotCard.addOnItemTouchListener(new RecyclerItemOnClickAdapter(this, rvPotCard, new RecyclerItemOnClickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        EditPot(potCardAdapter.getPotCardList().get(position), EditActivity.class);
                    }
                })
        );
    }

    private void loadPotCard(){
        new FetchPotCardTask(userProfile.getProfileID(), greeneryDB, new FetchPotCardTask.OnFetchSuccessListener() {
            @Override
            public void onFetchSuccess(List<PotCard> potCardList) {
                refreshData(potCardList);
            }

            private void refreshData(List<PotCard> potCardList) {
                if(potCardList.size() == 0){

                } else {
                    potCardAdapter.setPotCardList(potCardList);
                    potCardAdapter.notifyDataSetChanged();
                }
            }
        }).execute();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addpot) {
            EditPot(null, EditActivity.class);
        } else if (id == R.id.nav_logout) {
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
        intent.putExtra(PotCard.class.getSimpleName(), potCard);
        startActivity(intent);
    }
}
