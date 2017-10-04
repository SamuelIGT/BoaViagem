package br.ufc.quixada.boaviagem.activity;

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
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.StorageController;
import br.ufc.quixada.boaviagem.dao.Cost;
import br.ufc.quixada.boaviagem.fragments.DatePickerFragment;

public class NewCostActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Spinner category;
    private Spinner local;
    private Button datePicker;
    private Button done;
    private EditText amount;
    private EditText description;

    private ArrayAdapter<CharSequence> locations;
    private StorageController storage = new StorageController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cost);

        category = (Spinner) findViewById(R.id.spinner_category);
        local = (Spinner) findViewById(R.id.spinner_local);
        datePicker = (Button) findViewById(R.id.btn_costDate);
        amount = (EditText) findViewById(R.id.editTxt_amount);
        description = (EditText) findViewById(R.id.editTxt_description);

        setupSpinners();
    }


    private void setupSpinners() {
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category_cost, R.layout.spinner_item);
        category.setAdapter(categoryAdapter);

        locations = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, storage.getTravelsUniqueTitle(getString(R.string.STORAGE_KEY_TRAVELS), NewCostActivity.this));
        local.setAdapter(locations);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString("Activity_Identifier", "NEW_COST");
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void createNewCost(View view) {
        String uniqueTitle = "";
        uniqueTitle = (local.getSelectedItem() != null) ? local.getSelectedItem().toString(): uniqueTitle;

        if (!uniqueTitle.equals("")) {
            String costType = category.getSelectedItem().toString();
            double amountValue = Double.parseDouble(amount.getText().toString());
            String descriptionValue = description.getText().toString();

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {
                date = df.parse(datePicker.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
                date = Calendar.getInstance().getTime();
            }

            Cost cost = new Cost(costType, amountValue, date, descriptionValue);

            storage.saveCostByTravelUniqueTitle(getString(R.string.STORAGE_KEY_TRAVELS), this, cost, uniqueTitle);
            finish();
        }else{
            Toast toast = Toast.makeText(this,"Nenhuma Viagem Cadastrada", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Date data = createDate(year, month, day);
        datePicker.setText(day + "/" + month + "/" + year);
    }

    private Date createDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }
}
