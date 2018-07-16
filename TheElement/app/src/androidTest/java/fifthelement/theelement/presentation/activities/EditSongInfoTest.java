package fifthelement.theelement.presentation.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fifthelement.theelement.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditSongInfoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editSongInfoTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nvView),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(first(allOf(withId(R.id.popup_button),
                        isDisplayed()))));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Song Information"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.song_info_edit_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.flContent),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_song_info_name), withText("Adventure of a Lifetime"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("Adventure"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_song_info_name), withText("Adventure"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_song_info_artist), withText("Coldplay"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                3)));
        appCompatEditText3.perform(scrollTo(), replaceText("Cold"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edit_song_info_artist), withText("Cold"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.edit_song_info_album), withText("A Head Full of Dreams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                5)));
        appCompatEditText5.perform(scrollTo(), replaceText("A"));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.edit_song_info_album), withText("A"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText6.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.edit_song_info_genre), withText("Pop"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                7)));
        appCompatEditText7.perform(scrollTo(), replaceText(""));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.edit_song_info_genre),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.song_info_editDone_btn), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.song_info_editing),
                                        0),
                                8)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.song_info_name), withText("Adventure"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("Adventure")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.song_info_artist), withText("Cold"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                4),
                        isDisplayed()));
        textView2.check(matches(withText("Cold")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.song_info_album), withText("A"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView3.check(matches(withText("A")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.song_info_genre),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                8),
                        isDisplayed()));
        textView4.check(matches(withText("")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
    private <T> Matcher<T> first(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }

                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }
}