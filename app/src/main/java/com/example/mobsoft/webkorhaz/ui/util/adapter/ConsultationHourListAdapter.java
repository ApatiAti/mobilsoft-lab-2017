package com.example.mobsoft.webkorhaz.ui.util.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Apati on 2017.04.24..
 */

public class ConsultationHourListAdapter extends ArrayAdapter<ConsultationHourDTO> {

    DateFormat fullDateFormat;
    DateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public ConsultationHourListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ConsultationHourDTO> objects) {
        super(context, resource, objects);
        fullDateFormat = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // TODO optimalize
        //  http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_overview
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_consultationhour_dto, parent, false);

        TextView textViewTypeName = (TextView) rowView.findViewById(R.id.chListViewTypeName);
        TextView textViewInterval = (TextView) rowView.findViewById(R.id.chListViewInterval);
        TextView textViewPatientNumbers = (TextView) rowView.findViewById(R.id.chListPatientNumbers);

        ConsultationHourDTO item = getItem(position);

        String date = fullDateFormat.format(item.getBeginDate()) + " - " + timeFormat.format(item.getEndDate()) ;
        String patientNumbers = item.getAvailable() + " / " + item.getMaxNumberOfPatient();

        textViewTypeName.setText(item.getTpye());
        textViewInterval.setText(date);
        textViewPatientNumbers.setText(patientNumbers);

        return rowView;
    }

}
