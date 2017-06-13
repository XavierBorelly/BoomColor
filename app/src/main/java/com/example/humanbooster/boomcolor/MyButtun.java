package com.example.humanbooster.boomcolor;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Button personnel permettant en plus du fonctionnement classique du bouton de savoir si il est colorié et le nombre de fois ou le bouton a été presser
 */
public class MyButtun extends android.support.v7.widget.AppCompatButton {

    private boolean isColorer = false;
    private int score = 0;

    public MyButtun(Context context) {
        super(context);
    }

    public MyButtun(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButtun(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isColorer() {
        return isColorer;
    }

    public void setColorer(boolean colorer) {
        isColorer = colorer;
    }

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score ++;
    }
}
