package br.ufc.quixada.boaviagem.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.StorageController;
import br.ufc.quixada.boaviagem.adapter.ListViewCustomAdapter;
import br.ufc.quixada.boaviagem.dao.Viagem;

public class MyTravelsActivity extends AppCompatActivity {
    private ListView listViewTravels;
    private List<Viagem> travels;
    private StorageController storage = new StorageController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travels);

        populateList();

        listViewTravels = (ListView) findViewById(R.id.listView_Travels);
        ListViewCustomAdapter adapter = new ListViewCustomAdapter(travels, this);

        listViewTravels.setAdapter(adapter);
        listViewTravels.setEmptyView(findViewById(R.id.txt_empty_listview));

        setupList();
    }

    private void setupList(){
        listViewTravels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showOptionsDialog(MyTravelsActivity.this, position);
            }
        });
    }

    private void showOptionsDialog(final Context context, final int itemPosition){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_my_travels_options, null);

        TextView newCost = (TextView) mView.findViewById(R.id.txt_newCost);
        TextView myExpenses = (TextView) mView.findViewById(R.id.txt_myExpenses);
        TextView remove = (TextView) mView.findViewById(R.id.txt_remove);
        TextView edit = (TextView) mView.findViewById(R.id.txt_edit);

        mBuilder.setView(mView);
        final AlertDialog finishAlert = mBuilder.create();

        newCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCost = new Intent(MyTravelsActivity.this, NewCostActivity.class);
                startActivity(newCost);
                finishAlert.dismiss();
            }
        });

        myExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyTravelsActivity.this, ExpensesListActivity.class);
                intent.putExtra(getString(R.string.INTENT_TRAVEL_EXPENSES_ID), itemPosition);
                finishAlert.dismiss();
                startActivity(intent);

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAlert.dismiss();
                showRemoveConfirmationDialog(context, itemPosition);

            }
            
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyTravelsActivity.this, NewTripActivity.class);
                intent.putExtra(getString(R.string.KEY_EDIT_TRIP), travels.get(itemPosition).getId());
                startActivity(intent);
                finishAlert.dismiss();
            }
        });
        finishAlert.show();

    }

    private void showRemoveConfirmationDialog(Context context, final int itemPosition) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_remove_confirmation, null);
        mBuilder.setView(mView);
        final AlertDialog finishAlert = mBuilder.create();

        Button confirm = (Button) mView.findViewById(R.id.btn_remove_yes);
        Button negate = (Button) mView.findViewById(R.id.btn_remove_no);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implementar animaçao de slide quando deletar.
                travels.remove(itemPosition);
                //TODO: salvar lista atualizada.
                ((BaseAdapter)listViewTravels.getAdapter()).notifyDataSetChanged();
                finishAlert.dismiss();
            }
        });

        negate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAlert.dismiss();
            }
        });

        finishAlert.show();
    }

    @Override
    protected void onRestart() {
        updateListView();
        //TODO: Listview nao atualiza apos edita. Retornar Objeto para ser atualizado na lista daqui
        Log.d("onRestart", "TRUE ");
        super.onRestart();
    }

    private void updateListView() {
        ((BaseAdapter)listViewTravels.getAdapter()).notifyDataSetChanged();
    }

    private void populateList(){
        travels = storage.getTravels(getString(R.string.STORAGE_KEY_TRAVELS), this);
        if(travels == null){
            travels = new ArrayList<>();
        }
    }
}
