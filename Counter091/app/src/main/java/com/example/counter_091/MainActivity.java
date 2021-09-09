package com.example.counter_091;
//Mohit Gupta - 1CR18IS091
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button start, stop;
    TextView count;
    Handler handler;
    int countNO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        count = findViewById(R.id.display);
        handler = new Handler();
        //onClick listener for start button
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                handler.postDelayed(runnable,0);
            }
        });
        //onclick listener for stop button
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clicked on the stop btn", Toast.LENGTH_SHORT).show();
                handler.removeCallbacks(runnable);

            }
        });
    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count.setText(""+countNO);
            countNO++;
            handler.postDelayed(this, 1000);
        }
    };
}