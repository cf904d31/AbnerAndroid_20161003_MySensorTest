package iii.org.tw.mysensortest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2016/10/3.
 */
public class MyView extends View {
    private float viewW,viewH,ballX,ballY,ballR;
    private Paint drawVLine,drawHLine,drawBall;
    private boolean isInit;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);
    }

    private void init() {

        viewW = getWidth(); viewH = getHeight();
        ballX = viewW / 2; ballY = viewH/2;
        ballR = 40;


        drawHLine = new Paint();
        drawVLine = new Paint();
        drawBall = new Paint();

        drawBall.setColor(Color.YELLOW);
        drawVLine.setColor(Color.BLUE);
        drawHLine.setColor(Color.WHITE);

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit) init();

        canvas.drawCircle(ballX,ballY,ballR, drawBall);

        canvas.drawLine(viewW/2,0,viewW/2,viewH, drawVLine);
        canvas.drawLine(0,viewH/2,viewW,viewH/2, drawHLine);
    }

    void setXY (float x, float y) {
        ballX = x;
        ballY = y;
        invalidate();
    }

    float getScreenW() {
        return viewW;
    }
    float getScreenH() {
        return viewH;
    }
}
