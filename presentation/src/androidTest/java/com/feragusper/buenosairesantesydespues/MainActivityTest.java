package com.feragusper.buenosairesantesydespues;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.feragusper.buenosairesantesydespues.view.activity.HistoricalRecordListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(HistoricalRecordListActivity.class);

    @Test
    public void testToolbarDesign() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));

        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))));
    }
}