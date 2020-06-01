package com.atos.upsvar.activities;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.atos.upsvar.R;

/**
 * Created by Jarci on 29. 5. 2020.
 */

abstract class BaseActivity  extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.citizen:
                // TODO: 29. 5. 2020 something
                return true;
            case R.id.company:
                // TODO: 29. 5. 2020 something
                return true;
            case R.id.intro:
                // TODO: 29. 5. 2020 something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
