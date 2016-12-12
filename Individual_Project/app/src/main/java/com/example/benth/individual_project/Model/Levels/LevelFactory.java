package com.example.benth.individual_project.Model.Levels;

import com.example.benth.individual_project.Model.Game;

/**
 * Created by BenTh on 07/12/2016.
 */

public class LevelFactory {

    public static Level getLevel(String criteria)
    {
        if(criteria.equals("level1"))
        {
            return new Level1();

        }
        else if(criteria.equals("level2"))
        {
            return new Level2();
        }
        else if(criteria.equals("level3"))
        {
            return new Level3();
        }
        return null;
    }
}
