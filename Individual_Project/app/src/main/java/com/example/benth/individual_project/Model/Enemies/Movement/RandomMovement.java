package com.example.benth.individual_project.Model.Enemies.Movement;

import android.util.Log;

import com.example.benth.individual_project.Model.Enemies.Movement.Movement;
import com.example.benth.individual_project.Model.Enemies.Smiley;
import com.example.benth.individual_project.Model.Enemies.Vision.Vision;
import com.example.benth.individual_project.Model.Game;
import com.example.benth.individual_project.Model.Math.Vector2D;

import java.util.Random;

/**
 * Created by BenTh on 13/11/2016.
 */

public class RandomMovement implements Movement {

    public Smiley smiley;

    public RandomMovement(Smiley smiley)
    {
        this.smiley=smiley;
    }
    public Vector2D move(Random r, int x, int y){
        Vision vision=smiley.getVision();
        vision.scanArea(new Vector2D(x,y), Game.getInstance().level.grid.grid);


        boolean upDown = r.nextBoolean();
        int n = r.nextInt(3)-1;
        if(upDown) {

            if(n>0 && vision.isGoLeft())
                x=(x + n) % 26;
            if(n<0 && vision.isGoRight())
                x=(x + n) % 26;

            if(x<0)
                x=25;
        }
        else {//left right

            if(n>0 && vision.isGoUp())
                y=(y + n) % 26;
            if(n<0 && vision.isGoDown())
                y=(y + n) % 26;

            if (y < 0)
                y = 25;
        }
        return new Vector2D(x,y);
    }
    public String getDescription(){
        return "";
    }
}
