package com.tcf2j.powerof1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.CardView;


import com.tcf2j.powerof1.MainActivity;

public class HomePage extends MainActivity implements View.OnClickListener {

    private CardView aByCard, dvCard, conCard, stalkCard, sexACard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_home_page);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_home_page, null, false);
        drawerLayout.addView(contentView, 0);

        //defining Cards
        aByCard = (CardView) findViewById(R.id.ActiveByHomePageButton);
        dvCard = (CardView) findViewById(R.id.DatingVHomePButton);
        conCard = (CardView) findViewById(R.id.ConsentHomePButton);
        stalkCard = (CardView) findViewById(R.id.StalkingHomePButton);
        sexACard = (CardView) findViewById(R.id.SexAHomePButton);
        // Adding Click Listener
        aByCard.setOnClickListener(this);
        dvCard.setOnClickListener(this);
        conCard.setOnClickListener(this);
        stalkCard.setOnClickListener(this);
        sexACard.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.ActiveByHomePageButton:
                i = new Intent(this, ActiveBystander.class);
                startActivity(i);
                break;
            case R.id.DatingVHomePButton:
                i = new Intent(this, DatingViolence.class);
                startActivity(i);
                break;
            case R.id.ConsentHomePButton:
                i = new Intent(this, Consent.class);
                startActivity(i);
                break;
            case R.id.StalkingHomePButton:
                i = new Intent(this, Stalking.class);
                startActivity(i);
                break;
            case R.id.SexAHomePButton:
                i = new Intent(this, SexualAssault.class);
                startActivity(i);
                break;
            default:
                break;
    }




    }
}
