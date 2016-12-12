package com.example.benth.individual_project.Model.Enemies;

import com.example.benth.individual_project.Model.Enemies.Movement.Movement;
import com.example.benth.individual_project.Model.Enemies.Movement.RandomMovement;
import com.example.benth.individual_project.Model.Enemies.SmileyStates.AlertState;
import com.example.benth.individual_project.Model.Enemies.SmileyStates.GhostState;
import com.example.benth.individual_project.Model.Enemies.SmileyStates.SmileyState;
import com.example.benth.individual_project.Model.Enemies.Vision.BasicVision;
import com.example.benth.individual_project.Model.Enemies.Vision.Vision;
import com.example.benth.individual_project.Model.Grid.Grid;
import com.example.benth.individual_project.Model.Math.Vector2D;

import java.util.Random;

/**
 * Created by BenTh on 09/11/2016.
 */
public class NormalISmiley extends Smiley {

    public Grid grid;
    //The States of the smiley
    SmileyState GhostState;
    SmileyState AlertState;

    SmileyState state=AlertState;

    protected static int nextId=0;//Next available ID

    protected  String id;       //unique id
    protected Vision vision;    //vision attributes of smiley
    protected Movement movement;//movement attributes of smiley

    public int x;
    public int y;

    public NormalISmiley(int x, int y,Grid grid){
        //Set grid
        this.grid=grid;
        //set position
        this.x=x;
        this.y=y;
        //set vision
        this.vision=new BasicVision(2);
        //set movement
        this.movement=new RandomMovement(this);
        //this.movement= new RightMovement();
        //create states
        this.AlertState= new AlertState();
        this.GhostState= new GhostState();
        //setting current state
        this.state=this.AlertState;
    }

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
    public void setMovement(RandomMovement randomMovement) {
        if(this.state!=GhostState)
            this.movement= randomMovement;
    }
    public void description(){}

    @Override
    public void Update(){
        //vision.scanArea();
        Random r= new Random();
        //RandomMovement movement=this.movement;
        Vector2D pos=movement.move(r,x,y);
        x=pos.x;
        y=pos.y;
    }
}
