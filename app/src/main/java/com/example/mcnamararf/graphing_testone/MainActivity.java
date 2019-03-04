package com.example.mcnamararf.graphing_testone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class MainActivity extends AppCompatActivity {

    private PointsGraphSeries<DataPoint> series1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     double x,y;
        x=0;

        GraphView graph1 = (GraphView) findViewById(R.id.graph);
        series1 = new PointsGraphSeries<>();


        int numDataPoints = 250;
        for(int i = 0; i<numDataPoints; i++){
            x = x + .1;
            y = Math.sin(x) + 1;
            series1.appendData(new DataPoint(x,y), true, 100);
        }
        graph1.addSeries(series1);

    }
}
