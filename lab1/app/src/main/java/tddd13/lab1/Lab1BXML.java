package tddd13.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Lab1BXML extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_bxml);
    }

    public void goToNext(View view) {
        Intent intent = new Intent(this, Lab1CJava.class);
        startActivity(intent);

    }
}
