package br.ufc.quixada.boaviagem;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import br.ufc.quixada.boaviagem.fragments.DatePickerFragment;

public class NewTripActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button arrivalDate;
    private Button departureDate;
    private boolean isDepartureDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        this.arrivalDate = (Button) findViewById(R.id.btn_arrival);
        this.departureDate = (Button) findViewById(R.id.btn_departure);
    }

    public void showArrivalDateDialog(View v){
        isDepartureDate = false;
        showDatePickerDialog();
    }
    public void showDepartureDateDialog(View v){
        isDepartureDate = true;
        showDatePickerDialog();
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString("Activity_Identifier", "NEW_TRIP");
        newFragment.setArguments(args);

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Date data = createDate(year, month, day);
        if(isDepartureDate){
            departureDate.setText(day+"/"+month+"/"+year);
        }else
            arrivalDate.setText(day+"/"+month+"/"+year);

    }

    private Date createDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }

}
