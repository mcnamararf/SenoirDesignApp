package com.example.mcnamararf.graphing_testone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.jjoe64.graphview.series.DataPoint;

import com.jjoe64.graphview.series.PointsGraphSeries;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class MainActivity extends AppCompatActivity {
    
    private Button button;

    private String deviceNamePrefix = "HC-05";
    private byte[] serial_buffer = new byte [50];
    private byte[] input_buffer = new byte[4];
    private double input_data;
    private BluetoothSerial bluetoothSerial;
    private double glucoseConcentration;


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

        bluetoothSerial = new BluetoothSerial(
                this, new BluetoothSerial.MessageHandler() {
            public int read(int bufferSize, byte[] buffer) {
                return doRead(bufferSize, buffer);
            }
        }, deviceNamePrefix);

        if(!bluetoothSerial.connected) {
            bluetoothSerial.connect();
        }

    }

    public void openGraphActivity() {
        Intent intent = new Intent(this, Graph.class);
        startActivity(intent);
    }

    public int doRead(int bufferSize, byte[] buffer) {
        int i;
        for(i = 0; i < bufferSize; i++) {
            if(buffer[i] == 'R' && buffer[i+1] == 'O' && buffer[i+2] == 'B')
            {
                for(int j = 0; j < 4; j++)
                {
                    input_buffer[j] = buffer[i+3+j];
                }
            }
        }

        //input_data = toDouble(input_buffer);
        //String input_string = Double.toString(input_data);
        double input_double = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        glucoseConcentration = convertToGlucose(input_double);
        return i;
    }

    public double convertToGlucose(double voltage_in) {
        double glucose = 0;
        glucose = (((-8 * Math.pow(10, -5)) * voltage_in) + 1.2662);
        return glucose;
    }

}
