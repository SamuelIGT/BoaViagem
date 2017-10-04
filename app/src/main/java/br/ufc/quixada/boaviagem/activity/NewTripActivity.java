package br.ufc.quixada.boaviagem.activity;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.StorageController;
import br.ufc.quixada.boaviagem.dao.Viagem;
import br.ufc.quixada.boaviagem.fragments.DatePickerFragment;

public class NewTripActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button arrivalDate;
    private Button departureDate;
    private boolean isDepartureDate;
    private EditText destination;
    private RadioGroup travelType;
    private EditText budget;
    private EditText peopleAmount;
    private StorageController storage = new StorageController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        this.destination = (EditText) findViewById(R.id.editTxt_destination);
        this.travelType = (RadioGroup) findViewById(R.id.radioGroup_tripType);
        this.budget = (EditText) findViewById(R.id.editTxt_budget);
        this.peopleAmount = (EditText) findViewById(R.id.editTxt_people_amount);
        this.arrivalDate = (Button) findViewById(R.id.btn_arrival);
        this.departureDate = (Button) findViewById(R.id.btn_departure);
    }

    public void createTravel(View view){
        String destinationText;
        double totalSpend;
        boolean isBusiness;
        Date arrivalDate;
        Date departureDate;
        int amount;

        destinationText = this.destination.getText().toString();
        amount = Integer.parseInt(this.peopleAmount.getText().toString());

        totalSpend = Double.parseDouble(budget.getText().toString());
        isBusiness = isBusinessChecked(travelType);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            arrivalDate = df.parse(this.arrivalDate.getText().toString());
            departureDate = df.parse(this.departureDate.getText().toString());
        } catch (ParseException e) {
            arrivalDate = Calendar.getInstance().getTime();
            departureDate = Calendar.getInstance().getTime();
            e.printStackTrace();
        }

        Viagem travel = new Viagem(destinationText, totalSpend, isDepartureDate, arrivalDate, departureDate);

        storage.saveTravel(getString(R.string.STORAGE_KEY_TRAVELS), this, travel);
        finish();
    }
    private boolean isBusinessChecked(RadioGroup radioGroup){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);

        RadioButton r = (RadioButton)  radioGroup.getChildAt(idx);
        if(r != null){
            if(r.getText().toString().equals(getString(R.string.TRAVEL_TYPE_BUSINESS))){
                return true;
            }else{
                return false;
            }
        }else{
            Toast toast = Toast.makeText(this,"Nenhum tipo de viagem cadastrada!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

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
