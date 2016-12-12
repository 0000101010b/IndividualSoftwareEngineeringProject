package com.example.benth.individual_project.Model.ScoreSystem;

import android.graphics.Canvas;

import com.example.benth.individual_project.Model.Game;

/**
 * Created by BenTh on 13/11/2016.
 */

public class Score {
    public int points;
    public Score(){

        points=0;
    }
    public void hit()
    {
        this.points+=100;
    }
    public void hit2(){
        this.points+=200;
    }
}
