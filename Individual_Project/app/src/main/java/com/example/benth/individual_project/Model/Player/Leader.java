package com.example.benth.individual_project.Model.Player;


import com.example.benth.individual_project.Model.Player.Follower;
import com.example.benth.individual_project.Model.Math.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenTh on 08/11/2016.
 */

public class Leader {

    public int x,y;
    public int direction;
    public List<Follower> followers;

    public Leader() {
        y = x = 10;
        direction = 0;
        followers = new ArrayList<Follower>();
        for (int i = 1; i < 10; i++) {
            followers.add(new Follower(x-i, y));
        }
    }

    public void LeaderMovement(int height,int width){

        int lastX=x;
        int lastY=y;

        switch(direction) {
            case 0://right
            {
                x++;
                x=x%width;
                break;
            }
            case 1://left
            {
                x--;
                if(x<0)
                    x=width-1;
                x=x%width;
                break;
            }
            case 2://up
            {
                y++;
                y=y%height;
                break;
            }
            case 3:
            {
                y--;
                if(y<0)
                    y=height-1;
                y=y%height;
                break;
            }
        }
        //move followers
        int tempX=followers.get(0).x;
        int tempY=followers.get(0).y;
        followers.get(0).x=lastX;
        followers.get(0).y=lastY;
        lastX=tempX;
        lastY=tempY;

        for (int i=1;i<followers.size();i++)
        {
            tempX=followers.get(i).x;
            tempY=followers.get(i).y;
            followers.get(i).x=lastX;
            followers.get(i).y=lastY;
            lastX=tempX;
            lastY=tempY;
        }

    }

    public void MovementControls()
    {

    }


}
