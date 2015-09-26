package edu.purdue.vieck.budgetapp.CustomObjects;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by mvieck on 9/25/2015.
 */
public class DashboardItem {
    private Drawable image;
    private String label;
    private int color;
    private Intent intent;



    public DashboardItem(Drawable image, String label, int color, Intent intent) {
        this.image = image;
        this.label = label;
        this.color = color;
        this.intent = intent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Drawable getImage() {

        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
