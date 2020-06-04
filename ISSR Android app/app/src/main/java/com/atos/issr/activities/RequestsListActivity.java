package com.atos.issr.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.atos.issr.R;
import com.atos.issr.custom.RequestsListAdapter;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.types.DetailedRequest;

import java.util.List;

public class RequestsListActivity extends BaseActivity {
    public static final String REQUESTS_LIST_DATA = "REQUESTS_LIST_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);

        List<DetailedRequest> listOfRequests = ((ISSRResponse) getIntent().getSerializableExtra(REQUESTS_LIST_DATA)).getListOfRequests();

        if (listOfRequests != null) {
            ListView requestsList = findViewById(R.id.requests_list_view);
            requestsList.setAdapter(new RequestsListAdapter(getApplicationContext(), 0, listOfRequests, this));
        }
    }

}