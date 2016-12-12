package com.example.benth.individual_project.View;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.example.benth.individual_project.Model.Game;
import com.example.benth.individual_project.Model.Levels.LevelFactory;
import com.example.benth.individual_project.View.RenderTools.GLText;
import com.example.benth.individual_project.View.RenderTools.Square;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by BenTh on 08/11/2016.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {


    private static final String TAG = "MyGLRenderer";
    private GLText glText;
    private Context context;

    float screenWidth;
    float screenHeight;


    float timeThisRound;
    float timeLastRound;


    private FloatBuffer vertbuffer;
    private Square mSquare;

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];

    public MyGLRenderer(Context context)
    {
        super();
        this.context=context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        timeThisRound = System.nanoTime()/1000000.0f;
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        // Create the GLText
        glText = new GLText(context.getAssets());

        // Load the font from file (set size + padding), creates the texture
        // NOTE: after a successful call to this the font is ready for rendering!
        glText.load("fonts/Roboto-Regular.ttf", 14, 2, 2 );  // Create Font (Height: 14 Pixels / X+Y Padding 2 Pixels)

        // enable texture + alpha blending
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);


        Game.getInstance().currentLevel=2;
        Game.getInstance().level= LevelFactory.getLevel(Game.getInstance().levels[Game.getInstance().currentLevel]);
        Game.getInstance().level.Initialize();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        this.screenHeight=height;
        this.screenWidth=width;
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;


        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
/*
        int clearMask = GLES20.GL_COLOR_BUFFER_BIT;

        GLES20.glClear(clearMask);

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // TEST: render the entire font texture
        glText.drawTexture( (int)screenWidth/2, (int)screenHeight/2, mMVPMatrix);            // Draw the Entire Texture

        // End Text Rendering

        glText.begin( 0.0f, 0.0f, 1.0f, 1.0f, mMVPMatrix );         // Begin Text Rendering (Set Color BLUE)
        glText.draw( "More Lines...", 50, 200 );        // Draw Test String
        glText.draw( "The End.", 50, 200 + glText.getCharHeight(), 180);  // Draw Test String
        glText.end();
        */



        timeThisRound = System.nanoTime()/1000000.0f;  // only capture the time once in seconds
        float deltaTimeThisRound = timeThisRound-timeLastRound;  // to use for certain calculations
        timeLastRound = timeThisRound;


        float[] cellPos = new float[16];
        long time = SystemClock.uptimeMillis() % 4000L;

        Game.getInstance().level.Update();
        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);



     //   // Create a rotation transformation for the triangle
      //  float angle = 0.090f * ((int) time);
        Matrix.setRotateM(mRotationMatrix, 0, 0, 0, 0, -1.0f);

        // Combine the rotation matrix with the projection and camera view
        // Note that the mMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(cellPos, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        Game.getInstance().level.Render(cellPos);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public volatile float mAngle;

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

}
