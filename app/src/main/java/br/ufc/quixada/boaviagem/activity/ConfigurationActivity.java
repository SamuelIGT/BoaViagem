package br.ufc.quixada.boaviagem.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import br.ufc.quixada.boaviagem.fragments.MyPreferenceFragment;

/**
 * Created by samue on 03/10/2017.
 */

public class ConfigurationActivity extends PreferenceActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }
}
