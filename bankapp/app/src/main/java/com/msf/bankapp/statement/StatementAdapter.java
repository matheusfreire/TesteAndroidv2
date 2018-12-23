package com.msf.bankapp.statement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msf.bankapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.concrete.canarinho.formatador.Formatador;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementViewHolder> {

    private static final String PATTERN_BR_DATE = "dd/MM/yyyy";
    private static final String PATTERN_ISO_8601 = "yyyy-MM-dd";
    private List<Statement> mStatementList;

    StatementAdapter(List<Statement> statements){
        this.mStatementList = statements;
    }

    @NonNull
    @Override
    public StatementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statement_item, viewGroup, false);
        return new StatementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementViewHolder viewHolder, int i) {
        Statement statement = getStatement(viewHolder.getAdapterPosition());
        viewHolder.mItemTitle.setText(statement.getTitle());
        viewHolder.mItemDescription.setText(statement.getDesc());
        viewHolder.mItemDate.setText(buildDate(statement.getDate()));
        viewHolder.mItemValue.setText(buildValue(statement.getValue()));
    }

    private String buildValue(Double value){
        return Formatador.VALOR_COM_SIMBOLO.formata(String.valueOf(value));
    }

    private String buildDate(String itemDate) {
        try {
            Date date = new SimpleDateFormat(PATTERN_ISO_8601, new Locale("pt", "BR")).parse(itemDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_BR_DATE, new Locale("pt", "BR"));
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Statement getStatement(int position){
        return mStatementList.get(position);
    }

    @Override
    public int getItemCount() {
        return mStatementList.size();
    }


    class StatementViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_title)
        TextView mItemTitle;

        @BindView(R.id.item_date)
        TextView mItemDate;

        @BindView(R.id.itemDescription)
        TextView mItemDescription;

        @BindView(R.id.itemValue)
        TextView mItemValue;

        public StatementViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
