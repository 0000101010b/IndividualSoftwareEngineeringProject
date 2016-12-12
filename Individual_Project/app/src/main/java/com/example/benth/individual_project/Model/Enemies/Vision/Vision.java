package com.example.benth.individual_project.Model.Enemies.Vision;

import com.example.benth.individual_project.Model.Math.Vector2D;

/**
 * Created by BenTh on 13/11/2016.
 */

public interface Vision {
    public void scanArea(Vector2D pos,int[][] grid);
    public String getDescription();
    public boolean isGoRight();
    public boolean isGoLeft();
    public boolean isGoUp();
    public boolean isGoDown();

}
