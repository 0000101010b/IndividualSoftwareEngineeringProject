package com.example.benth.individual_project.Model.Enemies;

import com.example.benth.individual_project.Model.Enemies.Movement.Movement;
import com.example.benth.individual_project.Model.Enemies.Vision.BasicVision;
import com.example.benth.individual_project.Model.Enemies.Vision.Vision;
import com.example.benth.individual_project.Model.Grid.Grid;

/**
 * Created by BenTh on 13/11/2016.
 */

public abstract class Smiley{

    protected static int nextId=0;//Next available ID

    protected String   id;       //unique id
    protected Vision vision;     //vision attributes of smiley
    protected Movement movement; //movement attributes of smiley

    public String getId(){
        return id;
    }
    public Vision getVision(){
        return vision;
    }
    public void setVision(BasicVision basicVision){
        this.vision= basicVision;
    }
    public Movement getMovement(){
        return movement;
    }
    public void setMovement(Movement movement) {
        this.movement= movement;
    }
    public abstract void description();
    public abstract void Update();

}
