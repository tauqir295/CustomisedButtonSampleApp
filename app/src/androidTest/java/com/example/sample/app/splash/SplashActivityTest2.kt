package com.example.sample.app.splash


import android.view.View
import android.view.ViewGroup
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest2 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun splashActivityTest2() {
        val editText = onView(
            allOf(
                withId(R.id.inputFullName), withText("Full Name"),
                withParent(withParent(withId(R.id.fullNameTil))),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Full Name")))

        val editText2 = onView(
            allOf(
                withId(R.id.inputUserName), withText("Username"),
                withParent(withParent(withId(R.id.userNameTil))),
                isDisplayed()
            )
        )
        editText2.check(matches(isDisplayed()))

        val editText3 = onView(
            allOf(
                withId(R.id.inputPassword), withText("Password"),
                withParent(withParent(withId(R.id.passwordTil))),
                isDisplayed()
            )
        )
        editText3.check(matches(isDisplayed()))

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

        val textView = onView(
            allOf(
                withId(R.id.customizableButtonTitleTv), withText("Sign Up"),
                withParent(withParent(withId(R.id.customGenericButtonContainer))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Sign Up")))

        val textView2 = onView(
            allOf(
                withId(R.id.customizableButtonTitleTv), withText("Sign Up"),
                withParent(withParent(withId(R.id.customGenericButtonContainer))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Sign Up")))

        val appCompatEditText = onView(
            allOf(
                withId(R.id.inputFullName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fullNameTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("Joe Dan"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.inputUserName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.userNameTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), replaceText("joe"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.inputPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText3.perform(scrollTo(), replaceText("Android@2021"), closeSoftKeyboard())

        val checkableImageButton = onView(
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
        )
        checkableImageButton.perform(click())

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

        val editText4 = onView(
            allOf(
                withId(R.id.inputUserName), withText("Username"),
                withParent(withParent(withId(R.id.userNameTil))),
                isDisplayed()
            )
        )
        editText4.check(matches(isDisplayed()))

        val editText5 = onView(
            allOf(
                withId(R.id.inputPassword), withText("Password"),
                withParent(withParent(withId(R.id.passwordTil))),
                isDisplayed()
            )
        )
        editText5.check(matches(isDisplayed()))

        val linearLayout2 = onView(
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
        linearLayout2.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.customizableButtonTitleTv), withText("Login"),
                withParent(withParent(withId(R.id.customGenericButtonContainer))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Login")))

        val textView4 = onView(
            allOf(
                withId(R.id.customizableButtonTitleTv), withText("Login"),
                withParent(withParent(withId(R.id.customGenericButtonContainer))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Login")))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.inputUserName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.userNameTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText4.perform(scrollTo(), replaceText("joe"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.inputPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("Android@2021"), closeSoftKeyboard())

        val checkableImageButton2 = onView(
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
        )
        checkableImageButton2.perform(click())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.inputPassword), withText("Android@2021"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText6.perform(scrollTo(), click())

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

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.inputUserName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.userNameTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText7.perform(scrollTo(), replaceText("joe"), closeSoftKeyboard())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.inputPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText8.perform(scrollTo(), replaceText("Android"), closeSoftKeyboard())

        val checkableImageButton3 = onView(
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
        )
        checkableImageButton3.perform(click())

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.inputPassword), withText("Android"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText9.perform(scrollTo(), replaceText("Android@2021"))

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.inputPassword), withText("Android@2021"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText10.perform(closeSoftKeyboard())

        val customizableGenericButton3 = onView(
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
        customizableGenericButton3.perform(scrollTo(), click())


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

        onView(
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
        ).perform(scrollTo(), click())
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
