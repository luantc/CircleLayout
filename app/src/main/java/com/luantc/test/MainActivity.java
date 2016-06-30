package com.luantc.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private char mState = 0;

    View pie1, pie2, pie3, pie4, pie5;
     View pie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pie1 = (View) findViewById(R.id.pie1);
        pie2 = (View) findViewById(R.id.pie2);
        pie3 = (View) findViewById(R.id.pie3);
        pie4 = (View) findViewById(R.id.pie4);
        pie5 = (View) findViewById(R.id.pie5);
        pie1.setTag("10");
        pie2.setTag("20");
        pie3.setTag("25");
        pie4.setTag("30");
        pie5.setTag("15");
        pie1.setOnClickListener(this);
        pie4.setOnClickListener(this);
        pie2.setOnClickListener(this);
        pie3.setOnClickListener(this);
        pie5.setOnClickListener(this);
        /*
		 * All code below is NOT required. I've added it just for demonstration
		 * of different layout modes
		 */

        pie = findViewById(R.id.pie);
        final View normal = findViewById(R.id.normal);
        final View normalWithRange = findViewById(R.id.normalWithRange);

        final Button switchBtn = (Button) findViewById(R.id.switchBtn);
        switchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (mState) {
                    case 0:
                        normalWithRange.setVisibility(View.GONE);
                        pie.setVisibility(View.GONE);
                        normal.setVisibility(View.VISIBLE);

                        switchBtn.setText(R.string.normalWidthRange);
                        mState++;
                        break;
                    case 1:
                        normalWithRange.setVisibility(View.VISIBLE);
                        pie.setVisibility(View.GONE);
                        normal.setVisibility(View.GONE);

                        switchBtn.setText(R.string.pie);
                        mState++;
                        break;
                    case 2:
                        normalWithRange.setVisibility(View.GONE);
                        pie.setVisibility(View.VISIBLE);
                        normal.setVisibility(View.GONE);


                        switchBtn.setText(R.string.normal);
                        mState = 0;
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
v.setBackgroundDrawable(getResources().getDrawable(R.drawable.a));
        //pie.requestLayout();
        Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
    }
}
