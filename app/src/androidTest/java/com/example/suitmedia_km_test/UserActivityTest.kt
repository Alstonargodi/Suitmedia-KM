package com.example.suitmedia_km_test

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.example.suitmedia_km_test.presentation.first.MainActivity
import com.example.suitmedia_km_test.presentation.second.SecondScreenActivity
import com.example.suitmedia_km_test.presentation.third.ThirdScreenActivity
import com.example.suitmedia_km_test.presentation.third.adapter.UsersRecyclerViewAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class UserActivityTest {

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun userActivity(){
        assert(activityChecker()?.javaClass == MainActivity::class.java)
        onView(withId(R.id.etfirst_insert_word))
            .perform(typeText("suitmedia"))
        onView(ViewMatchers.isRoot()).perform(closeSoftKeyboard())

        onView(withId(R.id.btnfirst_check_palindrome))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(click())

        onView(withText("isPalindrome"))
            .inRoot(isDialog())
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withText("false"))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withText("yes"))
            .perform(click())

        onView(withId(R.id.etfirst_insert_word))
            .perform(replaceText("kasur rusak"))
        onView(ViewMatchers.isRoot()).perform(closeSoftKeyboard())

        onView(withId(R.id.btnfirst_check_palindrome))
            .perform(click())

        onView(withText("isPalindrome"))
            .inRoot(isDialog())
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withText("true"))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withText("yes"))
            .perform(click())

        onView(withId(R.id.etfirst_insert_name))
            .perform(typeText("nametest"))
        onView(ViewMatchers.isRoot()).perform(closeSoftKeyboard())

        onView(withId(R.id.btnfirst_check_next))
            .perform(click())

        assert(activityChecker()?.javaClass == SecondScreenActivity::class.java)

        onView(withText("nametest"))
            .check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.btn_choose_user))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(click())

        assert(activityChecker()?.javaClass == ThirdScreenActivity::class.java)

        onView(withId(R.id.recyclerView_third))
            .check(matches(ViewMatchers.isDisplayed()))

        Thread.sleep(5000)

        onView(withId(R.id.recyclerView_third))
            .perform(RecyclerViewActions.actionOnItemAtPosition<UsersRecyclerViewAdapter.ViewHolder>(
                1, click()
            ))

        assert(activityChecker()?.javaClass == SecondScreenActivity::class.java)
    }

    private fun activityChecker(): Activity?{
        var currentActivity : Activity? = null
        getInstrumentation().runOnMainSync {
            kotlin.run {
                currentActivity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAt(0)
            }
        }
        return currentActivity
    }
}