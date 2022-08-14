package com.example.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogVH> {

    ArrayList<logsRow> logs;
    Context mContext;



    public LogAdapter(ArrayList<logsRow> logs, Context context) {
        this.logs = logs;
        mContext = context;
    }

    public class LogVH extends RecyclerView.ViewHolder{

        ImageView graphImage;
        TextView dayAndDate;
        LinearLayout fileName;

        public LogVH(@NonNull View itemView) {
            super(itemView);
            graphImage = itemView.findViewById(R.id.graphImage);
            dayAndDate = itemView.findViewById(R.id.getFileName);
            fileName = itemView.findViewById(R.id.fileKaPata);
        }
    }

    @NonNull
    @Override
    public LogVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.logs_row, parent, false);
        LogVH lVH = new LogVH(v);
        return lVH;
    }

    @Override
    public void onBindViewHolder(@NonNull LogVH holder, int position) {


        holder.dayAndDate.setText(String.valueOf(logs.get(position).getFilesName()));
        holder.graphImage.setImageResource(logs.get(position).getLogsRowsIcon());

        holder.fileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof AllCharts) {
                    ((AllCharts)mContext).setGraphPopUpWindow(holder.fileName);

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return logs.size();
    }






}
