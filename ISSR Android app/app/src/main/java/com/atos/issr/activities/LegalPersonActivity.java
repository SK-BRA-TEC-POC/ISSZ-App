package com.atos.issr.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.atos.issr.R;
import com.atos.issr.modules.rx.model.interactor.DefaultSubscriber;
import com.atos.issr.modules.rx.model.interactor.useCase.LegalPersonService;
import com.atos.issr.modules.rx.model.ws.dtos.request.LegalPersonRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.CitizenResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.LegalPersonResponse;

import static com.atos.issr.utils.Constants.DETAILED_REQUEST_DATA;

public class LegalPersonActivity extends ActivityWithProgressBar {
    public static final String TAG = "LegalPersonActivity";

    private int covidTypeRequest;
    private EditText legalPersonIdEditText;
    private LegalPersonService service = new LegalPersonService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagal_person);
        legalPersonIdEditText = findViewById(R.id.legal_person_id_edit_text);
        initComponents();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.covid_state1:
                if (checked) {
                    covidTypeRequest = 1;
                    break;
                }
            case R.id.covid_state2:
                if (checked) {
                    covidTypeRequest = 2;
                    break;
                }
            default:
                //nothing
        }
        Log.d(TAG, "Covid state changed to state: '" + covidTypeRequest + '\'');
    }

    @Override
    View.OnClickListener onSearchButtonClick() {
        Activity intent = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading();

                if (legalPersonIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(intent, R.string.required_fields_empty, Toast.LENGTH_LONG).show();
                    stopLoading();
                } else if (legalPersonIdEditText.getText().length() == 6
                        || legalPersonIdEditText.getText().length() == 8) {
                    LegalPersonRequest request = new LegalPersonRequest(covidTypeRequest, legalPersonIdEditText.getText().toString());
                    service.execute(new LegalPersonResultSubscriber(), request);
                } else {
                    Toast.makeText(intent, R.string.invalid_legal_person_id, Toast.LENGTH_LONG).show();
                    stopLoading();
                }
                // TODO: 3. 6. 2020 call ws
            }
        };
    }

    private class LegalPersonResultSubscriber extends DefaultSubscriber<ISSRResponse> {

        @Override
        public void onError(Throwable e) {
            stopLoading();
            Log.d(TAG, e.getMessage(), e);
        }

        @Override
        public void onNext(ISSRResponse issrResponse) {
            stopLoading();

            if (issrResponse.getCode() == 0 && issrResponse instanceof LegalPersonResponse) {
                // switch to result Activity
                LegalPersonResponse response = (LegalPersonResponse) issrResponse;
                if (response.getListOfRequests().isEmpty()) {

                } else if (response.getListOfRequests().size() == 1) {
                    // go to screen with detail
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra(DETAILED_REQUEST_DATA, response.getListOfRequests().get(0));
                    startActivity(intent);
                } else {
                    // TODO: 4. 6. 2020 go to screen with more requests
                }
            }
        }
    }
}