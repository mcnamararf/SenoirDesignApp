package com.example.mcnamararf.graphing_testone;

import android.graphics.Color;
import android.graphics.Point;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
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
    //xyValue array is a global array list
    ArrayList<XYValue> xyValueArray = new ArrayList<>();

    GraphView mScatterPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //Creating scatter plot
        mScatterPlot = findViewById(R.id.graph1);
        createScatterPlot();
        //Displaying sugar value with toast and ontaplistener
        displaySugarValue();

    }

    private void createScatterPlot() {
        Log.d(TAG,"createScatterPlot: Creating Scatter Plot");
        xySeries = new PointsGraphSeries();

        int x =0;

        //Sample BG Values
        int[] yArray =new int[]
                {195,200,204,207,206,202,195,189,183,175,165,153,142,133,127,126,127,127,
                 125,121,118,114,110,106,102,100,102,113,133,158,180,193,195,191,186,181,80,80,80,80,80,80,};

        //Generate add 5 to the x values and add the x and y values to the array list
        for(int i =0; i<42; i++){

            xyValueArray.add(new XYValue(x,yArray[i]));
            x+=5;
        }
        //Append Data to Graph
        for(int i = 0; i<xyValueArray.size(); i++){
            int x2 = xyValueArray.get(i).getX();
            int y  = xyValueArray.get(i).getY();
            xySeries.appendData(new DataPoint(x2,y),true,100);
        }

        //Properties
        xySeries.setShape(PointsGraphSeries.Shape.POINT);
        xySeries.setColor(Color.BLUE);
        xySeries.setSize(10f);

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

        //Axis Labels
        mScatterPlot.getGridLabelRenderer().setHorizontalAxisTitle("Time");
        mScatterPlot.getGridLabelRenderer().setVerticalAxisTitle("mg/dl");

        //Plot
        mScatterPlot.addSeries(xySeries);
    }

    private void displaySugarValue() {
        //Test For Displaying Blood Sugar Value
        xySeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series xySeries, DataPointInterface dataPoint) {
                //Convert data point from double to int
                int yIntValue = (int) dataPoint.getY();
                //Display text view on graph
                TextView sugarTextView = findViewById(R.id.sugarValue);
                determineBGRange(sugarTextView, yIntValue);
                //Toast Value
                Toast.makeText(getApplicationContext(), "BG Value: " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void determineBGRange(TextView sugarTextView, int yIntValue) {
        int highBG = 200;
        int lowBG  = 100;

        //Change to yellow if High BG
        if(yIntValue>=highBG) {
            String yValue = String.valueOf(yIntValue);
            sugarTextView.setText(yValue);
            sugarTextView.setBackgroundColor(Color.YELLOW);
            sugarTextView.setTextColor(Color.BLACK);
        }
        //Change to red if Low BG
        else if (yIntValue<=lowBG){
            String yValue = String.valueOf(yIntValue);
            sugarTextView.setText(yValue);
            sugarTextView.setBackgroundColor(Color.WHITE);
            sugarTextView.setTextColor(Color.RED);
        }
        //Change to black if normal BG
        else{
            String yValue = String.valueOf(yIntValue);
            sugarTextView.setText(yValue);
            sugarTextView.setBackgroundColor(Color.WHITE);
            sugarTextView.setTextColor(Color.BLACK);
        }
    }
}
