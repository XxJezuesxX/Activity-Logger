package com.example.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


// shows all the details of all activity(s) (cardviews)
public class RecordsSheet extends AppCompatActivity implements saveFolderDialog.saveFolderDialogListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Button addBtn;

    ArrayList<Record> records = new ArrayList<>();
    private int indexOfEachRecord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table);

        createRecyclerView();
        addEmptyRecord();

        indexOfEachRecord = records.get(records.size() - 1).getIndex();

        // adds a new cell
        addBtn = findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check whether the last Record's credentials are empty or not
                if(records.get(records.size()-1).getTasksName().matches("") || records.get(records.size()-1).getHours() == 0) {
                    Toast.makeText(RecordsSheet.this, "Fill out previous Record's content", Toast.LENGTH_SHORT).show();
                    return;
                }

                int totalHours = 0;
                for(int i = 0; i < records.size(); i++){
                    totalHours = totalHours + (records.get(i).getHours());
                }

                if (totalHours <= 24) {
                    addEmptyRecord();
                    mAdapter.notifyItemInserted(records.size());
                } else {
                    Toast.makeText(RecordsSheet.this, "Total hours have exceeded 24 hours", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // makes recycler view where I put all my card views
    public void createRecyclerView(){
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new TimeTableAdapter(records, RecordsSheet.this);
        recyclerView.setAdapter(mAdapter);
    }

    // adds an empty Record where I can enter all the details
    public void addEmptyRecord(){

        records.add(new Record());
        for(int i = 0; i < records.size(); i++) {
            records.get(records.size() - 1).setIndex(indexOfEachRecord +i);
        }
        records.get(records.size() - 1).setTasksName("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.collectData:
                // add logic to make records with the pie chart
                savePopUpWindow();
                return true;

            case R.id.delete:
                records.clear();
                // possible issue - records arraylist is clearing, but is not deleting records on the screen

                addEmptyRecord();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void savePopUpWindow(){
        saveFolderDialog sFD = new saveFolderDialog();
        sFD.show(getSupportFragmentManager(),"save folder dialogue");
    }

    @Override
    public void assignFolderName(String fileName) {
        Intent intent = new Intent(RecordsSheet.this, AllCharts.class);
        intent.putExtra("fileKaNaam",fileName);
        startActivity(intent);
    }
}
