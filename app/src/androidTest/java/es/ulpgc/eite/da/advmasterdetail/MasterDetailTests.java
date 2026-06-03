package es.ulpgc.eite.da.advmasterdetail;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.content.pm.ActivityInfo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.advmasterdetail.login.LoginActivity;

@SuppressWarnings("ALL")
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MasterDetailTests {

    @Rule
    public ActivityTestRule<LoginActivity> testRule =
            new ActivityTestRule<>(LoginActivity.class);

    private void waitForData() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01GuestCanOpenSatellitesAndDetail() {

        onView(withId(R.id.invitadoText)).perform(click());

        waitForData();

        onView(withId(R.id.communicationsButton)).perform(click());

        waitForData();

        onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(0, R.id.product_name)).check(matches(withText("Starlink")));

        ViewInteraction starlink = onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(0, R.id.product_name));

        starlink.perform(click());

        waitForData();

        onView(withId(R.id.detailName))
                .check(matches(withText("Starlink")));

        onView(withId(R.id.detailAgency)).check(matches(withText("🏢 Agencia: SpaceX")));

        pressBack();

        waitForData();

        onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(1, R.id.product_name)).check(matches(withText("METEOSAT-11")));
    }

    @Test
    public void test02GuestCanOpenMissionsAndDetail() {

        onView(withId(R.id.invitadoText)).perform(click());

        waitForData();

        onView(withId(R.id.missionsButton)).perform(click());

        waitForData();

        onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(0, R.id.product_name)).check(matches(withText("Apollo 11")));

        ViewInteraction apollo =
                onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(0, R.id.product_name));

        apollo.perform(click());

        waitForData();

        onView(withId(R.id.detailName)).check(matches(withText("Apollo 11")));

        pressBack();

        waitForData();

        onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(1, R.id.product_name)).check(matches(withText("Artemis I")));
    }

    @Test
    public void test03GuestCannotAccessFavorites() {

        onView(withId(R.id.invitadoText)).perform(click());

        waitForData();

        onView(withId(R.id.favoritesButton)).perform(click());

        waitForData();

        onView(withId(R.id.homeTitle))
                .check(matches(withText("Descubre los\nsatélites que orbitan\nnuestro planeta")));
    }
    @Test
    public void test04RegisterWithDifferentPasswords() {

        onView(withId(R.id.registerText)).perform(click());

        waitForData();

        onView(withId(R.id.registerUsernameInput))
                .perform(replaceText("usuarioTest"), closeSoftKeyboard());

        onView(withId(R.id.registerPasswordInput))
                .perform(replaceText("1234"), closeSoftKeyboard());

        onView(withId(R.id.registerConfirmPasswordInput))
                .perform(replaceText("9999"), closeSoftKeyboard());

        onView(withId(R.id.registerButton)).perform(click());

        waitForData();

        onView(withId(R.id.registerTitle))
                .check(matches(withText("Crear\nCuenta")));
    }

    @Test
    public void test05RegisterCorrectly() {

        onView(withId(R.id.registerText)).perform(click());

        waitForData();

        onView(withId(R.id.registerUsernameInput))
                .perform(replaceText("david123"), closeSoftKeyboard());

        onView(withId(R.id.registerPasswordInput))
                .perform(replaceText("1234"), closeSoftKeyboard());

        onView(withId(R.id.registerConfirmPasswordInput))
                .perform(replaceText("1234"), closeSoftKeyboard());

        onView(withId(R.id.registerButton)).perform(click());

        waitForData();

        onView(withId(R.id.loginButton))
                .check(matches(withText("Iniciar Sesión")));
    }

    @Test
    public void test06LoginIncorrect() {

        onView(withId(R.id.usernameInput))
                .perform(replaceText("usuarioFake"), closeSoftKeyboard());

        onView(withId(R.id.passwordInput))
                .perform(replaceText("mal"), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        waitForData();

        onView(withId(R.id.loginTitle))
                .check(matches(withText("Bienvenido a\nSatelApp")));
    }

    @Test
    public void test07LoginCorrect() {

        onView(withId(R.id.usernameInput))
                .perform(replaceText("david123"), closeSoftKeyboard());

        onView(withId(R.id.passwordInput))
                .perform(replaceText("1234"), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        waitForData();

        onView(withId(R.id.homeTitle))
                .check(matches(withText("Descubre los\nsatélites que orbitan\nnuestro planeta")));
    }

    @Test
    public void test08LoggedUserCanAddFavorite() {

        onView(withId(R.id.usernameInput)).perform(replaceText("david123"), closeSoftKeyboard());

        onView(withId(R.id.passwordInput)).perform(replaceText("1234"), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        waitForData();

        onView(withId(R.id.communicationsButton)).perform(click());

        waitForData();

        onView(new RecyclerViewMatcher(R.id.product_recycler).atPositionOnView(0, R.id.product_name)).perform(click());

        waitForData();

        onView(withId(R.id.favoriteButton)).perform(click());

        waitForData();

        onView(withId(R.id.favoriteButton)).check(matches(withText("Quitar de favoritos")));
    }
    private void rotateScreen() {

        if(testRule.getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){

            testRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        } else {

            testRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        waitForData();
    }
    @Test
    public void test09RotationKeepsLoginData() {

        onView(withId(R.id.usernameInput)).perform(replaceText("Daniela"), closeSoftKeyboard());

        onView(withId(R.id.passwordInput)).perform(replaceText("1234"), closeSoftKeyboard());

        rotateScreen();

        onView(withId(R.id.usernameInput)).check(matches(withText("Daniela")));

        onView(withId(R.id.passwordInput)).check(matches(withText("1234")));
    }
    @Test
    public void test10RotationKeepsRegisterData() {

        onView(withId(R.id.registerText)).perform(click());

        waitForData();

        onView(withId(R.id.registerUsernameInput))
                .perform(replaceText("usuario"), closeSoftKeyboard());

        onView(withId(R.id.registerPasswordInput))
                .perform(replaceText("1234"), closeSoftKeyboard());

        onView(withId(R.id.registerConfirmPasswordInput))
                .perform(replaceText("1234"), closeSoftKeyboard());

        rotateScreen();

        onView(withId(R.id.registerUsernameInput))
                .check(matches(withText("usuario")));

        onView(withId(R.id.registerPasswordInput))
                .check(matches(withText("1234")));

        onView(withId(R.id.registerConfirmPasswordInput))
                .check(matches(withText("1234")));
    }
    @Test
    public void test11RotationKeepsDetailData() {

        onView(withId(R.id.invitadoText)).perform(click());

        waitForData();

        onView(withId(R.id.communicationsButton)).perform(click());

        waitForData();

        onView(new RecyclerViewMatcher(R.id.product_recycler)
                .atPositionOnView(0, R.id.product_name))
                .perform(click());

        waitForData();

        rotateScreen();

        onView(withId(R.id.detailName))
                .check(matches(withText("Starlink")));

        onView(withId(R.id.detailAgency))
                .check(matches(withText("🏢 Agencia: SpaceX")));
    }


}