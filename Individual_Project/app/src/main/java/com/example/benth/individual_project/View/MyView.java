package com.example.benth.individual_project.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.benth.individual_project.Model.Game;

/**
 * Created by BenTh on 08/12/2016.
 */

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);


        Typeface plain = Typeface.createFromAsset(getContext().getAssets(),"fonts/Starlight.ttf");
        Typeface Level = Typeface.createFromAsset(getContext().getAssets(), "fonts/F.ttf");
        Typeface Lives = Typeface.createFromAsset(getContext().getAssets(), "fonts/ArcadeClassic.ttf");

        // Typeface bold = Typeface.create(plain, Typeface.DEFAULT_BOLD);

        Paint textPaint = new Paint();
        textPaint.setARGB(255, 20, 20, 20);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(plain);
        textPaint.setTextSize(225);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

        if(Game.getInstance().gameover)
            canvas.drawText("Game Over", xPos, yPos, textPaint);
        textPaint.setARGB(255, 255, 255, 255);
        textPaint.setTextSize(175);
        textPaint.setTypeface(Level);
        canvas.drawText("            "+Game.getInstance().levels[Game.getInstance().currentLevel]+"             ",xPos,125,textPaint);
        //textPaint.setTypeface(plain);
        textPaint.setTextSize(75);
        textPaint.setTypeface(Lives);
        int count=0;
        count++;
        canvas.drawText("SCORE: "+ Game.getInstance().level.score.points,300,250,textPaint);
        canvas.drawText("Lives:  x3 ",800,250,textPaint);

    }
    public void reDraw() {
        this.invalidate();
    }


}
