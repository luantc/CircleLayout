package com.luantc.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PieChart extends AppCompatActivity {
    private TextView textView;
    PieView pieView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        textView = (TextView)findViewById(R.id.textView);
        pieView = (PieView)findViewById(R.id.pie_view);
        button = (Button)findViewById(R.id.pie_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomSet(pieView);
            }
        });
        set(pieView);
    }
    private void randomSet(PieView pieView){
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        int totalNum = 4;

        int totalInt = 0;
        for(int i=0; i<totalNum; i++){
            int ranInt = (int)(Math.random()*10)+1;
            intList.add(ranInt);
            totalInt += ranInt;
        }
        for(int i=0; i<totalNum; i++){
            pieHelperArrayList.add(new PieHelper(100f*intList.get(i)/totalInt));
        }

        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setDate(pieHelperArrayList);
    }

    private void set(PieView pieView){
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(26, Color.BLACK));
        pieHelperArrayList.add(new PieHelper(30));
        pieHelperArrayList.add(new PieHelper(12));
        pieHelperArrayList.add(new PieHelper(32));

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if(index != PieView.NO_SELECTED_INDEX) {
                    textView.setText(index + " selected");
                }else{
                    textView.setText("No selected pie");
                }
            }
        });
        pieView.selectedPie(2);
    }

}
