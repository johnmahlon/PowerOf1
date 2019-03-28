package com.tcf2j.powerof1.Quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tcf2j.powerof1.MainActivity;
import com.tcf2j.powerof1.Quiz.DBHelper.DBHelper;
import com.tcf2j.powerof1.Quiz.Question.Question;
import com.tcf2j.powerof1.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends MainActivity {

    private TextView textViewQusetion;
    private TextView textViewCount;
    private TextView textViewSeverity;
    private Button yes;
    private Button no;
    private Button buttonNext;
    private ArrayList<Question> questionList;


    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_NOANSWERED = "keyNoAnswered";
    private static final String KEY_YESANSWERED = "keyYesAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    private static final String KEY_SeverityTotal = "keySeverityTotal";

    private int questionCounter; // number of question answered
    private int questionCountTotal; // total number of question in the list
    private Question currentQuestion; // current question in the list
    private int SeverityTotal; // holds total points of the questions
    private boolean yesAns = false;
    private boolean noAns = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_quiz, null, false);
        drawerLayout.addView(contentView, 0);
       // setContentView(R.layout.activity_quiz);

        textViewQusetion = findViewById(R.id.text_view_question);
        textViewCount = findViewById(R.id.text_view_count);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);




        if (savedInstanceState == null) {
            DBHelper dbHelper = new DBHelper(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = questionList.size(); // get total count of count
            Collections.shuffle(questionList);// randomize question in the list


            nextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            SeverityTotal = savedInstanceState.getInt(KEY_SeverityTotal);
            currentQuestion = questionList.get(questionCounter - 1);
            questionCountTotal = questionList.size(); // get total count of count
            yesAns = savedInstanceState.getBoolean(KEY_YESANSWERED);
            noAns = savedInstanceState.getBoolean(KEY_NOANSWERED);
            yes.setText(currentQuestion.getOption1());
            no.setText(currentQuestion.getOption2());

        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                yesAns = true;
                checkAnswer();
                endQuiz();
                nextQuestion();

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noAns = true;
                checkAnswer();
                endQuiz();
                nextQuestion();

            }
        });



    }
    private void nextQuestion(){

        if (questionCounter < questionCountTotal){// check to see if there are still Question left
            currentQuestion = questionList.get(questionCounter);


            textViewQusetion.setText(currentQuestion.getQuestion()); // display question in text view
            yes.setText(currentQuestion.getOption1());
            no.setText(currentQuestion.getOption2());
            questionCounter++;

            textViewCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
           // Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer(){

        if(yesAns) {
            SeverityTotal += currentQuestion.getYesValue();
        }
        if(noAns){
            SeverityTotal += currentQuestion.getNoValue();
        }
        yesAns = false;
        noAns = false;
    }


    private void endQuiz(){ // wnd quiz by calling a new intent congratulations
        if(questionCounter == questionCountTotal) {

            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, Congratulations.class);
            bundle.putInt("scores",SeverityTotal);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    @Override
    // save state went screen rotate
    protected void onSaveInstanceState(Bundle onState) {
        super.onSaveInstanceState(onState);
        onState.putInt(KEY_QUESTION_COUNT, questionCounter);
        onState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
        onState.putInt(KEY_SeverityTotal,SeverityTotal);
        onState.putBoolean(KEY_YESANSWERED,yesAns);
        onState.putBoolean(KEY_NOANSWERED,noAns);
    }
}


