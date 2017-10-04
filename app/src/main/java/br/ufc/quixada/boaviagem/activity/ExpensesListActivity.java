package br.ufc.quixada.boaviagem.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.StorageController;
import br.ufc.quixada.boaviagem.adapter.ExpensesListViewAdapter;
import br.ufc.quixada.boaviagem.dao.Cost;

public class ExpensesListActivity extends AppCompatActivity {

    private ListView expensesListView;
    private List<Cost> expenses;
    private StorageController storage = new StorageController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        getExpenseList();

        this.expensesListView = (ListView) findViewById(R.id.lstView_expenses);
        ExpensesListViewAdapter adapter = new ExpensesListViewAdapter(expenses, this);
        expensesListView.setAdapter(adapter);
    }

    private void getExpenseList() {
        int itemIndex = getIntent().getIntExtra(getString(R.string.INTENT_TRAVEL_EXPENSES_ID), -1);
        if(itemIndex > -1){
            expenses = storage.getTravels(getString(R.string.STORAGE_KEY_TRAVELS), this).get(itemIndex).getExpenses();
        }
        else{
            Log.d("EXPENSES_LIST","INTENT NULL");
            expenses = new ArrayList<>();
        }
    }
}
