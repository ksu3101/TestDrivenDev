package kr.swkang.testdrivendev;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Kang-SungWoo
 * @since 2016-09-23
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestMainActivity {
  @Rule
  public ActivityTestRule<MainActivity> activityTestRule
      = new ActivityTestRule<MainActivity>(MainActivity.class);

  private IdlingResource idlingResource;

  @Before
  public void initTest() {
    idlingResource = activityTestRule.getActivity().getIdlingResource();
    Espresso.registerIdlingResources(idlingResource);
  }

  @Test
  public void testTextView() {
    onView(withId(R.id.main_textview))
        .perform(click())
        .check(matches(isDisplayed()));

    onView(withId(R.id.main_textview))
        .check(matches(withText("KANG")));

    onView(withId(R.id.main_edittext))
        .perform(
            click(),
            //pressKey(KeyEvent.KEYCODE_LANGUAGE_SWITCH),
            //pressKey(new EspressoKey.Builder().withKeyCode(KeyEvent.KEYCODE_SPACE).withShiftPressed(true).build()),
            typeText("rkdtjddn"),
            closeSoftKeyboard()
        )
        .check(matches(withText("강성우")));

  }

  @After
  public void unregisterIdlingResources() {
    if (idlingResource != null) {
      Espresso.unregisterIdlingResources(idlingResource);
    }
  }

}
