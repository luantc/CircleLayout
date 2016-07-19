package com.luantc.test;

/**
 * Created by luantruong on 7/19/16.
 */
public class ViewModel {
    float percentage;
    boolean needHighlight;

    public ViewModel(int percentage, boolean needHighlight) {
        this.percentage = percentage;
        this.needHighlight = needHighlight;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public boolean isNeedHighlight() {
        return needHighlight;
    }

    public void setNeedHighlight(boolean needHighlight) {
        this.needHighlight = needHighlight;
    }
}
