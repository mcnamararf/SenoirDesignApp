package com.example.mcnamararf.graphing_testone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

public class Graph extends AppCompatActivity {
    private PointsGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        double x, y;
        x = 0;

        GraphView graph1 = (GraphView) findViewById(R.id.graph1);
        series1 = new PointsGraphSeries<>();


        int numDataPoints = 150;
        for (int i = 0; i < numDataPoints; i++) {
            x = x + .1;
            y = (Math.sin(x) + 1);
            series1.appendData(new DataPoint(x, y), true, 150);
        }
        graph1.addSeries(series1);

        series1.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series1, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series1: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
