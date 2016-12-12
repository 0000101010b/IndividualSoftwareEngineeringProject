package com.example.benth.individual_project.Model.Enemies.SmileyStates;

import com.example.benth.individual_project.Model.Enemies.Smiley;
import com.example.benth.individual_project.Model.Enemies.SmileyStates.SmileyState;

/**
 * Created by BenTh on 13/11/2016.
 */

public class AlertState implements SmileyState {
    private Smiley smiley;
    public float damageMultiplier(){
        return 1;
    }
    public float[] setColor(){
        return new float[]{1.0f , 0.0f,0.0f,1.0f};
    }
}
