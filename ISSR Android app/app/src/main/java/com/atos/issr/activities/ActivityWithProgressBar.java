package com.atos.issr.activities;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.atos.issr.R;

/**
 * Created by Jarci on 3. 6. 2020.
 */

abstract class ActivityWithProgressBar extends BaseActivity {
    protected ProgressBar progressBar;
    protected Button searchButton;
    private ConstraintLayout rootLayout;

    protected void initComponents() {
        progressBar = findViewById(R.id.progress_bar);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(onSearchButtonClick());
        rootLayout = findViewById(R.id.root_frame);
        rootLayout.setOnTouchListener(touchListener());
    }


    private View.OnTouchListener touchListener() {
        Activity activity = this;
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View focused = getCurrentFocus();
                if (focused instanceof EditText) {
                    focused.clearFocus();
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(focused.getWindowToken(), 0);
                }
                return true;
            }
        };
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
