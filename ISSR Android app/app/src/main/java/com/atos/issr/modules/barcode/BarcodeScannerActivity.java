package com.atos.issr.modules.barcode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.atos.issr.R;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Jarci on 20. 12. 2017.
 */

public class BarcodeScannerActivity extends AppCompatActivity {

    private BarcodeScannerFragment barcodeScannerFragment;
    private Button lightButton;
    private boolean lightOn = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        barcodeScannerFragment = (BarcodeScannerFragment) getFragmentManager().findFragmentById(R.id.scanner_fragment);

        lightButton = findViewById(R.id.light_button);
        lightButton.setOnClickListener(lightOnClickListener());
        lightButton.setVisibility(View.VISIBLE);
    }

    private View.OnClickListener lightOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightOn = !lightOn;
                ZBarScannerView scannerView = barcodeScannerFragment.getScannerView();
                scannerView.setFlash(lightOn);

                synchronized (scannerView) {
                    scannerView.notify();
                }

                lightButton.setText(lightOn ? R.string.light_off : R.string.light_on);
                synchronized (lightButton) {
                    lightButton.notify();
                }
            }
        };
    }
}
