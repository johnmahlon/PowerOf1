package com.tcf2j.powerof1.Quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tcf2j.powerof1.MainActivity;
import com.tcf2j.powerof1.Quiz.DBHelper.DBHelper;
import com.tcf2j.powerof1.R;

public class StartQuizActivity extends MainActivity {
    private static final String TAG = "StartQuizActivity"; // tag for exception
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // setContentView(R.layout.activity_start_quiz);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_start_quiz, null, false);
        drawerLayout.addView(contentView, 0);
        final Button startQuiz = findViewById(R.id.start);// start activity
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();// calls QuizActivity
            }
        });



        DBHelper sqlLiteDataBaseHelper = new DBHelper(this);
        try {

            if(sqlLiteDataBaseHelper.checkDataBase()){

                Log.e(TAG,"Data Base Already Exists");// print exception

            }else {

                sqlLiteDataBaseHelper.CopyDataBaseFromAsset();//get database from asset folder

            }

        }catch (Exception e){
            e.printStackTrace(); //prints exception trace
        }
        try {

            sqlLiteDataBaseHelper.openDataBase(); // opens DataBase

        }catch (Exception e){
            e.printStackTrace(); //prints exception trace
        }
    }

    // new intent for QuizActivity class
    private void startQuiz() {
        Intent intent = new Intent(StartQuizActivity.this, QuizActivity.class);
        startActivity(intent);
    }


}
