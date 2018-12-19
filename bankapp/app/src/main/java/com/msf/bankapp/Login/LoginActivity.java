package com.msf.bankapp.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.msf.bankapp.R;
import com.msf.bankapp.util.ScreenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginInteractor.LoginListener{

    @BindView(R.id.user)
    TextInputEditText mUserEdit;

    @BindView(R.id.password)
    TextInputEditText mPasswordView;

    @BindView(R.id.login_form)
    View mLoginFormView;

    @BindView(R.id.login_progress)
    View mProgressView;

    private LoginInteractor mLoginInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin(mPasswordView);
                return true;
            }
            return false;
        });
    }


    @OnClick(R.id.sign_in_button)
    public void attemptLogin(View view) {
        mUserEdit.setError(null);
        mPasswordView.setError(null);

        String user = mUserEdit.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(user)) {
            mUserEdit.setError(getString(R.string.error_field_required));
            focusView = mUserEdit;
            cancel = true;
        }
        showProgress(true);
        initializeInteractor();
        ScreenUtil.hideKeyboard(this);
        mLoginInteractor.attemptToLogin(user, password);
    }

    private void initializeInteractor() {
        if (mLoginInteractor == null) {
            mLoginInteractor = new LoginInteractor(this);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onDataRetrieved(UserAccount userAccount) {

    }
}