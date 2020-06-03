package com.atos.issr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.atos.issr.R;

/**
 * Created by Jarci on 29. 5. 2020.
 */

abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTAG();
    }

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
                startActivityIfNotEquals(IndividualsActivity.class);
                return true;
            case R.id.company:
                startActivityIfNotEquals(LegalPersonActivity.class);
                return true;
            case R.id.intro:
                startActivityIfNotEquals(SearchRequestActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startActivityIfNotEquals(Class clazz) {
        if (this.getClass() == clazz) {
            Toast.makeText(this, R.string.you_are_in_screen, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), clazz);
            startActivity(intent);
        }
    }

    private void setTAG() {
        TAG = "AtosIssr"+getClass().getSimpleName();
    }
}
