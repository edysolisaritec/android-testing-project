package com.teamtreehouse.testingbase;

import android.graphics.Color;
import android.view.inputmethod.EditorInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    MainActivityView view;

    MainActivityPresenter presenter;

    @Before
    public void setUp()
    {
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void edidtTextUpdatesTexview() throws Exception
    {
        // Arrege

        String givenString = "test123";
        //Act
        presenter.editTextUpdated(givenString);
        // Asert

        Mockito.verify(view).changeTextViewText(givenString);

    //    assertEquals(givenString,actualString);
    }

    @Test
    public void  colorSelecd() throws  Exception
    {
        // Arrange
        int index = 2;
        int givenColor = Color.GREEN;

        // Act
        presenter.colorSelected(index);

        // Assert
        Mockito.verify(view).changeBackgroundColor(givenColor);
    }

    @Test
    public void  bottonclickActivity() throws  Exception
    {
        // Arrange
        Class clazz = OtherActivity.class;
        // Act
        presenter.buttomClicked(clazz);
        // Assert
        Mockito.verify(view).launchOtherActivity(clazz);
    }
}
