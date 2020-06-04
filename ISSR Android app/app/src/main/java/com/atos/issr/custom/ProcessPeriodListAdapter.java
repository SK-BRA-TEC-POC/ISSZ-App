package com.atos.issr.custom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.atos.issr.R;
import com.atos.issr.modules.rx.model.ws.dtos.types.RequestState;

import java.util.List;

/**
 * Created by Jarci on 3. 6. 2020.
 */

public class ProcessPeriodListAdapter extends ArrayAdapter {
    private List<RequestState> listOfData;

    public ProcessPeriodListAdapter(@NonNull Context context, int resource, @NonNull List<RequestState> listOfData) {
        super(context, resource, listOfData);
        this.listOfData = listOfData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RequestState diffData = listOfData.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.process_period_line_layout, null);

        TextView date = view.findViewById(R.id.process_period_date_text_view);
        TextView status = view.findViewById(R.id.process_period_status_text_view);

        date.setText(diffData.getDate());
        status.setText(diffData.getState());

        return view;
    }
}
