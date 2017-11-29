package com.example.jensk.lab3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        WordSearcher wordSearcher = new WordSearcher(this);
        layout.addView(wordSearcher); //adds a text bar that can be used to search

        Button button = new Button(this);
        layout.addView(button);

        setContentView(layout);
    }
}
