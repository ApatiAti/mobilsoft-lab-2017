package com.example.mobsoft.webkorhaz.ui.util.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.dto.ConsultationHourDto;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Apati on 2017.04.24..
 */

public class ConsultationHourListAdapter extends RecyclerView.Adapter<ConsultationHourListAdapter.ConsultationHourDtoViewHolder> {

    DateFormat fullDateTimeFormat;
    DateFormat timeFormat;
    List<ConsultationHourDto> consultationHourDtoList;



    public class ConsultationHourDtoViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTypeName;
        TextView textViewInterval;
        TextView textViewPatientNumbers;

        public ConsultationHourDtoViewHolder(View itemView) {
            super(itemView);
            textViewTypeName = (TextView) itemView.findViewById(R.id.chListViewTypeName);
            textViewInterval = (TextView) itemView.findViewById(R.id.chListViewInterval);
            textViewPatientNumbers = (TextView) itemView.findViewById(R.id.chListPatientNumbers);
        }
    }

    public ConsultationHourListAdapter(List<ConsultationHourDto> consultationHourDtoList, DateFormat fullDateTimeFormat, DateFormat timeFormat) {
        this.consultationHourDtoList = consultationHourDtoList;
        this.fullDateTimeFormat = fullDateTimeFormat;
        this.timeFormat = timeFormat;
    }

    @Override
    public ConsultationHourDtoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_consultationhour_dto, parent, false);
        return new ConsultationHourDtoViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ConsultationHourDtoViewHolder holder, int position) {
        ConsultationHourDto item = consultationHourDtoList.get(position);

        String date = fullDateTimeFormat.format(item.getBeginDate()) + " - " + timeFormat.format(item.getEndDate()) ;
        String patientNumbers = item.getAvailable() + " / " + item.getMaxNumberOfPatient();

        holder.textViewTypeName.setText(item.getTpye());
        holder.textViewPatientNumbers.setText(patientNumbers);
        holder.textViewInterval.setText(date);
    }

    @Override
    public int getItemCount() {
        return consultationHourDtoList.size();
    }

}
