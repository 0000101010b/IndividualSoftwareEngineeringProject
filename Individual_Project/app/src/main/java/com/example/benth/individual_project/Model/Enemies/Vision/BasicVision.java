package com.example.benth.individual_project.Model.Enemies.Vision;

import com.example.benth.individual_project.Model.Math.Vector2D;

/**
 * Created by BenTh on 13/11/2016.
 */

public class BasicVision implements Vision {

    public int visionDistance;
    public boolean goRight;
    public boolean goLeft;
    public boolean goUp;
    public boolean goDown;

    public BasicVision(int visionDistance)
    {
        this.visionDistance=visionDistance;
    }

    public boolean isGoRight(){
        return goRight;
    }

    public boolean isGoLeft(){
        return goLeft;
    }

    public boolean isGoUp(){
        return goUp;
    }

    public boolean isGoDown(){
        return goDown;
    }

    @Override
    public void scanArea(Vector2D pos, int[][] grid){

        //set all to true
        goRight=goDown=goUp=goLeft=true;

        for(int i=-visionDistance;i<visionDistance;i++)
        {
            //correct vision within wrap around grid
            int lookX=(pos.x+i)%26;
            if(lookX<0)
                lookX=25;

            int lookY=(pos.y+i)%26;
            if(lookY<0)
                lookY=25;

            //If another smiley in _____(direction) don't go _____(direction)
            if(grid[lookX][pos.y]==3 && i<0) //right
                goRight=false;
            if(grid[lookX][pos.y]==3 && i>0) //left
                goLeft=false;
            if(grid[pos.x][lookY]==3 && i>0) //up
                goUp=false;
            if(grid[pos.x][lookY]==3 && i<0) //down
                goDown=false;


        }

        //prevent basic collision
        if(visionDistance>=1) {
            //if top right corner

            //if top left corner

            //if bottom right

            //if botton left


        }
    }

    @Override
    public String getDescription(){
        return "";
    }
}
