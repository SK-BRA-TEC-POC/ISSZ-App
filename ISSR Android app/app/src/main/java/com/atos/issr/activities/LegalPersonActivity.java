package com.atos.issr.activities;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.atos.issr.R;

public class LegalPersonActivity extends BaseActivity {
    public static final String TAG = "LegalPersonActivity";

    private int covidState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagal_person);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.covid_state1:
                if (checked) {
                    covidState = 1;
                    break;
                }
            case R.id.covid_state2:
                if (checked) {
                    covidState = 2;
                    break;
                }
            default:
                //nothing
        }
        Log.d(TAG, "Covid state changed to state: '"+covidState+'\'');
    }
}