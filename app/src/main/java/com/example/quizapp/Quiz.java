package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.style.QuoteSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    private TextView textView_count;
    private TextView textView_timer;
    private TextView textView_Question;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button btn_next;
    private  MockData MD = new MockData();

    public Question currentQuestion; // = new Question("how are you?", "fine", "good", "alhamdulillah", "not good", "3");
    private int totalQuestions = 13;
    private int questionsDone = 0;
    private int score = 0;
    public static int currentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        MD.LoadQuestions();

        textView_count = findViewById(R.id.textView_numberOfQuestions);
        textView_timer = findViewById(R.id.textView_timer);
        textView_Question = findViewById(R.id.textView_question);

        radioGroup = findViewById(R.id.radio_group);
        radioButton1 = findViewById(R.id.option_1);
        radioButton2 = findViewById(R.id.option_2);
        radioButton3 = findViewById(R.id.option_3);
        radioButton4 = findViewById(R.id.option_4);

        btn_next = findViewById(R.id.button_next);

        setCurrentQuestion();
        //DisplayNextQuestion();
    }

    public void DisplayNextQuestion(){
        radioGroup.clearCheck();
        if(questionsDone < totalQuestions){
            textView_Question.setText(currentQuestion.getQuestion());
            radioButton1.setText(currentQuestion.getFirstOption());
            radioButton2.setText(currentQuestion.getSecondOption());
            radioButton3.setText(currentQuestion.getThirdOption());
            radioButton4.setText(currentQuestion.getFourthOption());

            textView_count.setText("Question: " + (questionsDone + 1) + " / " + totalQuestions);

            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(currentQuestion.getCorrectOption() == "1"){
                        if(radioButton1.isChecked()){
                            score = score + 1;
                        }
                    }
                    else if(currentQuestion.getCorrectOption() == "2"){
                        if(radioButton2.isChecked()){
                            score = score + 1;
                        }
                    }
                    else if(currentQuestion.getCorrectOption() == "3"){
                        if(radioButton3.isChecked()){
                            score = score + 1;
                        }
                    }
                    else if(currentQuestion.getCorrectOption() == "4"){
                        if(radioButton4.isChecked()){
                            score = score + 1;
                        }
                    }
                    radioGroup.clearCheck();
                    questionsDone = questionsDone + 1;
                    currentQuestionIndex = currentQuestionIndex + 1;
                    setCurrentQuestion();
                }
            });
        }
        else if (questionsDone == totalQuestions){
            textView_count.setText("Question: " + (questionsDone + 1) + " / " + totalQuestions);

            String scr = "Score: " + score;

            Toast.makeText(getApplicationContext(),scr,Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void setCurrentQuestion(){

        if(questionsDone == totalQuestions){
            DisplayNextQuestion();
            return;
        }

        String ques = MD.questions[currentQuestionIndex].getQuestion();
        String op1 = MD.questions[currentQuestionIndex].getFirstOption();
        String op2 = MD.questions[currentQuestionIndex].getSecondOption();
        String op3 = MD.questions[currentQuestionIndex].getThirdOption();
        String op4 = MD.questions[currentQuestionIndex].getFourthOption();
        String cop = MD.questions[currentQuestionIndex].getCorrectOption();

        currentQuestion = new Question(
                /*MD.questions[currentQuestionIndex].getQuestion(),
                MD.questions[currentQuestionIndex].getFirstOption(),
                MD.questions[currentQuestionIndex].getSecondOption()),
                MD.questions[currentQuestionIndex].getThirdOption(),
                MD.questions[currentQuestionIndex].getFourthOption(),
                MD.questions[currentQuestionIndex].getCorrectOption()*/

                ques, op1, op2, op3, op4,cop
        );

        DisplayNextQuestion();
    }


}