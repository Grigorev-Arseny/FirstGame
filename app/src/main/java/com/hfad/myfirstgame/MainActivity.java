package com.hfad.myfirstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

        Map map;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            map = new Map(15 , 20);

            setContentView(new DrawGraphics(this, map));
        }

        public class DrawGraphics extends View {
            Map map;

            private float touchX = 0;
            private float touchY = 0;

            private float dX = 0;
            private float dY = 0;

            float map_width_px;
            float map_height_px;

            public DrawGraphics(Context context, Map map) {
                super(context);
                this.map = map;

                map_width_px = Cell.SIZE * this.map.getWidth();
                map_height_px = Cell.SIZE * this.map.getHeight();
            }

            @Override
            public void onDraw(Canvas canvas) {

                if (this.dX > 0) { this.dX = 0; }
                if (this.dX < -this.map_width_px + canvas.getWidth()) { this.dX = -this.map_width_px + canvas.getWidth(); }


                if (this.dY >  0) { this.dY = 0; }
                if (this.dY < -this.map_height_px + canvas.getHeight()) { this.dY = -this.map_height_px + canvas.getHeight(); }

                drawGrid(canvas);

                for (int i = 0; i < map.getWidth(); i++) {
                    for (int j = 0; j < map.getHeight(); j++) {
                        drawCell(canvas, i, j);
                    }
                }
                //System.out.println("DRAW MAP:" + this.map.getWidth() + " / " + map_width_px + " / " + map_height_px);




                //Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                //canvas.drawBitmap(myBitmap, this.touchX, this.touchY, null);

                /*DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                float height = (float)displayMetrics.heightPixels;
                float width = (float)displayMetrics.widthPixels;*/

                //for(int i; i<)

                /*super.onDraw(canvas);

                float x = 350;
                float y = 250;
                float x1 = 950;
                float y1 = 250;
                float x2 = 950;
                float y2 = 1050;

                //Paint paint = new Paint();
                Paint paint1 = new Paint();
                //paint.setColor(Color.BLUE);
                paint1.setColor(Color.BLACK);
                //canvas.drawColor(Color.RED);
                //canvas.drawLine(x, y, x1, y1, paint);
                //canvas.drawLine(x1, y1, x2, y2, paint);
                //canvas.drawLine(x2, y2, x, y, paint);
                //canvas.drawCircle(x, y, 500, paint);
                canvas.drawRect(200, 200, 700, 700, paint1);*/

                //1. Как работают массивы обектов +
                //2. Заполнить клетки кружками разного цвета
                //3. Карту за пределы
                //4. Обработать свапы
                //5. DrawCell

            }

            private void drawGrid (Canvas canvas) {
                for (int i = 0; i <= this.map.getWidth(); i++) {
                    //System.out.println("DRAW:" + (i * Cell.SIZE) + " - " + map_height_px);
                    canvas.drawLine(i * Cell.SIZE  + dX,  dY, i * Cell.SIZE + dX, map_height_px + dY, new Paint());
                }

                for (int i = 0; i <= this.map.getHeight(); i++) {
                    canvas.drawLine(dX, i * Cell.SIZE + dY, map_width_px + dX, i * Cell.SIZE + dY, new Paint());
                }
            }

            private void drawCell (Canvas canvas, int i, int j) {
                int color = (i + j) % 2; //(int) (Math.random() * 3);
                Paint paint = new Paint();
                if (color == 1) {
                    paint.setColor(Color.GREEN);
                } else {
                    paint.setColor(Color.BLUE);
                }

                canvas.drawCircle(i*Cell.SIZE + Cell.SIZE/2 + dX, j*Cell.SIZE + Cell.SIZE/2 + dY, Cell.SIZE/4, paint);

            }

            public boolean onTouchEvent(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("ACTION_DOWN: " + + event.getX() + " / " + "; " + event.getY());

                    this.touchX = event.getX();
                    this.touchY = event.getY();

                    invalidate();
                }

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    System.out.println("ACTION_MOVE: " + this.dX + " / " + this.dY);

                    this.dX += event.getX() - this.touchX;
                    this.dY += event.getY() - this.touchY;

                    this.touchX = event.getX();
                    this.touchY = event.getY();

                    invalidate();
                }

                return true;
            }
        }
}