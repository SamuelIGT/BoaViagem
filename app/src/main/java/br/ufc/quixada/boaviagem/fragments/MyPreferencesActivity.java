package br.ufc.quixada.boaviagem.fragments;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import br.ufc.quixada.boaviagem.ConfigurationActivity;

/**
 * Created by samue on 03/10/2017.
 */

public class MyPreferencesActivity extends PreferenceActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new ConfigurationActivity()).commit();
    }
}
