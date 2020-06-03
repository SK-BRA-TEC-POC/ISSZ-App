package com.atos.issr.activities;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.atos.issr.R;

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
                Intent individualActivity = new Intent(getApplicationContext(), IndividualsActivity.class);
                startActivity(individualActivity);
                return true;
            case R.id.company:
                Intent legalPersonActivity = new Intent(getApplicationContext(), LegalPersonActivity.class);
                startActivity(legalPersonActivity);
                return true;
            case R.id.intro:
                // TODO: 29. 5. 2020 something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
