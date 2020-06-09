package com.atos.issr.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.atos.issr.R;
import com.atos.issr.modules.barcode.BarcodeScannerActivity;
import com.atos.issr.modules.rx.model.interactor.DefaultSubscriber;
import com.atos.issr.modules.rx.model.interactor.useCase.SearchRequestService;
import com.atos.issr.modules.rx.model.ws.dtos.request.SearchRequestRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.SearchRequestResponse;

import java.util.ArrayList;
import java.util.List;

import static com.atos.issr.activities.ErrorActivity.ERROR_MESSAGE_DATA;
import static com.atos.issr.modules.barcode.BarcodeScannerFragment.BARCODE_VALUE_NAME;
import static com.atos.issr.utils.Constants.DETAILED_REQUEST_DATA;

public class SearchRequestActivity extends ActivityWithProgressBar {
    public static final int PERMISSION_REQUEST_CODE = 1;

    private EditText requestIdEditText;
    private Button scanBarcodeButton;
    private SearchRequestService service = new SearchRequestService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_request);
        initComponents();
        requestIdEditText = findViewById(R.id.request_id_text_view);
        scanBarcodeButton = findViewById(R.id.scan_barcode_button);
        scanBarcodeButton.setOnClickListener(scanBarcodeListener());

        Intent intent = getIntent();
        String barcode = intent.getStringExtra(BARCODE_VALUE_NAME);

        if (barcode != null) {
            requestIdEditText.setText(barcode);
        } else {
            requestIdEditText.setText("");
        }
    }

    private View.OnClickListener scanBarcodeListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
                    }
                } else {
                    Intent barcodeReaderActivity = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                    startActivity(barcodeReaderActivity);
                }
            }
        };
    }

    @Override
    View.OnClickListener onSearchButtonClick() {
        Activity intent = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading();
                if (requestIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(intent, R.string.required_fields_empty, Toast.LENGTH_LONG).show();
                    stopLoading();
                } else {
                    SearchRequestRequest request = new SearchRequestRequest(requestIdEditText.getText().toString());
                    service.execute(new SearchRequestSubscriber(), request);
                }
            }
        };
    }

    private class SearchRequestSubscriber extends DefaultSubscriber<ISSRResponse> {

        @Override
        public void onError(Throwable e) {
            stopLoading();
            Log.d(TAG, e.getMessage(), e);
        }

        @Override
        public void onNext(ISSRResponse issrResponse) {
            stopLoading();
            if (issrResponse.getCode() == 0 && issrResponse instanceof SearchRequestResponse) {
                // switch to result Activity
                SearchRequestResponse response = (SearchRequestResponse) issrResponse;
                if (response.getListOfRequests().size() == 1) {
                    // go to screen with detail
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra(DETAILED_REQUEST_DATA, response.getListOfRequests().get(0));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                    intent.putExtra(ERROR_MESSAGE_DATA, getResources().getString(R.string.unexpected_state));
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                intent.putExtra(ERROR_MESSAGE_DATA, issrResponse.getDescription());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSION_REQUEST_CODE == requestCode) {
            List<String> unCheckedPermissions = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (PackageManager.PERMISSION_GRANTED != grantResults[i]) {
                    unCheckedPermissions.add(permissions[i]);
                }
            }

            if (unCheckedPermissions.isEmpty()) {
                Intent barcodeReaderActivity = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                startActivity(barcodeReaderActivity);
            } else {
                Toast.makeText(this, R.string.unable_to_use_functionality, Toast.LENGTH_LONG).show();
                //requestPermissions(unCheckedPermissions.toArray(new String[]{}), PERMISSION_REQUEST_CODE);
            }


        }
    }
}