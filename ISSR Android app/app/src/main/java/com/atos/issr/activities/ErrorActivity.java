package com.atos.issr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.atos.issr.R;

public class ErrorActivity extends AppCompatActivity {

    public static final String ERROR_MESSAGE_DATA = "MESSAGE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        String message = getIntent().getStringExtra(ERROR_MESSAGE_DATA);

        TextView errorTextView = findViewById(R.id.error_text_view);
        errorTextView.setText(message == null ? "" : message);

    }
}