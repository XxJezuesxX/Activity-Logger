package com.example.timetable;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllCharts extends AppCompatActivity implements ShowGraph.showGraphListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<logsRow> logs;
    String getStrFileName;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_sheet);

        logs = new ArrayList<>();
        createRecyclerView();
        addLog();

    }

    public void createRecyclerView(){
        recyclerView = findViewById(R.id.log_sheet);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new LogAdapter(logs, AllCharts.this);
        recyclerView.setAdapter(mAdapter);
    }

    public void addLog(){

        try {
            getStrFileName = getIntent().getStringExtra("fileKaNaam");

            if (!getStrFileName.equals(" ")) {
                logs.add(new logsRow(getStrFileName));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void getDataPopUpWindow(){
        ShowGraph SG = new ShowGraph();
        SG.show(getSupportFragmentManager(), "Show the graph");

    }


    public void setGraphPopUpWindow(LinearLayout lL){
        lL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataPopUpWindow();
            }
        });
    }

    @Override
    public void assignGraph() { getDataPopUpWindow();}
}
