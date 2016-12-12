package com.example.benth.individual_project;

import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.benth.individual_project.Controller.MyGLSurfaceView;
import com.example.benth.individual_project.Model.Game;
import com.example.benth.individual_project.Model.Levels.Level;
import com.example.benth.individual_project.Model.Levels.LevelFactory;
import com.example.benth.individual_project.View.MyView;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView surface;
    private MyView guiView;
    private FrameLayout container;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        surface = new MyGLSurfaceView(this);
        guiView = new MyView(this);
        Game.getInstance().view=guiView;

        container = new FrameLayout(this);
        container.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(surface);
        container.addView(guiView);

        //Game.getInstance().container=container;
        setContentView(container);



        new Thread(new Runnable() {
            @Override
            public void run() {
                Game.getInstance().view.draw(new Canvas());
                int score=0;
                while (true) {
                   //container.invalidate();
                    Game.getInstance().view.reDraw();

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }

        /*
        new Thread(new Runnable() {
            public void run() {

                while(true) {
                    mHandler.post(new Runnable() {
                        public void run() {
                            if(Game.getInstance().level!=null) {

                                if (Game.getInstance().level.score != null) {
                                    Game.getInstance().level.score.points += 1;
                                }
                            }
                            guiView = new MyView(Game.getInstance().mainActivity);
                            container.invalidate();
                        }
                    });
                }
                }
            }).start();
        }*/


    public void UpdateView(){
      /*  ViewGroup vg = (ViewGroup) findViewById (R.id.activity_main);
        vg.removeAllViews();
        vg.refreshDrawableState();
        setContentView(vg);*/
    }

    public void onPause() {
        super.onPause();
        surface.onPause();

    }

    public void onResume() {
        super.onResume();
        surface.onResume();
    }


}
