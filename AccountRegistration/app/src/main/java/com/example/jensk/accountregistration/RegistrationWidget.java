package com.example.jensk.accountregistration;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;


/**
 * Created by JensK on 2017-11-30.
 */

public class RegistrationWidget extends LinearLayout{

    private final Context context;
    private ArrayList<View> viewList = new ArrayList<View>();

    private EditText textBox;
    private LayoutParams matchAndWrap = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT);
    private LayoutParams matchAndWrapAndWeight = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
            1.0f);

    public RegistrationWidget(Context con){
        super(con);
        this.context = con;

        //Create the linear layout of the page
        this.setOrientation(LinearLayout.VERTICAL);
        this.setLayoutParams(matchAndWrap);
        //create basic rows

        Button sendButton = new Button(context);
        sendButton.setText("Register");
        sendButton.setLayoutParams(matchAndWrapAndWeight);
        sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate();

            }
        });
        this.addView(sendButton);

    }

    public LinearLayout createNewRow(String text, EditText et) {

        EditText editText = et;
        LinearLayout tempLayout = new LinearLayout(this.context);
        tempLayout.setOrientation(LinearLayout.HORIZONTAL);

        editText.setHint(text);
        editText.setLayoutParams(matchAndWrap);
        tempLayout.addView(editText);
        Log.d("new row","Added "+ editText + " to viewlist");

        viewList.add(editText);
        return tempLayout;
    }

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASS_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!$%@#£€*?&]{8,}$";
    private static final String REQUIRED_MSG = "required";
    private static final String PASS_MSG = "invalid password";
    private static final String EMAIL_MSG = "invalid email";


    private boolean Validate()
    {
        boolean allValid = true;

        for (int i =0; i<viewList.size();i++)
        {
            Log.d("VALIDATE", " number " + i);
           allValid = validateChild(viewList.get(i));
           if(!allValid)
               Log.d("allValid", "" + allValid);
               //return allValid;
        }
        return allValid;

    }

    private boolean validateChild(View v){
        if(v instanceof EditText)
        {
            if(((EditText) v).getInputType() == (TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_EMAIL_ADDRESS))
                return isEmailAddress(((EditText) v),true);

            if(((EditText) v).getInputType() == (TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD))
                return isPassword(((EditText) v),true);
        }
        return true;
    }


    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }
    public boolean isPassword(EditText editText, boolean required) {
        return isValid(editText, PASS_REGEX, PASS_MSG, required);
    }


    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

}
