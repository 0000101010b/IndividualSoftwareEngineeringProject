package com.example.benth.individual_project.View.RenderTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by BenTh on 13/11/2016.
 */

public class TextureAtlas {


    private final String _planeVertexShaderCode =
            "attribute vec4 aPosition;          \n"
                    +   "attribute vec2 aCoord;             \n"
                    +   "varying vec2 vCoord;               \n"
                    +   "uniform mat4 uMVP;                 \n"
                    +   "void main() {                      \n"
                    +   " gl_Position = uMVP * aPosition;   \n"
                    +   " vCoord = aCoord;                  \n"
                    +   "}                                  \n";

    private final String _planeFragmentShaderCode =
            "#ifdef GL_FRAGMENT_PRECISION_HIGH              \n"
                    +   "precision highp float;                         \n"
                    +   "#else                                          \n"
                    +   "precision mediump float;                       \n"
                    +   "#endif                                         \n"
                    +   "varying vec2 vCoord;                           \n"
                    +   "uniform sampler2D uSampler;                    \n"
                    +   "void main() {                                  \n"
                    +   " gl_FragColor = texture2D(uSampler,vCoord);    \n"
                    +   "}                                              \n";

    private int _planeProgram;
    private int _planeAPositionLocation;
    private int _planeACoordinateLocation;
    private int _planeUMVPLocation;
    private int _planeUSamplerLocation;

    private FloatBuffer _planeVFB;
    private FloatBuffer _planeTFB;
    private ShortBuffer _planeISB;

    private int _textureId;

    public TextureAtlas()
    {

    }






    private void initplane() {
        float[] planeVFA = {
                10.000000f,-10.000000f,0.000000f,
                -10.000000f,-10.000000f,0.000000f,
                10.000000f,10.000000f,0.000000f,
                -10.000000f,10.000000f,0.000000f,
        };

        float[] planeTFA = {
                // 1,0, 0,0, 1,1, 0,1
                1,1, 0,1, 1,0, 0,0
        };

        short[] planeISA = {
                2,3,1,
                0,2,1,
        };

        ByteBuffer planeVBB = ByteBuffer.allocateDirect(planeVFA.length * 4);
        planeVBB.order(ByteOrder.nativeOrder());
        _planeVFB = planeVBB.asFloatBuffer();
        _planeVFB.put(planeVFA);
        _planeVFB.position(0);

        ByteBuffer planeTBB = ByteBuffer.allocateDirect(planeTFA.length * 4);
        planeTBB.order(ByteOrder.nativeOrder());
        _planeTFB = planeTBB.asFloatBuffer();
        _planeTFB.put(planeTFA);
        _planeTFB.position(0);

        ByteBuffer planeIBB = ByteBuffer.allocateDirect(planeISA.length * 2);
        planeIBB.order(ByteOrder.nativeOrder());
        _planeISB = planeIBB.asShortBuffer();
        _planeISB.put(planeISA);
        _planeISB.position(0);
    }
    public void SurfaceChanged(float width,float height,float[] _ViewMatrix,float[] _ProjectionMatrix,Context _context){
        initplane();

        float ratio = (float) width / height;
        float zNear = 0.1f;
        float zFar = 1000;
        float fov = 0.95f; // 0.2 to 1.0
        float size = (float) (zNear * Math.tan(fov / 2));

        Matrix.setLookAtM(_ViewMatrix, 0, 0, 0, 50, 0, 0, 0, 0, 1, 0);
        Matrix.frustumM(_ProjectionMatrix, 0, -size, size, -size / ratio, size / ratio, zNear, zFar);
        _planeProgram = loadProgram(_planeVertexShaderCode, _planeFragmentShaderCode);

        _planeAPositionLocation = GLES20.glGetAttribLocation(_planeProgram, "aPosition");
        _planeACoordinateLocation = GLES20.glGetAttribLocation(_planeProgram, "aCoord");
        _planeUMVPLocation = GLES20.glGetUniformLocation(_planeProgram, "uMVP");
        _planeUSamplerLocation = GLES20.glGetUniformLocation(_planeProgram, "uSampler");

        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        _textureId = textures[0];

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, _textureId);
        InputStream is1;
                //= _context.getResources().openRawResource(R.drawable.brick);
        Bitmap img1;
        /*try {
            //img1 = BitmapFactory.decodeStream(is1);
        } finally {
            try {
              //  is1.close();
            //} catch(IOException e) {
                //e.printStackTrace();
            //}
        }*/
        GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST); // GL_LINEAR
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        //GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, img1, 0);
    }


    private int loadShader(int type, String source)  {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        return shader;
    }

    private int loadProgram(String vertexShaderCode, String fragmentShaderCode) {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        return program;
    }





}
