package br.ufc.quixada.boaviagem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.boaviagem.adapter.ListViewCustomAdapter;
import br.ufc.quixada.boaviagem.dao.Viagem;

public class MyTravelsActivity extends AppCompatActivity {
    private ListView listViewTravels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travels);
        List<Viagem> travels = new ArrayList<>();
        listViewTravels = (ListView) findViewById(R.id.listView_Travels);

        populateList(travels);
        ListViewCustomAdapter adapter = new ListViewCustomAdapter(travels, this);
        listViewTravels.setAdapter(adapter);
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

    private void showOptionsDialog(Context context, int iemPosition){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_my_travels_options, null);

        TextView newCost = (TextView) mView.findViewById(R.id.txt_newCost);
        TextView myExpenses = (TextView) mView.findViewById(R.id.txt_myExpenses);
        TextView remove = (TextView) mView.findViewById(R.id.txt_remove);
        TextView edit = (TextView) mView.findViewById(R.id.txt_edit);

        newCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCost = new Intent(MyTravelsActivity.this, NewCostActivity.class);
                startActivity(newCost);
            }
        });

        myExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
            
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBuilder.setView(mView);
        final AlertDialog finishAlert = mBuilder.create();

        finishAlert.show();

    }

    private void populateList(List<Viagem> travels){
        Viagem a = new Viagem("Sao Paulo", "10/09/2017 a 14/09/2017", 300, R.drawable.ic_launcher);
        Viagem b = new Viagem("Fortaleza", "15/09/2017 a 24/09/2017", 400, R.drawable.ic_launcher);
        travels.add(a);
        travels.add(b);
    }
}
