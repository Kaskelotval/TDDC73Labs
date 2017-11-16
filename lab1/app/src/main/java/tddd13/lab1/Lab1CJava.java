package tddd13.lab1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import  android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;


public class Lab1CJava extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create container for all objects
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // - - - First - - - //
        TextView text = new TextView(this);
        text.setText("Hur trivs du på LiU");
        layout.addView(text);

        String[] options = new String[]{"Bra", "Mycket Bra", "Superbra"};
        layout.addView(createNewRow(options));


        // - - - Second - - - //
        TextView text2 = new TextView(this);
        text2.setText("Läser du på LiTH");
        layout.addView(text2);

        String[] yesNo = new String[]{"Ja", "Nej"};
        layout.addView((createNewRow(yesNo)));

        // - - - Image - - - //
        ImageView image = new ImageView(this);
        LinearLayout.LayoutParams imParameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        imParameters.gravity = Gravity.CENTER;

        image.setLayoutParams(imParameters);
        image.setBackgroundResource(R.drawable.logo);
        layout.addView(image);

        // - - - Last question - - - //

        TextView text3 = new TextView(this);
        text3.setText("Är detta lius logotyp?");
        layout.addView(text3);

        layout.addView((createNewRow(yesNo)));

        // - - - End of questions - - - //
        layout.setPadding(10,10,10,10);
        setContentView(layout);

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
    }

    private LinearLayout createNewRow(String[] options) {

        //Parameters for linear layout
        LinearLayout.LayoutParams parameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // Create the container for each row
        LinearLayout tempLayout = new LinearLayout(this);

        tempLayout.setLayoutParams(parameters);
        tempLayout.setOrientation(LinearLayout.HORIZONTAL);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        RadioButton[] radio = new RadioButton[options.length];
        for(int i = 0; i<options.length;i++)
        {
            radio[i] = new RadioButton(this);
            radio[i].setText(options[i]);
            radioGroup.addView(radio[i]);
        }

        tempLayout.addView(radioGroup);

        return tempLayout;

    }

   /** Called when the user taps the Send button */
   public void goToNext() {
        Intent intent = new Intent(this, Lab1CXML.class);
        startActivity(intent);

   }

}
