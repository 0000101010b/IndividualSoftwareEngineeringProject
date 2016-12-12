package com.example.benth.individual_project.Model.Grid;

import android.opengl.Matrix;
import android.util.Log;

import com.example.benth.individual_project.Model.Enemies.NormalISmiley;
import com.example.benth.individual_project.Model.Game;
import com.example.benth.individual_project.Model.Player.Leader;
import com.example.benth.individual_project.View.MyView;
import com.example.benth.individual_project.View.RenderTools.Square;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by BenTh on 08/11/2016.
 */

public class Grid {

    public int[][] grid;    //grid states
    public Square[][] cells;//sprites
    float colorCell[] = { 0.7f, 0.7f, 0.7f, 1.0f };

    public ArrayList<NormalISmiley> smilies;
    public Leader leader;

    //
    float colorLeader[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    float colorFollower[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    float colorSmilies[]={ 1.0f, 1.0f, 0.0f, 1.0f };


    public boolean followersHit=false;

    public Grid(int width, int height,int enemyNum)
    {
        grid =new int[width][height];
        leader=new Leader();
        smilies=new ArrayList<NormalISmiley>();
        cells=new Square[width][height];

        //Initialize the squares for the draw call
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                cells[i][j]=new Square();
            }
        }
        //Create initial wave of enemies
        CreateEnemyWave(enemyNum);
    }


    public void CreateEnemyWave(int number){
        smilies.clear();

        Random r=new Random();
        for(int i=0;i<number;i++) {
            int x=r.nextInt(grid.length);
            int y=r.nextInt(grid[0].length);
            smilies.add(new NormalISmiley(x,y,this));
        }
    }

    public void Update(){


        //Update Leader position
        leader.LeaderMovement(grid.length, grid[0].length);

        //Set default cell colour and empty state for grid
        for(int i=0;i<cells.length;i++)
        {
            for(int j=0;j<cells[0].length;j++)
            {
                cells[i][j].color=colorCell;
                grid[i][j]=0;
            }
        }
        //Set leader cell colour and pos on grid
        cells[leader.x][leader.y].color=colorLeader;
        grid[leader.x][leader.y]=1;

        //Set followers cell positon color and environment position
        for(int i=0;i<leader.followers.size();i++)
        {
            cells[leader.followers.get(i).x][leader.followers.get(i).y].color=colorFollower;
            grid[leader.followers.get(i).x][leader.followers.get(i).y]=2;
        }

        for(int i=0;i<smilies.size();i++)
        {
            if(smilies.get(i).x==leader.x&&smilies.get(i).y==leader.y) {
                smilies.remove(i);
                Game.getInstance().level.score.hit();
                //Log.d("hit", "Update: Kill");
            }
            /*
            for(int j=0;i<leader.followers.size();j++)
            {
                if(smilies.get(i).x== leader.followers.get(j).x && smilies.get(i).y==leader.followers.get(j).y)
                {
                    Game.getInstance().level.
                }
            }*/

            cells[smilies.get(i).x][smilies.get(i).y].color=colorSmilies;
            grid[smilies.get(i).x][smilies.get(i).y]=3;

            smilies.get(i).Update();
        }


    }
    public void Draw(float[] mMVPMatrix){
        //cells=new Square[5][20];
        //cells[0][1].draw(mMVPMatrix);
        //Square s=new Square();
        //s.draw(mMVPMatrix);
        float[] cellPos = new float[16];

        //scale
        Matrix.scaleM(mMVPMatrix,0,mMVPMatrix,0,.1f,.1f,1f);

        //float[] mRotationMatrix = new float[16];
        //Matrix.multiplyMM(cellPos, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        //cells[0][0].draw(mMVPMatrix);

        for(float i = -grid.length/2; i< grid.length/2; i++) {

           for (float j = -grid[0].length/2; j < grid[0].length/2; j++) {
               Matrix.translateM(cellPos, 0, mMVPMatrix, 0, i, j, 1.5f);
               cells[(int)i+ grid.length/2][(int)j+ grid.length/2].draw(cellPos);
           }
       }
    }
}
