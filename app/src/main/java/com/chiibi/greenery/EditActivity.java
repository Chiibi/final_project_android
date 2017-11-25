package com.chiibi.greenery;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chiibi.greenery.database.GreeneryDB;
import com.chiibi.greenery.database.entity.PotCard;
import com.chiibi.greenery.model.UserProfile;
import com.chiibi.greenery.task.AddPotCardTask;
import com.chiibi.greenery.task.DeletePotTask;
import com.chiibi.greenery.task.UpdatePotCardTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imageButton) ImageButton imageButton;
    @BindView(R.id.edt_potName) MaterialEditText edtPotName;
    @BindView(R.id.edt_note) MaterialEditText edtNote;
    @BindView(R.id.btn_del) Button btnDelete;
    @BindView(R.id.btn_save) Button btnSave;

    private static final String DB_NAME = "Greeney";

    private GreeneryDB greeneryDB;
    private PotCard mPotcard;
    private UserProfile userProfile;
    private Uri imgPotTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);
        setupView();
        buildDB();
        setupData();
    }

    private void setupView(){
        setSupportActionBar(toolbar);
    }

    private void setupData(){
        userProfile = new UserProfile();
        mPotcard = getIntent().getParcelableExtra(PotCard.class.getSimpleName());
        if(mPotcard != null){
            setTitle(getString(R.string.title_editPot));
            edtPotName.setText(mPotcard.getPotName());
            edtNote.setText(mPotcard.getNote());
            imageButton.setImageURI(mPotcard.getImgUri());
            btnSave.setText(getString(R.string.button_save));
            btnDelete.setEnabled(true);
        } else {
            setTitle(getString(R.string.title_addPot));
            btnSave.setText(getString(R.string.button_add));
            btnDelete.setEnabled(false);
        }
    }

    private void buildDB(){
        greeneryDB = Room.databaseBuilder(this, GreeneryDB.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @OnClick(R.id.imageButton) void selectImg(){
        FishBun.with(EditActivity.this).MultiPageMode()
                .setActionBarColor(Color.parseColor("#7f613c"), Color.parseColor("#5e472a"), false)
                .setMaxCount(1)
                .setCamera(true)
                .startAlbum();
    }

    @OnClick(R.id.btn_del) void deletePot(){
        new DeletePotTask(greeneryDB, new DeletePotTask.OnDeleteSuccessListener() {
            @Override
            public void onDeleteSuccess() {
                finish();
            }
        }).execute(mPotcard);
        redirectTo(MainActivity.class);
    }

    @OnClick(R.id.btn_save) void submit(){
        Uri imgPot;
        String potName;
        String potNote;
        String uid;

        potName = edtPotName.getText().toString();
        potNote = edtNote.getText().toString();
        uid = userProfile.getProfileID();
        imgPot = imgPotTmp;

        PotCard potCard = new PotCard(uid, imgPot, potName, potNote);

        if(mPotcard != null){
            mPotcard.updateInfo(potCard);
            new UpdatePotCardTask(greeneryDB, new UpdatePotCardTask.OnUpdateSuccessListener() {
                @Override
                public void onUpdateSuccess() {
                    finish();
                }
            }).execute(mPotcard);
        } else {
            new AddPotCardTask(greeneryDB, new AddPotCardTask.OnAddSuccessListener() {
                @Override
                public void onAddSuccess() {
                    finish();
                }
            }).execute(potCard);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    ArrayList<Uri> path = data.getParcelableArrayListExtra(Define.INTENT_PATH);
                    imgPotTmp = path.get(0);
                    if(path != null){
                        imageButton.setImageURI(path.get(0));
                        break;
                    }
                }
        }
    }

    private void redirectTo(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
