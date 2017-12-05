package com.example.jensk.accountregistration;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

public class MainActivity extends AppCompatActivity {
    private LinearLayout.LayoutParams matchAndWrap = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        RegistrationWidget rw = new RegistrationWidget(this);

        //1: Define the View, 2:Use view.setInputType(type), 3: Use the createNewRow function to create row with the view
        EditText emailtext = new EditText(this);
        emailtext.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_EMAIL_ADDRESS); //If this.type == typetextvariationetc
        View email = rw.createNewRow("Email", emailtext);
        email.setLayoutParams(matchAndWrap);
        layout.addView(email);

        EditText passwordText = new EditText(this);
        passwordText.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
        View password = rw.createNewRow("Password", passwordText);
        password.setLayoutParams(matchAndWrap);
        layout.addView(password);

        EditText infoText = new EditText(this);
        View info = rw.createNewRow("info", infoText);
        info.setLayoutParams(matchAndWrap);
        layout.addView(info);

        rw.setLayoutParams(matchAndWrap);
        layout.addView(rw);
        setContentView(layout);
    }
}
