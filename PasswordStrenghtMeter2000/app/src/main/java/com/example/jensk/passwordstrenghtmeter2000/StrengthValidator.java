package com.example.jensk.passwordstrenghtmeter2000;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static android.text.InputType.TYPE_CLASS_TEXT;


/**
 * Created by JensK on 2017-11-26.
 */

public class StrengthValidator extends LinearLayout{

    private EditText password;
    private ProgressBar progress;
    private TextView status;
    private String yourPass = "Your password is ";
    private List<String> strengthStrings = Arrays.asList("abysmal", "horrible...", "pretty bad tbh.", "ok I guess.", "almost good.", "aces!");
    private int passStrengthLevel;
    protected StrengthValidatorAlgorithm algorithm;
    private LayoutParams matchAndWrap = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT);

    public StrengthValidator(Context context){
        super(context);
        LinearLayout layout = (LinearLayout) inflate(context,R.layout.strenght_validator,null);

        password = layout.findViewById(R.id.password);
        progress = layout.findViewById(R.id.progress);
        status   = layout.findViewById(R.id.status);

        status.setText(yourPass + "non-existent!");
        passStrengthLevel = 0;
        progress.setProgress(0);

        password.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable pass) {
                if(pass.length()==0)
                {
                    status.setText(yourPass + "non-existent!");
                    passStrengthLevel = 0;
                    progress.setProgress(0);
                }
                if(pass.length()>0)
                {
                    passStrengthLevel = (algorithm.Validator(password.getText().toString()));
                    status.setText(yourPass + strengthStrings.get(passStrengthLevel));
                    progress.setProgress(passStrengthLevel);
                }
            }


        });
        layout.setLayoutParams(matchAndWrap);

        algorithm = new StrengthValidatorAlgorithm(){

            @Override
            public int Validator(String password){
                int points = 0;
                if(password.length()<6)
                    return points;
                if(password.length()>10)
                    points++;
                if(password.matches(".*\\d+.*")) //check if password has at least one digit
                    points++;
                if(password.matches(".*[a-zA-Z].*")) //if password has at least one letter (upper or lower)
                    points++;
                if(password.matches(".*[^A-Za-z0-9].*")) //if password has at last one non-letter non-number
                    points++;
                if(!password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase())) //check if the password isnt all upper/lover
                    points++;
                return points;
            }
        };

        addView(layout);
    }
   /* public int Validator(String password){
        int points = 0;
        if(password.length()<6)
            return points;

        if(password.length()>10)
            points++;
        if(password.matches(".*\\d+.*")) //check if password has at least one digit
            points++;
        if(password.matches(".*[a-zA-Z].*")) //if password has at least one letter (upper or lower)
            points++;
        if(password.matches(".*[^A-Za-z0-9].*")) //if password has at last one non-letter non-number
            points++;
        if(!password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase())) //check if the password isnt all upper/lover
            points++;
        return points;
    }*/

    public void setAlgorithm(StrengthValidatorAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
