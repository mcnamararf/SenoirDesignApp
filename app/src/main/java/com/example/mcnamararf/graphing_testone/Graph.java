package com.example.mcnamararf.graphing_testone;

import android.graphics.Color;
import android.graphics.Point;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Graph extends AppCompatActivity {
    private static final String TAG = "GraphActivity";
    PointsGraphSeries<DataPoint> xySeries;

    GraphView mScatterPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        //Creating scatter plot
        mScatterPlot = (GraphView) findViewById(R.id.graph1);
        createScatterPlot();

        xySeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series1, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series1: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createScatterPlot() {
        Log.d(TAG,"createScatterPlot: Creating Scatter Plot");
        xySeries = new PointsGraphSeries();

        ArrayList<XYValue> xyValueArray = new ArrayList<>();
        int x =0;

        //Sample BG Values
        int[] yArray =new int[]
                {80,80,80,80,80,80,195,200,204,207,206,202,195,189,183,175,165,153,142,133,127,126,127,127,
                 125,121,118,114,110,106,102,100,102,113,133,158,180,193,195,191,186,181};

        //Generate add 5 to the x values and add the x and y values to the array list
        for(int i =0; i<42; i++){
            x+=5;
            xyValueArray.add(new XYValue(x,yArray[i]));
        }
        for(int i = 0; i<xyValueArray.size(); i++){
            int x2 = (int)xyValueArray.get(i).getX();
            int y = (int) xyValueArray.get(i).getY();
            xySeries.appendData(new DataPoint(x2,y),true,40);
        }

        //Properties
        xySeries.setShape(PointsGraphSeries.Shape.POINT);
        xySeries.setColor(Color.BLUE);
        xySeries.setSize(20f);

        //set Scrollable & Scalable
        mScatterPlot.getViewport().setScalable(true);
        mScatterPlot.getViewport().setScalableY(true);
        mScatterPlot.getViewport().setScrollable(true);
        mScatterPlot.getViewport().setScrollableY(true);

        //set manual y bounds
        mScatterPlot.getViewport().setYAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxY(400);
        mScatterPlot.getViewport().setMinY(40);

        //set manual x bounds
        mScatterPlot.getViewport().setXAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxX(180);
        mScatterPlot.getViewport().setMinX(0);

        //Plot
        mScatterPlot.addSeries(xySeries);
    }
}
