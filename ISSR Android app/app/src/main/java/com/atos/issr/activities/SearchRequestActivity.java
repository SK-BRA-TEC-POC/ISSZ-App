package com.atos.issr.activities;

import android.app.Activity;
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

public class SearchRequestActivity extends ActivityWithProgressBar {
    private EditText requestIdEditText;
    private SearchRequestService service = new SearchRequestService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_request);
        initComponents();
        requestIdEditText = findViewById(R.id.request_id_edit_text);
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
        public void onNext(ISSRResponse getProcessResponse) {
            stopLoading();
            // TODO: 3. 6. 2020 switch to result Activity
        }
    }
}