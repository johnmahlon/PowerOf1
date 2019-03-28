package com.tcf2j.powerof1.Quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tcf2j.powerof1.MainActivity;
import com.tcf2j.powerof1.R;
public class Congratulations extends MainActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_congratulations);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_congratulations, null, false);
        drawerLayout.addView(contentView, 0);
        final TextView text2 = findViewById(R.id.textView2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int scores = bundle.getInt("scores");
        if (scores == 0) {
            text2.setText("You got a score of zero? Don’t worry - it’s a good thing! It sounds like your relationship is on a pretty healthy track. " +
                    "Maintaining healthy relationships takes some work - keep it up! Remember that while you may have a healthy "+
                    "relationship, it’s possible that a friend of yours does not. If you know someone who is in " +
                    "an abusive relationship.");
        }
        else if(scores <= 5){

            text2.setText("You might be noticing a couple of things in your relationship that are unhealthy, but it doesn’t necessarily mean " +
                    "they are warning signs. It’s still a good idea to keep an eye out and make sure there isn’t an unhealthy pattern " +
                    "developing. The best thing to do is to talk to your partner and let them know what you like and don’t like. " +
                    "Encourage them to do the same. Remember, communication is always important when building a healthy " +
                    "relationship. It’s also good to be informed so you can recognize the different types of abuse. If you would still like " +
                    "to learn more.");
        }
        else {
            text2.setText("You are definitely seeing warning signs and may be in an abusive relationship. Remember the most important thing " +
                    "is your safety — consider making a safety plan. You don’t have to deal with this alone. We can help.");
        }
        final Button end = findViewById(R.id.Done);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(); // goes back to main
            }
        });
    }
    // method to goes back to main
    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);// classes main intent
        startActivity(intent);


    }
}
