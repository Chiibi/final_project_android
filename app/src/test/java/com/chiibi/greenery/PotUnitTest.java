package com.chiibi.greenery;

import android.net.Uri;

import com.chiibi.greenery.database.entity.PotCard;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PotUnitTest {

    @Test
    public void addNewPotTest(){
        Uri uri= null;
        String uid = "118255656220";
        String potName = "Catus";
        String note = "Plant near the sun";
        int task = 1;
        int period = 1;

        PotCard potCard = new PotCard(uid, uri, potName, note, task, period);

        assertEquals(potCard.getUid(), uid);
        assertEquals(potCard.getImgUri(), uri);
        assertEquals(potCard.getPotName(), potName);
        assertEquals(potCard.getNote(), note);
        assertEquals(potCard.getTask(), task);
        assertEquals(potCard.getPeriod(), period);
    }
}
