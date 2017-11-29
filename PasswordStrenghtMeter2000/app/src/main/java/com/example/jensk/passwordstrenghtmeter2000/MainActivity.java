package com.example.jensk.passwordstrenghtmeter2000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int passWordStrenghtLevel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText password = findViewById(R.id.password);
        final ProgressBar progress = findViewById(R.id.progress);
        final TextView status = findViewById(R.id.status);

        final String yourPass = "Your password is ";
        final List<String> strengthStrings = Arrays.asList("non-existent!", "horrible...", "pretty bad tbh.", "ok I guess.", "almost good.", "aces!");
        status.setText(yourPass + strengthStrings.get(0));
        final  StrengthValidator sv = new StrengthValidator();

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable pass) {
                if(pass.length()==0)
                {
                    status.setText(yourPass + strengthStrings.get(0));
                    passWordStrenghtLevel = 0;
                    progress.setProgress(0);
                }
                if(pass.length()>1)
                {
                    passWordStrenghtLevel = (sv.StrengthValidator(password.getText().toString()));
                    status.setText(yourPass + strengthStrings.get(passWordStrenghtLevel));
                    progress.setProgress(passWordStrenghtLevel);
                }
            }

            
        });

    }
}

