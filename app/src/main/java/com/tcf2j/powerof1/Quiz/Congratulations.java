package com.tcf2j.powerof1.Quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tcf2j.powerof1.DatingViolence;
import com.tcf2j.powerof1.HomePage;
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
            final TextView header = findViewById(R.id.header1);
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            final int scores = bundle.getInt("scores");

            final Button end = findViewById(R.id.Done);
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToMain(); // goes back to main
                }
            });


            if (scores == 0) {
                header.setText("It sounds like your relationship is on a pretty healthy track!");
                text2.setText("Maintaining healthy relationships takes some work -- keep it up! Remember that while you" +
                        "may have a healthy relationship, it’s possible that a friend of yours does not. If you know someone who is in an" +
                        " abusive relationship.");

                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToMain(); // goes back to main
                    }
                });
            } else if (scores <= 5) {
                header.setText("Hmm...Let’s Reflect");

                text2.setText("You might be noticing a couple of things in your relationship that are unhealthy, but it doesn’t " +
                        "necessarily mean they are warning signs. It’s still a good idea to keep an eye out" +
                        " and make sure there isn’t an unhealthy pattern developing.");

                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToDv(); // goes back to main
                    }
                });
            } else {
                header.setText("You are definitely seeing warning signs and may be in an abusive relationship.");
                text2.setText("Remember the most important thing is your safety — consider making a safety plan. " +
                        "You don’t have to deal with this alone. We can help.");

                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToDv(); // goes back to main
                    }
                });
            }

        }

        // method to goes back to main
        private void goToMain() {
            Intent intent = new Intent(this, HomePage.class);// classes main intent
            startActivity(intent);


        }

        private void goToDv() {
            Intent intent = new Intent(this, DatingViolence.class);
            startActivity(intent);
        }

}
