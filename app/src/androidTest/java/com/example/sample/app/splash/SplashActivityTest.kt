package com.example.sample.app.splash

import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.sample.app.BaseApplication
import com.example.sample.app.R
import com.example.sample.app.utils.DB_NAME
import com.example.sample.app.utils.SHARED_PREF_NAME
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUp() {
            ApplicationProvider.getApplicationContext<BaseApplication>().run {

                deleteDatabase(DB_NAME)

                deleteSharedPreferences(SHARED_PREF_NAME)
            }
        }
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun endToEndTesting() {
        Thread.sleep(1500)

        onView(withId(R.id.fullNameTil)).check(matches(isDisplayed()))
        onView(withId(R.id.userNameTil)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordTil)).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.customGenericButtonContainer),
                withParent(
                    allOf(
                        withId(R.id.signUpButton),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.customizableButtonTitleTv), withText("Sign Up"),
                withParent(withParent(withId(R.id.customGenericButtonContainer))),
                isDisplayed()
            )
        ).check(matches(withText("Sign Up")))

        onView(withId(R.id.inputFullName))
            .perform(scrollTo(), replaceText("Joe Dan"), closeSoftKeyboard())

        onView(withId(R.id.inputUserName))
            .perform(scrollTo(), replaceText("joe"), closeSoftKeyboard())

        onView(withId(R.id.inputPassword))
            .perform(scrollTo(), replaceText("Android@2021"), closeSoftKeyboard())

        onView(
            allOf(
                withId(R.id.text_input_end_icon), withContentDescription("Show password"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        ).perform(click())

        onView(
            allOf(
                withId(R.id.signUpButton),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        ).perform(scrollTo(), click())

        onView(withId(R.id.userNameTil)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordTil)).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.customGenericButtonContainer),
                withParent(
                    allOf(
                        withId(R.id.loginButton),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.customizableButtonTitleTv), withText("Login"),
                withParent(withParent(withId(R.id.customGenericButtonContainer))),
                isDisplayed()
            )
        ).check(matches(withText("Login")))

        onView(withId(R.id.inputUserName))
            .perform(scrollTo(), replaceText("joe"), closeSoftKeyboard())

        onView(withId(R.id.inputPassword))
            .perform(scrollTo(), replaceText("Android@2021"), closeSoftKeyboard())

        onView(
            allOf(
                withId(R.id.text_input_end_icon), withContentDescription("Show password"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        ).perform(click())

        onView(
            allOf(
                withId(R.id.loginButton),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        ).perform(scrollTo(), click())

        onView(
            allOf(
                withId(R.id.customGenericButtonContainer),
                withParent(
                    allOf(
                        withId(R.id.logoutBtn),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))


        onView(withId(R.id.logoutBtn)).perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
