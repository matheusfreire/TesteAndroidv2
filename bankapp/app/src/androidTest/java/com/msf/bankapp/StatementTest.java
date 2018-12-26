package com.msf.bankapp;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.msf.bankapp.login.LoginActivity;
import com.msf.bankapp.util.EspressoIdlingResource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.concrete.canarinho.formatador.Formatador;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class StatementTest {

    private final Object o = new Object();

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void testLoginSuccessful(){
        onView(withId(R.id.user)).perform(typeText("test_user"));
        onView(withId(R.id.password)).perform(typeText("Test@1"), closeSoftKeyboard());
        onView(withId(R.id.sign_in_button)).perform(click());
        Intent intent = new Intent();
        onView(withId(R.id.account_name)).check(matches(withText("Jose da Silva Teste")));
        onView(withId(R.id.account)).check(matches(withText(getValueBankAccountAndAgency())));
        onView(withId(R.id.balanceValue)).check(matches(withText(getBalanceValueFormatted())));
    }

    private String getBalanceValueFormatted() {
        return Formatador.VALOR_COM_SIMBOLO.formata(String.valueOf(3.3445));
    }

    private String getValueBankAccountAndAgency(){
        return InstrumentationRegistry.getContext().getString(R.string.account_value, "2050", "012314564");
    }

}
