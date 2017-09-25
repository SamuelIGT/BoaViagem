package br.ufc.quixada.boaviagem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.boaviagem.adapter.ListviewCustomAdapter;
import br.ufc.quixada.boaviagem.dao.Viagem;

public class MyTravelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travels);
        List<Viagem> travels = new ArrayList<>();
        ListView listViewTravels = (ListView) findViewById(R.id.listView_Travels);

        ListviewCustomAdapter adapter = new ListviewCustomAdapter(travels, this);

        listViewTravels.setAdapter(adapter);
    }
}
