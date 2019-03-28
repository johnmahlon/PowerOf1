package com.tcf2j.powerof1.Quiz.Question;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String question;
    private String option1;
    private String option2;

    private int yesValue;
    private int noValue;

    public Question(String question, String option1, String option2, int yesValue, int noValue) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.yesValue = yesValue;
        this.yesValue = noValue;

    }

    public Question() {
    }

    protected Question(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        yesValue = in.readInt();
        noValue = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeInt(yesValue);
        dest.writeInt(noValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }


    public int getYesValue() {
        return yesValue;
    }

    public void setYesValue(int yesValue) {
        this.yesValue = yesValue; }

    public int getNoValue() {
        return noValue;
    }

    public void setNoValue(int noValue) {
        this.noValue = noValue; }

}
