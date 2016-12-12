package com.example.benth.individual_project.Model.Levels;

import com.example.benth.individual_project.Model.Grid.Grid;
import com.example.benth.individual_project.Model.ScoreSystem.Score;

/**
 * Created by BenTh on 07/12/2016.
 */

public abstract class Level {

    public Grid grid;
    public Score score;

    public void Initialize(){};
    public void Update(){};
    public void Render(float[] cellPos){};
}
