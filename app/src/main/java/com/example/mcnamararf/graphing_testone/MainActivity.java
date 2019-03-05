package com.example.mcnamararf.graphing_testone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.jjoe64.graphview.series.DataPoint;

import com.jjoe64.graphview.series.PointsGraphSeries;



public class MainActivity extends AppCompatActivity {
    
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.graph_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraphActivity();
            }
        });

    }

    public void openGraphActivity() {
        Intent intent = new Intent(this, Graph.class);
        startActivity(intent);
    }
}
