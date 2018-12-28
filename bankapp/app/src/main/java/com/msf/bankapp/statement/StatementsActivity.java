package com.msf.bankapp.statement;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msf.bankapp.R;
import com.msf.bankapp.login.LoginActivity;
import com.msf.bankapp.login.UserAccount;
import com.msf.bankapp.util.EspressoIdlingResource;

import java.util.List;

import br.com.concrete.canarinho.formatador.Formatador;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatementsActivity extends AppCompatActivity implements StatementInteractor.StatementListener {

    @BindView(R.id.account_name)
    TextView mAccountName;

    @BindView(R.id.account)
    TextView mAccount;

    @BindView(R.id.balanceValue)
    TextView mBalance;

    @BindView(R.id.statements)
    RecyclerView mRecyclerViewStatements;

    @BindView(R.id.lasts)
    TextView mLasts;

    @BindView(R.id.statements_progress)
    ProgressBar mProgressLoading;

    private UserAccount userAccount;

    private StatementInteractor mStatementInteractor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);
        if(getIntent() != null && getIntent().hasExtra(LoginActivity.KEY_USER)){
            userAccount = getIntent().getParcelableExtra(LoginActivity.KEY_USER);
        }
        ButterKnife.bind(this);
        mStatementInteractor = new StatementInteractor(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStatementInteractor.getStatement(userAccount.getUserId());
        setVisibilityOfRecycler(false);
        buildAccountViews();
    }

    private void buildAccountViews() {
        mAccountName.setText(userAccount.getName());
        mAccount.setText(getString(R.string.account_value, userAccount.getBankAccount(), userAccount.getAgency()));
        mBalance.setText(Formatador.VALOR_COM_SIMBOLO.formata(String.valueOf(userAccount.getBalance())));
    }

    private void setVisibilityOfRecycler(boolean showRecyclerView){
        mRecyclerViewStatements.setVisibility(showRecyclerView ? View.VISIBLE : View.INVISIBLE);
        mLasts.setVisibility(showRecyclerView ? View.VISIBLE : View.INVISIBLE);
        mProgressLoading.setVisibility(!showRecyclerView ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onStatementRetrieved(List<Statement> listStatement, boolean hasError) {
        if(hasError){
            mProgressLoading.setVisibility(View.INVISIBLE);
            Snackbar.make(getWindow().getDecorView(), getString(R.string.an_error_has_occurred), Snackbar.LENGTH_LONG).show();
        } else if(listStatement != null){
            buildRecyclerView(listStatement);
            setVisibilityOfRecycler(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EspressoIdlingResource.decrement();
    }

    private void buildRecyclerView(List<Statement> listStatement) {
        StatementAdapter statementAdapter = new StatementAdapter(listStatement);
        mRecyclerViewStatements.setAdapter(statementAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.space));
        mRecyclerViewStatements.setLayoutManager(linearLayoutManager);
        mRecyclerViewStatements.addItemDecoration(dividerItemDecoration);
    }

    @OnClick(R.id.out)
    public void onClickOut(View view){
        NavUtils.navigateUpFromSameTask(this);
    }

}
