package com.teamtreehouse.testingbase;

public interface MainActivityView {
    void changeTextViewText(String text);
    void changeBackgroundColor(int color);
    void launchOtherActivity(Class activity);
}
