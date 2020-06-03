package com.atos.issr.activities;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.atos.issr.R;

/**
 * Created by Jarci on 3. 6. 2020.
 */

abstract class ActivityWithProgressBar extends BaseActivity {
    protected ProgressBar progressBar;
    protected Button searchButton;


    protected void initComponents() {
        progressBar = findViewById(R.id.progress_bar);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(onSearchButtonClick());
    }

    abstract View.OnClickListener onSearchButtonClick();

    protected void startLoading() {
        Log.d(TAG, "Starting loading");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (progressBar != null) {
            progressBar.bringToFront();
            progressBar.setVisibility(View.VISIBLE);
            Log.d(TAG, "Loading started");
        }
    }

    protected void stopLoading() {
        Log.i(TAG, "Stopping loading");
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.d(TAG, "Loading stopped");
        }
    }
}
