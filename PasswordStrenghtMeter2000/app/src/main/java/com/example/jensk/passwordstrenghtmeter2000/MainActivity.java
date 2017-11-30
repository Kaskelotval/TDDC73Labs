package com.example.jensk.passwordstrenghtmeter2000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity {

    private LinearLayout.LayoutParams matchAndWrap = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        //Following two lines to use the password strength widget:
        StrengthValidator sv = new StrengthValidator(this);
        sv.setLayoutParams(matchAndWrap);
        layout.addView(sv);

        setContentView(layout);
    }
}

