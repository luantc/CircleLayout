package com.luantc.test;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.luantc.test.animation.ChartAnimator;
import com.luantc.test.animation.Easing;
import com.luantc.test.animation.EasingFunction;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {


    View pie1, pie2, pie3, pie4, pie5;
    CircleLayout pie;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;

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

        pie = (CircleLayout) findViewById(R.id.pie);


        pie.animateY(6000);
        pie.setOnTouchListener(this);

    }

    @Override
    public void onClick(final View v) {
        //v.setBackgroundDrawable(getResources().getDrawable(R.drawable.a));
        //pie.requestLayout();
        Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
        Animation mAni = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        //pie.startAnimation(mAni);

        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        CircleLayout.LayoutParams params = pie.layoutParams(v);
        Log.d("angle", String.valueOf(params.startAngle)+ "  >>> "+String.valueOf(params.endAngle));
        int start = (int) ((params.endAngle + params.startAngle)/2);
        pie.animate().rotation(180-start);

        pie.requestLayout();
        /*
        Random r = new Random();
        int start = (int) ((params.endAngle + params.startAngle)/2);
        int end = (int) (start + r.nextInt(30));
        final RotateAnimation animRotate = new RotateAnimation(params.startAngle, 180 ,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        Log.d("angle", String.valueOf(start) + "  >>> " + String.valueOf(end));

        animRotate.setDuration(200);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);

        pie.startAnimation(animSet);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CircleLayout.LayoutParams p = pie.layoutParams(v);
                Log.d("final", String.valueOf(p.startAngle) + "  >>> " + String.valueOf(p.endAngle));            }
        }, 500);*/
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final float xc = pie.getWidth() / 2;
        final float yc = pie.getHeight() / 2;

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                pie.clearAnimation();
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                break;
            }
            case MotionEvent.ACTION_UP: {
                mPrevAngle = mCurrAngle = 0;
                break;
            }
        }

        return true;
    }

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        pie.startAnimation(rotate);
    }
}
