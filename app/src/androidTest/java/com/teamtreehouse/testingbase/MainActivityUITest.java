package com.teamtreehouse.testingbase;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editTextUpdatesTextView() throws Exception {
        // Arrange
        String givenString = "test123";
        onView(withId(R.id.editText)).perform(typeText(givenString));
        // Act
        onView(withId(R.id.editText)).perform(pressImeActionButton());

        // Assert
        onView(withId(R.id.textView)).check(matches(withText(givenString)));
    }

    @Test
    public void spinnerUpdatesBackgroundColor() throws Exception {
        // Arrange


        final int givenColor = Color.GREEN;
        String spinnerItemText = "Green";
        // Act
        onView(withId(R.id.colorSpinner)).perform(click());
       // onView(withText(spinnerItemText)).perform(click()); // solo buscaria en los elemntos de la vista el arreglo tendria 100 pero en vista 5
        onData(allOf(is(instanceOf(String.class)),is(spinnerItemText))).perform(click());

        // Assert
        BoundedMatcher backgroundColorMatches = new BoundedMatcher<View,LinearLayout>(LinearLayout.class) {
            @Override
            protected boolean matchesSafely(LinearLayout linearLayout) {

                int actualColor = ((ColorDrawable) linearLayout.getBackground()).getColor();
                return givenColor == actualColor;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("background color should  equal: "+ givenColor);

            }
        };
        onView(withId(R.id.linearLayout)).check(matches(backgroundColorMatches));
    }

    @Test
    public void buttonLaunchesOtherActivity() throws Exception {
        // Arrange
        String otherActivityString = "Other Activity";


        // Act
        onView(withId(R.id.launchActivityButton)).perform(click());

        // Assert
        onView(withText(otherActivityString)).check(matches(notNullValue()));

    }
}
