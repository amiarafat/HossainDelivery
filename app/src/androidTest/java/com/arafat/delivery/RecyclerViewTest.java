package com.arafat.delivery;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.action.ViewActions.click;


public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recycleTest(){

        Espresso.onView(ViewMatchers.withId(R.id.rvDeliveries))
                .perform(RecyclerViewActions.scrollToPosition(19));

        Espresso.onView(ViewMatchers.withId(R.id.rvDeliveries))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
    }
}