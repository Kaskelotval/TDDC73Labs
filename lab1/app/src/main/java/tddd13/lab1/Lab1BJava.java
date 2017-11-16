package tddd13.lab1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class Lab1BJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create container for all objects
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // - - - NAMN - - - //
        layout.addView(createNewRow("Namn", new EditText(this)));

        // - - - Lösen - - - //
        layout.addView(createNewRow("Lösenord", new EditText(this)));

        // - - - Epost - - - //
        layout.addView(createNewRow("Epost",new EditText(this)));

        // - - - Ålder - - - //
        layout.addView(createNewRow("Ålder", new SeekBar(this)));


        // bottom button
        Button button = new Button(this);
        button.setText("Next");
        layout.addView(button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                goToNext();
            }});

        layout.setPadding(10,10,10,10);
        setContentView(layout);
    }

    private LinearLayout createNewRow(String text, View input) {

        //Parameters for linear layout
        LinearLayout.LayoutParams parameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // Create the container for each row
        LinearLayout tempLayout = new LinearLayout(this);
        tempLayout.setLayoutParams(parameters);
        tempLayout.setOrientation(LinearLayout.HORIZONTAL);

        //Text (left)
        TextView textView = new TextView(this);
        textView.setLayoutParams(
                new ActionBar.LayoutParams(
                        120,
                        120,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        textView.setText(text);

        //Input (Right)
        input.setLayoutParams(parameters);
        tempLayout.addView(textView);
        tempLayout.addView(input);

        return tempLayout;
    }

    /** Called when the user taps the Send button */
    public void goToNext() {
        Intent intent = new Intent(this, Lab1BXML.class);
        startActivity(intent);

    }
}
