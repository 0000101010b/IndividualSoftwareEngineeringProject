package com.example.benth.individual_project.Model.Levels;

import com.example.benth.individual_project.Model.Grid.Grid;
import com.example.benth.individual_project.Model.ScoreSystem.Score;

/**
 * Created by BenTh on 07/12/2016.
 */

public class Level3 extends Level {

    //Grid
    int board_width=26;
    int board_height=26;
    //Enemies
    int initialEnemyNum=18;


    public void Initialize(){
        this.score=new Score();
        this.grid=new Grid(board_width,board_height,initialEnemyNum);

    }

    public void Update(){
        grid.Update();
    }

    public void Render(float[] cellPos){
        grid.Draw(cellPos);
    }
}
