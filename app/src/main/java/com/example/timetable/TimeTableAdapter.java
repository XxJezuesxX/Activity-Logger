package com.example.timetable;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.recordVH> {

    ArrayList<Record> records;
    Context context;

    public TimeTableAdapter(ArrayList<Record> records, Context context){
        this.records = records;
        this.context = context;
    }

    public class recordVH extends RecyclerView.ViewHolder{

        public TextView index;
        public EditText tasksName;
        public EditText totalHours;

        public recordVH(@NonNull View itemView) {
            super(itemView);

            index = itemView.findViewById(R.id.index);
            tasksName = itemView.findViewById(R.id.taskName);
            totalHours = itemView.findViewById(R.id.totalHours);


            tasksName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String strTasksName = tasksName.getText().toString();
                    records.get(records.size()-1).setTasksName(strTasksName);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            totalHours.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    try {
                        int intTotalHours = Integer.parseInt(totalHours.getText().toString());
                        records.get(records.size() - 1).setHours(intTotalHours);
                    }
                    catch(NumberFormatException nfe){
                        System.out.println(nfe);
                    }


                }
            });
        }
    }

     @NonNull

    /**
     * RecyclerView calls this method whenever it needs to create a new ViewHolder.
     * The method creates and initializes the ViewHolder and its associated View, but
     * does not fill in the view's contents—the ViewHolder has not yet been bound to
     * specific data.
     */
    @Override
    public recordVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_row, parent, false);
        recordVH rVH = new recordVH(v);
        return rVH;
    }

    /**
     * RecyclerView calls this method to associate a ViewHolder with data. The method fetches
     * the appropriate data and uses the data to fill in the view holder's layout. For example,
     * if the RecyclerView displays a list of names, the method might find the appropriate name
     * in the list and fill in the view holder's TextView widget.
     */

    @Override
    public void onBindViewHolder(@NonNull recordVH holder, int position) {
        holder.index.setText(String.valueOf(records.get(position).getIndex()));
        holder.tasksName.setText(String.valueOf(records.get(position).getTasksName()));
        holder.totalHours.setText(String.valueOf(records.get(position).getHours()));
    }

    /**
     * RecyclerView calls this method to get the size of the data set. For example, in an address book
     * app, this might be the total number of addresses. RecyclerView uses this to determine when there
     * are no more items that can be displayed.
     */
    @Override
    public int getItemCount() {
        return records.size();
    }


}
