package twitter.com.example.gevik.twitter;

import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import twitter.com.example.gevik.twitter.SearchScreen.SearchActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by gevik on 4/11/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BasicUiTests {

    @Rule
    public ActivityTestRule<SearchActivity> mActivityRule = new ActivityTestRule<>(
            SearchActivity.class);

    @Test
    public void emptySearch() {
        //empty user name
        onView(withId(R.id.search)).perform(click()); //press submit
        SystemClock.sleep(2000);
    }

    @Test
    public void emptyUserNameTest() {
        onView(withId(R.id.search_text))
                .perform(typeText("BMW"), closeSoftKeyboard());
        onView(withId(R.id.search)).perform(click());
        SystemClock.sleep(2000);
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SystemClock.sleep(4000);
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SystemClock.sleep(4000);
    }


}
