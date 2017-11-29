package com.example.jensk.passwordstrenghtmeter2000;

/**
 * Created by JensK on 2017-11-26.
 */

public class StrengthValidator
{
    public int StrengthValidator(String password){


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
}
