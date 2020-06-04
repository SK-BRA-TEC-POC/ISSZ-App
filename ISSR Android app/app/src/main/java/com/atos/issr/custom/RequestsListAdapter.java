package com.atos.issr.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.atos.issr.R;
import com.atos.issr.activities.DetailActivity;
import com.atos.issr.modules.rx.model.ws.dtos.types.DetailedRequest;

import java.util.List;

import static com.atos.issr.utils.Constants.DETAILED_REQUEST_DATA;

/**
 * Created by Jarci on 3. 6. 2020.
 */

public class RequestsListAdapter extends ArrayAdapter {
    private List<DetailedRequest> listOfData;
    private Activity activity;

    public RequestsListAdapter(@NonNull Context context, int resource, @NonNull List<DetailedRequest> listOfData, Activity activity) {
        super(context, resource, listOfData);
        this.listOfData = listOfData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DetailedRequest requestDetail = listOfData.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.display_request_line_layout, null);

        TextView requestIdTextView = view.findViewById(R.id.request_id_text_view);
        TextView requestTypeTextView = view.findViewById(R.id.request_type_text_view);
        TextView requestStatusTextView = view.findViewById(R.id.request_status_text_view);
        TextView requestDetailTextView = view.findViewById(R.id.request_description_text_view);

        requestIdTextView.setText(requestDetail.getRequestId());
        requestTypeTextView.setText(requestDetail.getType());
        requestStatusTextView.setText(requestDetail.getState().getState());
        requestDetailTextView.setText(requestDetail.getDescription());

        view.setOnClickListener(requestOnClickListener(position));
        return view;
    }

    private View.OnClickListener requestOnClickListener(int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra(DETAILED_REQUEST_DATA, listOfData.get(position));
                activity.startActivity(intent);
            }
        };
    }
}
