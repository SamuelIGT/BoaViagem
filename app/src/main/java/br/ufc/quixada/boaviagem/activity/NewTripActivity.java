package br.ufc.quixada.boaviagem.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private Button confirm;
    private StorageController storage = new StorageController();
    private long editTravelID;

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
        this.confirm = (Button) findViewById(R.id.btn_new_travel_confirm);

        editTravelID = getIntent().getLongExtra(getString(R.string.KEY_EDIT_TRIP), -1);
        setupConfirmation();
    }

    private void setupConfirmation() {
        if (editTravelID >= 0){
            Viagem travelEdit = storage.getTravelById(getString(R.string.STORAGE_KEY_TRAVELS), this, editTravelID);
            destination.setText(travelEdit.getDestination());
            budget.setText(getString(R.string.New_Travel_Budget_Field, travelEdit.getBudget()));
            peopleAmount.setText(travelEdit.getNumberOfPeople());
            arrivalDate.setText(travelEdit.getArrivalDateString());
            departureDate.setText(travelEdit.getDepartureDateString());
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String destinationText;
                double totalSpend;
                boolean isBusiness;
                Date arrival;
                Date departure;
                int amount;

                destinationText = destination.getText().toString();

                amount = (!peopleAmount.getText().toString().equals("")) ? Integer.parseInt(peopleAmount.getText().toString()) : 0;

                totalSpend = (!budget.getText().toString().equals("")) ? Double.parseDouble(budget.getText().toString()) : 0;
                isBusiness = isBusinessChecked(travelType);

                //TODO: Data de chegada deve ser maior que a de saida.
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    arrival = df.parse(arrivalDate.getText().toString());
                    departure = df.parse(departureDate.getText().toString());
                } catch (ParseException e) {
                    arrival = Calendar.getInstance().getTime();
                    departure = Calendar.getInstance().getTime();
                    e.printStackTrace();
                }

                if (editTravelID >= 0) {
                    Log.d("ExtraEDIT", ""+editTravelID);
                    editTravel(destinationText, totalSpend, isBusiness, arrival, departure, amount);
                } else {
                    createTravel(destinationText, totalSpend, isBusiness, arrival, departure, amount);
                }
            }
        });
    }

    private void editTravel(String destinationText, double budget, boolean isBusiness, Date arrivalDate, Date departureDate, int amount) {
        if(validateData(destinationText, budget, isBusiness, arrivalDate, departureDate, amount)) {
            Viagem travel = new Viagem(destinationText, budget, isBusiness, arrivalDate, departureDate, amount);
            long id = editTravelID;
            storage.editTravel(getString(R.string.STORAGE_KEY_TRAVELS), this, travel, id);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", ""+id);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }else{
            Toast toast = Toast.makeText(this, "Voce deixou algum campo vazio.", Toast.LENGTH_SHORT);
        }

    }

    public void createTravel(String destinationText, double totalSpend, boolean isBusiness, Date arrivalDate, Date departureDate, int amountOfPeople) {
        if(validateData(destinationText, totalSpend, isBusiness, arrivalDate, departureDate, amountOfPeople)){
            Viagem travel = new Viagem(destinationText, totalSpend, isBusiness, arrivalDate, departureDate, amountOfPeople);
            storage.saveTravel(getString(R.string.STORAGE_KEY_TRAVELS), this, travel);
            finish();
        }else{
            Toast toast = Toast.makeText(this, "Voce deixou algum campo vazio.", Toast.LENGTH_SHORT);
        }

    }
    public boolean validateData(String destinationText, double totalSpend, boolean isBusiness, Date arrivalDate, Date departureDate, int amountOfPeople){
        if (destinationText.equals("")){
            return false;
        }
        if (totalSpend == 0){
            return false;
        }
        if(arrivalDate.getTime() < departureDate.getTime() ){
            arrivalDate = departureDate;
        }
        if (amountOfPeople == 0){
            return false;
        }
        return true;

    }

    private boolean isBusinessChecked(RadioGroup radioGroup) {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);

        RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
        if (r != null) {
            if (r.getText().toString().equals(getString(R.string.TRAVEL_TYPE_BUSINESS))) {
                return true;
            } else {
                return false;
            }
        } else {
            Toast toast = Toast.makeText(this, "Nenhum tipo de viagem cadastrada!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

    }

    public void showArrivalDateDialog(View v) {
        isDepartureDate = false;
        showDatePickerDialog();
    }

    public void showDepartureDateDialog(View v) {
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
        if (isDepartureDate) {
            departureDate.setText(day + "/" + month + "/" + year);
        } else
            arrivalDate.setText(day + "/" + month + "/" + year);

    }

    private Date createDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }

}
