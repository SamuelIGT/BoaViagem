package br.ufc.quixada.boaviagem;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import br.ufc.quixada.boaviagem.fragments.DatePickerFragment;

public class NewCostActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner category;
    private Spinner local;
    private Button datePicker;
    private Button done;
    private EditText amount;
    private EditText description;

    private ArrayAdapter<CharSequence> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cost);

        category = (Spinner) findViewById(R.id.spinner_category);
        local = (Spinner) findViewById(R.id.spinner_local);
        datePicker = (Button) findViewById(R.id.btn_costDate);
        setupSpinners();
    }


    private void setupSpinners() {
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category_cost, R.layout.spinner_item);
        category.setAdapter(categoryAdapter);

//        locations = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, getIntent().getCharSequenceArrayExtra("LOCATION_EXTRA"));

  //      local.setAdapter(locations);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString("Activity_Identifier", "NEW_COST");
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Date data = createDate(year, month, day);
        datePicker.setText(day+"/"+month+"/"+year);
    }

    private Date createDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }
}
