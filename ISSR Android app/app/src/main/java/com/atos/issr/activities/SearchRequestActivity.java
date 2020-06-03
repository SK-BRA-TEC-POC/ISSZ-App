package com.atos.issr.activities;

import android.os.Bundle;
import android.view.View;

import com.atos.issr.R;

public class SearchRequestActivity extends ActivityWithProgressBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_request);
        initComponents();
    }

    @Override
    View.OnClickListener onSearchButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading();
                // TODO: 3. 6. 2020 call ws
            }
        };
    }
}