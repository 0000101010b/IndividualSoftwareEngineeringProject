package com.example.benth.individual_project.Model.Levels;

import android.os.Debug;
import android.util.Log;

import com.example.benth.individual_project.Model.Game;
import com.example.benth.individual_project.Model.Grid.Grid;
import com.example.benth.individual_project.Model.ScoreSystem.Score;

import java.io.Console;

/**
 * Created by BenTh on 07/12/2016.
 */

public class Level1 extends Level {

    public boolean gameover;
    public int lives=3;
    //Grid
    int board_width=26;
    int board_height=26;
    //Enemies
    int initialEnemyNum=5;


    public void Initialize(){
        gameover=false;
        this.score=new Score();
        this.grid=new Grid(board_width,board_height,initialEnemyNum);

//        Log.d("Level1", "Made Level 1");
      //  Log.d("hit", ""+score.points);
    }

    public void Update(){

        if(!gameover) {
            grid.Update();
        }

        //if(grid.followersHit())
          //  lives--;

        if(lives==0)
            gameover=true;
    }

    public void Render(float[] cellPos){
        grid.Draw(cellPos);
    }

}
