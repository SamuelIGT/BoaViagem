package br.ufc.quixada.boaviagem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

import br.ufc.quixada.boaviagem.adapter.ExpensesListViewAdapter;
import br.ufc.quixada.boaviagem.adapter.ListViewCustomAdapter;
import br.ufc.quixada.boaviagem.dao.Cost;

public class ExpensesListActivity extends AppCompatActivity {

    private ListView expensesListView;
    private List<Cost> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        this.expensesListView = (ListView) findViewById(R.id.lstView_expenses);
        populateList(expenses);
        ExpensesListViewAdapter adapter = new ExpensesListViewAdapter(expenses, this);
        expensesListView.setAdapter(adapter);
    }

    private void populateList(List<Cost> expenses) {
        Cost a = new Cost(01, "Alimentaçao", 1000, Calendar.getInstance().getTime(), "Bolo de chocolate", "Sao Paulo");
        Cost b = new Cost(01, "Transporte", 500, Calendar.getInstance().getTime(), "Coxinha", "Fortaleza");
        expenses.add(a);
        expenses.add(b);
    }
}
