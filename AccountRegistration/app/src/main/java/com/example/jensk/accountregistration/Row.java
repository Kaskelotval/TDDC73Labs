package com.example.jensk.accountregistration;

import android.content.Context;
import android.widget.EditText;

public class Row extends android.support.v7.widget.AppCompatEditText {
    private String title;
    private EditText input;

    public Row(Context context, String title, EditText input) {
        super(context);
        this.title = title;
        this.input = input;
    }

    public String getTitle() {
        return this.title;
    }

    public String getInput() {
        return this.input.getText().toString();
    }
}
