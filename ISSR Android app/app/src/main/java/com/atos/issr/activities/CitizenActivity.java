package com.atos.issr.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.atos.issr.R;
import com.atos.issr.custom.CustomTextWatcher;
import com.atos.issr.modules.rx.model.interactor.DefaultSubscriber;
import com.atos.issr.modules.rx.model.interactor.useCase.CitizenService;
import com.atos.issr.modules.rx.model.ws.dtos.request.CitizenRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.utils.AppUtils;
import com.microblink.entities.recognizers.Recognizer;
import com.microblink.entities.recognizers.RecognizerBundle;
import com.microblink.entities.recognizers.blinkid.generic.BlinkIdRecognizer;
import com.microblink.uisettings.ActivityRunner;
import com.microblink.uisettings.DocumentVerificationUISettings;

public class CitizenActivity extends ActivityWithProgressBar {
    public static final int MY_REQUEST_CODE = 0x101;

    private int covidState;
    private Button scanIdCardButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText personalNoNameEditText;
    private static RecognizerBundle recognizerBundle;
    private DocumentVerificationUISettings documentUISettings;
    private BlinkIdRecognizer blinkIdRecognizer;
    private CitizenService citizenService = new CitizenService();
    private CitizenRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);
        initComponents();

        try {
            AppUtils.initMicroblink(this);
            blinkIdRecognizer = new BlinkIdRecognizer();
            recognizerBundle = new RecognizerBundle(blinkIdRecognizer);
            documentUISettings = new DocumentVerificationUISettings(recognizerBundle);
            documentUISettings.enableHighResSuccessFrameCapture(false);
        } catch (Exception e) {
            Log.w(TAG, "No license.", e);
        }

        firstNameEditText = findViewById(R.id.edit_text_first_name);
        lastNameEditText = findViewById(R.id.edit_text_last_name);
        personalNoNameEditText = findViewById(R.id.edit_text_personal_no);
        personalNoNameEditText.addTextChangedListener(textChangeListener());

        scanIdCardButton = findViewById(R.id.scan_id_card_button);
        if (blinkIdRecognizer == null) {
            scanIdCardButton.setEnabled(false);
        } else {
            scanIdCardButton.setOnClickListener(scanIdCardOnClickListener());
        }
    }

    private TextWatcher textChangeListener() {
        return new CustomTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StringBuilder sb = new StringBuilder(10);
                sb.append(s);
                if (sb.length() == 6 && !sb.toString().contains("/") && before != 1) {
                    sb.append('/');
                    personalNoNameEditText.setText(sb.toString());
                    personalNoNameEditText.setSelection(sb.length());
                } else if (sb.length() == 7 && !sb.toString().contains("/")) {
                    char extraChar = sb.charAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1)
                            .append('/')
                            .append(extraChar);
                    personalNoNameEditText.setText(sb.toString());
                    personalNoNameEditText.setSelection(sb.length());
                }
            }
        };
    }

    private View.OnClickListener scanIdCardOnClickListener() {
        Activity intent = this;
        return listener -> {
            firstNameEditText.setText("");
            lastNameEditText.setText("");
            personalNoNameEditText.setText("");

            ActivityRunner.startActivityForResult(intent, MY_REQUEST_CODE, documentUISettings);
        };
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                recognizerBundle.loadFromIntent(data);
                Recognizer<Recognizer.Result> recognizer = recognizerBundle.getRecognizers()[0];
                BlinkIdRecognizer.Result result = (BlinkIdRecognizer.Result) recognizer.getResult();
                if (Recognizer.Result.State.Valid == result.getResultState()) {
                    firstNameEditText.setText(result.getFirstName());
                    lastNameEditText.setText(result.getLastName());
                    personalNoNameEditText.setText(result.getPersonalIdNumber());

                    // TODO: 3. 6. 2020 uncomment line below if want automation
                    //searchButton.callOnClick();
                }
            }
        }
    }

    @Override
    View.OnClickListener onSearchButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading();

                if (firstNameEditText.getText().toString().isEmpty() ||
                        lastNameEditText.getText().toString().isEmpty() ||
                        personalNoNameEditText.getText().toString().isEmpty()) {
                } else {
                    request = new CitizenRequest(firstNameEditText.getText().toString(),
                            lastNameEditText.getText().toString(),
                            personalNoNameEditText.getText().toString());

                    // TODO: 3. 6. 2020 call ws
                    citizenService.execute(new CitizenServiceSubscriber(), request);
                }

            }
        };
    }

    private class CitizenServiceSubscriber extends DefaultSubscriber<ISSRResponse> {

        @Override
        public void onError(Throwable e) {
            stopLoading();
            Log.d(TAG, e.getMessage(), e);
        }

        @Override
        public void onNext(ISSRResponse getProcessResponse) {
            stopLoading();
            // TODO: 3. 6. 2020 switch to result Activity
        }
    }
}