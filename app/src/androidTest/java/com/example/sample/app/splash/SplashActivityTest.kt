package com.example.sample.app.splash

import android.view.View
import android.view.ViewGroup
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.sample.app.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
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
            val flag = InstrumentationRegistry.getTargetContext().deleteDatabase("customised-button-sample-app-db")
            println("flag == $flag")
        }
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)


    @Test
    fun endToEndTesting() {
        Thread.sleep(1500)
        onView(withId(R.id.fullNameEt)).check(matches(isDisplayed()))
        onView(withId(R.id.usernameEt)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordEt)).check(matches(isDisplayed()))

        val linearLayout = onView(
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
        )
        linearLayout.check(matches(isDisplayed()))

        val linearLayout2 = onView(
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
        )
        linearLayout2.check(matches(isDisplayed()))

        val appCompatEditText = onView(
            allOf(
                withId(R.id.fullNameEt),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("name"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.usernameEt),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), replaceText("test"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.passwordEt),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatEditText3.perform(scrollTo(), replaceText("digital@1"), closeSoftKeyboard())

        val customizableGenericButton = onView(
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
        )
        customizableGenericButton.perform(scrollTo(), click())


        onView(withId(R.id.usernameEt)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordEt)).check(matches(isDisplayed()))

        val linearLayout3 = onView(
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
        )
        linearLayout3.check(matches(isDisplayed()))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.usernameEt),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText4.perform(scrollTo(), replaceText("test"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.passwordEt),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("digital@1"), closeSoftKeyboard())

        val customizableGenericButton2 = onView(
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
        )
        customizableGenericButton2.perform(scrollTo(), click())

        val linearLayout4 = onView(
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
        )
        linearLayout4.check(matches(isDisplayed()))

        val customizableGenericButton3 = onView(
            allOf(
                withId(R.id.logoutBtn),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    5
                )
            )
        )
        customizableGenericButton3.perform(scrollTo(), click())

        val linearLayout5 = onView(
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
        )
        linearLayout5.check(matches(isDisplayed()))

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
