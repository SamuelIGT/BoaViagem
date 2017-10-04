package br.ufc.quixada.boaviagem.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import br.ufc.quixada.boaviagem.activity.NewCostActivity;
import br.ufc.quixada.boaviagem.activity.NewTripActivity;

/**
 * Created by samue on 24/09/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public DatePickerFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        String activityIdentifier = getArguments().getString("Activity_Identifier");
        if(activityIdentifier.equals("NEW_TRIP")){
            return new DatePickerDialog(getActivity(), (NewTripActivity)getActivity(), year, month, day);
        }else {
            return new DatePickerDialog(getActivity(), (NewCostActivity)getActivity(), year, month, day);
        }

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
