package com.example.benth.individual_project.Model.Enemies.Movement;

import com.example.benth.individual_project.Model.Math.Vector2D;

import java.util.Random;

/**
 * Created by BenTh on 24/11/2016.
 */

public class DownwardMovement implements Movement{
    public Vector2D move(Random r, int x, int y){
        boolean upDown = r.nextBoolean();

        int n = r.nextInt(3)-1;

        if(upDown) {
            x = (x+n) % 26;
            if(x<0)
                x=25;
        }
        else {//left right
            y = (y - 1) % 26;
            if (y < 0)
                y = 25;
        }
        return new Vector2D(x,y);
    }
    public String getDescription(){
        return "";
    }
}
