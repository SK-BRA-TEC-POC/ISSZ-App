package com.atos.issr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.atos.issr.R;
import com.atos.issr.modules.rx.model.interactor.DefaultSubscriber;
import com.atos.issr.modules.rx.model.interactor.useCase.SearchRequestService;
import com.atos.issr.modules.rx.model.ws.dtos.request.SearchRequestRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.SearchRequestResponse;

import static com.atos.issr.activities.ErrorActivity.ERROR_MESSAGE_DATA;
import static com.atos.issr.utils.Constants.DETAILED_REQUEST_DATA;

public class SearchRequestActivity extends ActivityWithProgressBar {
    private EditText requestIdEditText;
    private SearchRequestService service = new SearchRequestService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_request);
        initComponents();
        requestIdEditText = findViewById(R.id.request_id_text_view);
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
}