package com.teamtreehouse.testingbase;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.inputmethod.EditorInfo;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTestRobo {

    MainActivityView view;

    MainActivityPresenter presenter;


    MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
    }

    @Test
    public void edidtTextUpdatesTexview() throws Exception {
        // Arrege

        String givenString = "test123";
        activity.editText.setText(givenString);

        //Act
        activity.editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        // Asert
        String actualString = activity.textView.getText().toString();
        assertEquals(givenString, actualString);
    }

    @Test
    public void spinnerUpdateBackgroundColor() throws Exception {
        // Arrege
        int index = 2;
        int givenColor = Color.GREEN;

        //Act
        activity.colorSpinner.setSelection(index);
        // Asert
        int expendCOlor = ((ColorDrawable) activity.linearLayout.getBackground()).getColor();
        assertEquals(givenColor, expendCOlor);
    }

    @Test
    public void bottonclickActivity() throws Exception {
        // Arrange
        Class clazz = OtherActivity.class;
        Intent expectedIntent = new Intent(activity,clazz);
        // Act
        activity.launchActivityButton.callOnClick();
        // Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
