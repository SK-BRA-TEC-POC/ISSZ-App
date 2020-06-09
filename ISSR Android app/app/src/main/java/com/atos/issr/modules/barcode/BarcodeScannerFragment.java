package com.atos.issr.modules.barcode;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.atos.issr.R;
import com.atos.issr.activities.SearchRequestActivity;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Jarci 23. 11. 2017.
 */

public class BarcodeScannerFragment extends Fragment implements ZBarScannerView.ResultHandler {
    public static final String TAG = "AtosIssrBarcodeScanner";
    public static String BARCODE_VALUE_NAME = "BARCODE_VALUES";

    private ZBarScannerView scanner;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanner = new ZBarScannerView(getActivity()) {
            @Override
            protected IViewFinder createViewFinderView(final Context context) {
                return new ViewFinderView(context){
                    @Override
                    public synchronized void updateFramingRect() {
                        // setting barcode finder view in scale 2:7
                        int sideMargin = 50;

                        super.updateFramingRect();
                        super.setLaserEnabled(true);
                        super.setSoundEffectsEnabled(true);
                        Display display = getActivity().getWindowManager().getDefaultDisplay();

                        int x = display.getWidth();
                        int y = display.getHeight();

                        int scannerWidth = x - 2 * sideMargin;
                        int scannerHeight = (scannerWidth / 4);
                        int topMargin = (y - scannerHeight) / 2;

                        Rect rect = getFramingRect();
                        rect.set(sideMargin, topMargin, scannerWidth + sideMargin, topMargin + scannerHeight);
                    }
                };
            }
        };
        scanner.setAutoFocus(true);

        List barcodeFormats = new ArrayList();
        barcodeFormats.add(BarcodeFormat.CODE39);
        scanner.setFormats(barcodeFormats);

        Log.i(TAG, "Initialization complete.");
        return scanner;
    }

    @Override
    public void onResume() {
        super.onResume();
        scanner.setResultHandler(this);
        scanner.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scanner.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        if(result.getBarcodeFormat() == BarcodeFormat.CODE39) {
            Log.i(TAG, "Loaded Barcode: '" + result.getContents() +'\'');

            Intent intent = new Intent(getActivity(), SearchRequestActivity.class);
            intent.putExtra(BarcodeScannerFragment.BARCODE_VALUE_NAME, result.getContents());
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), R.string.unknown_barcode, Toast.LENGTH_LONG).show();
            Log.i(TAG, "Unsupported barcode type");
        }
        scanner.resumeCameraPreview(this);
    }

    public ZBarScannerView getScannerView() {
        return scanner;
    }
}
