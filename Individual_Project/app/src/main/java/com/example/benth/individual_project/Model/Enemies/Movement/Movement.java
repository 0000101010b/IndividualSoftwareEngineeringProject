package com.example.benth.individual_project.Model.Enemies.Movement;

import com.example.benth.individual_project.Model.Math.Vector2D;

import java.util.Random;

/**
 * Created by BenTh on 13/11/2016.
 */
public interface Movement {
    public Vector2D move(Random r, int x, int y);
    public String getDescription();
}
