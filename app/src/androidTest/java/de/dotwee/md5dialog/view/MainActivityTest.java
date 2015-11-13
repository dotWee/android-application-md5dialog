package de.dotwee.md5dialog.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.dotwee.md5dialog.R;
import de.dotwee.md5dialog.model.MainModelImpl;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.not;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String LOG_TAG = "MainActivityTest";

    /**
     * Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
     */
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * This method looks for an EditText with id = R.id.editText,
     * performs some inout and verifies the EditText's entered value.
     *
     * @throws Exception
     */
    @Test
    public void testEditTextContent() throws Exception {

        onView(withId(R.id.editText))
                .perform(typeText(LOG_TAG))
                .check(matches(withText(LOG_TAG)));
    }

    /**
     * This method performs a Md5 check and verifies if the hash gets
     * displayed right.
     *
     * @throws Exception
     */
    @Test
    public void testHashTextView() throws Exception {

        // perform text input
        onView(withId(R.id.editText)).perform(typeText(LOG_TAG));

        // click hash button
        onView(withText(R.string.button_name)).perform(click());

        String message = MainModelImpl.getInstance().getMd5Message(activityTestRule.getActivity(), LOG_TAG, MainModelImpl.getInstance().getMd5Hash(LOG_TAG));

        // verify textview value existence
        onView(withId(R.id.textViewHash))
                .check(matches(withText(message)));
    }

    /**
     * This method performs an empty input on the EditText and
     * clicks on the hash-button. Verifies if a Toast appears.
     *
     * @throws Exception
     */
    @Test
    public void testHashToast() throws Exception {

        // perform text input
        onView(withId(R.id.editText)).perform(typeText(LOG_TAG));

        // click hash button
        onView(withText(R.string.button_name)).perform(click());

        String message = MainModelImpl.getInstance().getMd5Message(activityTestRule.getActivity(), LOG_TAG, MainModelImpl.getInstance().getMd5Hash(LOG_TAG));

        // verify toast existence
        onView(withText(message))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    /**
     * This method verifies the cleanup-mechanism on the EditText.
     *
     * @throws Exception
     */
    @Test
    public void testEditTextCleanup() throws Exception {

        // perform text input
        onView(withId(R.id.editText))
                .perform(typeText(LOG_TAG));

        // click pin button
        onView(withText(R.string.button_name))
                .perform(click());

        // verify empty edittext
        onView(withId(R.id.editText))
                .check(matches(withText("")));
    }

    /**
     * This method verifies the md5-check mechanism using a pre-tested
     * string and its corresponding hash.
     *
     * @throws Exception
     */
    @Test
    public void testMd5Mechanism() throws Exception {

        String testString = activityTestRule.getActivity().getString(R.string.app_name);
        String testedStringHash = "0bbd25f190709954ca75cf3d67f8bd52";

        assertEquals(MainModelImpl.getInstance().getMd5Hash(testString), testedStringHash);
    }
}
