package com.srsoft.saptagirimess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srsoft.saptagirimess.R;
import com.srsoft.saptagirimess.databinding.AttendanceItemBinding;
import com.srsoft.saptagirimess.ui.modal.Attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    private List<Attendance> items;

    private String userId;
    private Context context;

    public AttendanceAdapter(List<Attendance> items, Context context, String userId) {
        this.items = items;
        this.context = context;
        this.userId = userId;
    }

    public AttendanceAdapter() {
    }

    @NonNull
    @Override
    public AttendanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AttendanceItemBinding binding = AttendanceItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AttendanceAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.MyViewHolder holder, int position) {
        Attendance item = items.get(position);
        holder.binding.date.setText(convertDateFormat(item.getDate()));
        if (item.getDinner().contains(userId)) {
            holder.binding.dinner.setText("Present");
            holder.binding.dinner.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.binding.dinner.setText("Absent");
            holder.binding.dinner.setTextColor(context.getResources().getColor(R.color.redlight));
        }
        if (item.getLunch().contains(userId)) {
            holder.binding.lunch.setText("Present");
            holder.binding.lunch.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.binding.lunch.setText("Absent");
            holder.binding.lunch.setTextColor(context.getResources().getColor(R.color.redlight));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private AttendanceItemBinding binding;

        public MyViewHolder(AttendanceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static String convertDateFormat(String inputDate) {
        // Define input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the input date string
        Date date;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            // Handle parsing exception
            return null;
        }
        return outputFormat.format(date);
    }

}
