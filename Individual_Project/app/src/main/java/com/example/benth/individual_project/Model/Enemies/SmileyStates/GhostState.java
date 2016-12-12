package com.example.benth.individual_project.Model.Enemies.SmileyStates;

import com.example.benth.individual_project.Model.Enemies.SmileyStates.SmileyState;

/**
 * Created by BenTh on 13/11/2016.
 */

public class GhostState implements SmileyState {

    public float damageMultiplier(){
        return 0.0f;
    }
    public float[] setColor(){
        return new float[]{0.603922f , 0.803922f,0.196078f,1.0f};
    }
}
