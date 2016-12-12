package com.example.benth.individual_project;

import com.example.benth.individual_project.Model.Game;
import com.example.benth.individual_project.Model.Levels.Level;
import com.example.benth.individual_project.Model.Levels.Level1;
import com.example.benth.individual_project.Model.Levels.LevelFactory;
import com.example.benth.individual_project.Model.ScoreSystem.Score;
import com.example.benth.individual_project.View.MyGLRenderer;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void scoreTest() throws Exception {
        Score score= new Score();
        assertEquals(score.points, 0);
        score.hit();
        assertEquals(score.points,100);
        score.hit2();
        assertEquals(score.points,300);
    }

    @Test
    public void levelFactoryTest() throws Exception {
        /* opengl native method code required

        Game.getInstance().currentLevel=0;
        Game.getInstance().level= LevelFactory.getLevel(Game.getInstance().levels[Game.getInstance().currentLevel]);
        Game.getInstance().level.Initialize();
        assertEquals(Game.getInstance().level != null,true);
        assertEquals(Game.getInstance().level.score.points,0);
        */
    }

    @Test
    public void levelFactoryTest2() throws Exception {

        Game.getInstance().currentLevel=0;
        Game.getInstance().level= LevelFactory.getLevel(Game.getInstance().levels[Game.getInstance().currentLevel]);
        //Game.getInstance().level.Initialize();
        assertEquals(Game.getInstance().level==null,false);
    }
}