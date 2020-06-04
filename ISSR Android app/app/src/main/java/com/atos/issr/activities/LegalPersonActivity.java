package com.atos.issr.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atos.issr.R;
import com.atos.issr.modules.rx.model.interactor.DefaultSubscriber;
import com.atos.issr.modules.rx.model.interactor.useCase.LegalPersonService;
import com.atos.issr.modules.rx.model.ws.dtos.request.LegalPersonRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.LegalPersonResponse;

import static com.atos.issr.activities.ErrorActivity.ERROR_MESSAGE_DATA;
import static com.atos.issr.activities.RequestsListActivity.REQUESTS_LIST_DATA;
import static com.atos.issr.utils.Constants.DETAILED_REQUEST_DATA;

public class LegalPersonActivity extends ActivityWithProgressBar {
    public static final String TAG = "LegalPersonActivity";

    private EditText legalPersonIdEditText;
    private RadioGroup radioButtonGroup;
    private LegalPersonService service = new LegalPersonService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagal_person);
        legalPersonIdEditText = findViewById(R.id.legal_person_id_edit_text);
        radioButtonGroup = findViewById(R.id.radio_button_selector);
        radioButtonGroup.getCheckedRadioButtonId();
        initComponents();
    }

    private int getRequestType(int radioButtonId) {
        switch (radioButtonId) {
            case R.id.covid_state1:
                    return  1;
            case R.id.covid_state2:
                    return  2;
            default:
                return -1;
                //nothing
        }
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
                    LegalPersonRequest request = new LegalPersonRequest(getRequestType(radioButtonGroup.getCheckedRadioButtonId()), legalPersonIdEditText.getText().toString());
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
                    Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                    intent.putExtra(ERROR_MESSAGE_DATA, getResources().getString(R.string.unexpected_state));
                    startActivity(intent);
                } else if (response.getListOfRequests().size() == 1) {
                    // go to screen with detail
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra(DETAILED_REQUEST_DATA, response.getListOfRequests().get(0));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), RequestsListActivity.class);
                    intent.putExtra(REQUESTS_LIST_DATA, response);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                intent.putExtra(ERROR_MESSAGE_DATA, issrResponse.getDescription());
                startActivity(intent);
            }
        }
    }
}