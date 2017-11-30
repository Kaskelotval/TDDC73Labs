package com.example.jensk.lab3;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import org.json.JSONArray;

import static android.text.InputType.TYPE_CLASS_TEXT;

public class WordSearcher extends LinearLayout {
    //definitions
    private EditText editText;
    private WordSuggestions suggestions;
    private int maxId = 0, currId = -1;
    private PopupWindow pop;

    private LayoutParams matchAndWrap = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT);


    public WordSearcher(Context context){
        super(context);
        editText = new EditText(context);
        editText.setMaxLines(1);
        editText.setInputType(TYPE_CLASS_TEXT);


        //setLayoutParams(matchAndWrap);
        editText.setLayoutParams(matchAndWrap);

        suggestions = new WordSuggestions(context, this);
        pop = new PopupWindow(suggestions, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        pop.setContentView(suggestions);

        addView(editText);
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                //Find suggestions
                if(s.length() > 0) {
                    new JSONTask(WordSearcher.this).execute(
                            "http://getnames-getnames.a3c1.starter-us-west-1.openshiftapps.com/getnames/" + maxId + "/" + s
                    );
                    //increase the ID to get a new one every search
                    maxId++;
                } else
                    setSuggestions(new JSONArray());
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        pop.showAsDropDown(this ,0, 0);


    }

    public void setSuggestions(JSONArray names) {
        suggestions.setSuggestions(names);
    }

    public int getCurrId() {
        return currId;
    }

    public void setCurrId(int id) {
        currId = id;
    }

    public void setText(String word) {
        editText.setText(word);
        editText.setSelection(word.length());
    }

    public void setMaxSuggestions(int maxSuggestions) {
        suggestions.setMaxSuggestions(maxSuggestions);
    }

    public int getMaxSuggestions() {
        return suggestions.getMaxSuggestions();
    }

}