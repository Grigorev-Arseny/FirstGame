package com.hfad.myfirstgame;

//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.os.Bundle;
import android.util.DisplayMetrics;
//import android.view.MotionEvent;
//import android.view.View;

//import androidx.appcompat.app.AppCompatActivity;

//import com.hfad.myfirstgame.Cell;

public class Map {
    private int width;
    private int height;
    private Cell[][] map;

    public Map (int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Cell[width][height];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Cell[][] getMap() { return this.map; }
}
