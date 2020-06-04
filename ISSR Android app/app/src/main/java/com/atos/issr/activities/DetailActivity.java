package com.atos.issr.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.atos.issr.R;
import com.atos.issr.custom.ProcessPeriodListAdapter;
import com.atos.issr.modules.rx.model.ws.dtos.types.DetailedRequest;

import static com.atos.issr.utils.Constants.DETAILED_REQUEST_DATA;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailedRequest detail = (DetailedRequest) getIntent().getSerializableExtra(DETAILED_REQUEST_DATA);

        if (detail != null) {
            TextView requestIdEditText = findViewById(R.id.request_id_text_view);
            requestIdEditText.setText(detail.getRequestId());

            TextView requestDurationEditText = findViewById(R.id.duration_text_view);
            requestDurationEditText.setText(detail.getState().getDate());

            TextView requestStatusEditText = findViewById(R.id.request_status_text_view);
            requestStatusEditText.setText(detail.getState().getState());

            ListView processPeriodListView = findViewById(R.id.process_period_list_view);
            processPeriodListView.setAdapter(new ProcessPeriodListAdapter(getApplicationContext(), 0, detail.getListOfPreviousStates()));
        }
    }
}