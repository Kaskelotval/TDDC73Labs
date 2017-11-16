package tddd13.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create container for all objects
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //Needed for Stars
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        // Top button
        Button button = new Button(this);
        button.setText("Next");
        layout.addView(button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                 goToNext();
        }});


        //Text field
        EditText textField = new EditText(this);
        textField.setMaxLines(1); //sets line to max 1
        layout.addView(textField);

        //Stars
        RatingBar stars = new RatingBar(this);
        stars.setMax(5);
        stars.setNumStars(5);
        //Wrap content or chaos
        LinearLayout.LayoutParams starParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(stars, starParams);

        //textfield with multiple lines
        EditText multilineTextField = new EditText(this);
        LinearLayout.LayoutParams multilineTextFieldParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Match == fyll allt i detta fall
                LinearLayout.LayoutParams.MATCH_PARENT);
        layout.addView(multilineTextField, multilineTextFieldParams);


        setContentView(layout);
    }
    /** Called when the user taps the Send button */
    public void goToNext() {
        Intent intent = new Intent(this, Lab1AXML.class);
        startActivity(intent);

    }
}
