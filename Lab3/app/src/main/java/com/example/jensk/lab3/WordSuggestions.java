package com.example.jensk.lab3;

/**
 * Created by JensK on 2017-11-23.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;

public class WordSuggestions extends View{

    protected String[] names;
    protected WordSearcher wordsearcher;
    protected int textSize = 45, textHeight, textWidth, width, maxSuggestions = 10;
    protected Paint textPaint, background;

    protected FrameLayout.LayoutParams zeroSize = new FrameLayout.LayoutParams(1, 1);

    public WordSuggestions(Context context, final WordSearcher wordsearcher){
        super(context);
        this.wordsearcher = wordsearcher;

        Rect bounds = new Rect();

        textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.parseColor("#000000"));
        textPaint.setAntiAlias(true);
        textPaint.getTextBounds("W", 0, 1, bounds);

        textHeight = bounds.height()+15;
        textWidth = bounds.width();
        width = textWidth * 8;

        background = new Paint();
        background.setColor(Color.parseColor("#ffffff"));

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                int y = (int) e.getY();
                int wordNr = y / textHeight;

                wordsearcher.setText(names[wordNr]);
                return false;
            }
        });
    }

    public void setSuggestions(JSONArray names){
        int length = Math.min(names.length(), maxSuggestions);

        this.names = new String[length];
        for(int i = 0; i < length; i++){
            try {
                this.names[i] = (String) names.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //check if name is valid
        if (names == null || names.length == 0) {
            setLayoutParams(zeroSize);
            return;
        }
        int height = 15 + textHeight * names.length; //size of box depending on lenght of words
        setLayoutParams(new FrameLayout.LayoutParams(width, height));
        canvas.drawRect(0, 0, width, height,background);

        for (int i = 0; i < names.length; i++) {
            canvas.drawText(names[i], 0, textHeight * (i+1), textPaint);
        }
    }

    public void setMaxSuggestions(int maxSuggestions) {
        this.maxSuggestions = maxSuggestions;
    }

    public int getMaxSuggestions() {
        return maxSuggestions;
    }
}