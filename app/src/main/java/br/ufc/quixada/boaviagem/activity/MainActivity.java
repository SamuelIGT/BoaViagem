package br.ufc.quixada.boaviagem.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.ufc.quixada.boaviagem.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void newTrip(View view) {
        Intent newTrip = new Intent(this, NewTripActivity.class);
        startActivity(newTrip);
    }

    void newCost(View view) {
        Intent newCost = new Intent(this, NewCostActivity.class);
        startActivity(newCost);
    }

    void myTravels(View view){
        Intent myTravel = new Intent(this, MyTravelsActivity.class);
        startActivity(myTravel);
    }
    void configuration(View view){
        Intent config = new Intent(this, ConfigurationActivity.class);
        startActivity(config);

    }
}

