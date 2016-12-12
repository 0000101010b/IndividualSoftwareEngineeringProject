package com.example.benth.individual_project.Model;
import android.content.Context;
import android.widget.FrameLayout;

import com.example.benth.individual_project.MainActivity;
import com.example.benth.individual_project.Model.Grid.Grid;
import com.example.benth.individual_project.Model.Levels.Level;
import com.example.benth.individual_project.Model.ScoreSystem.Score;
import com.example.benth.individual_project.View.MyView;

/**
 * Created by BenTh on 13/11/2016.
 */
public class Game {

        public boolean gameover=false;


        public MyView view;

        public int currentLevel;
        public String[] levels={"level1","level2","level3"};
        public Level level;
        //public FrameLayout container;
        //public Context context;
        // Private constructor prevents instantiation from other classes
        private Game() {
        }
        /**
         * GameHolder is loaded on the first execution of Game.getInstance()
         * or the first access to GameHolder.INSTANCE, not before.
         */
        private static class GameHolder {
            private static final Game INSTANCE = new Game();
        }

        public static Game getInstance() {
            return GameHolder.INSTANCE;
        }

}
