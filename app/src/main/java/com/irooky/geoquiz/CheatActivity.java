package com.irooky.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by irooky on 2016-10-08.
 */

public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE = "com.irooky.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_INDEX = "com.irooky.geoquiz.answer_index";
    public static final String EXTRA_ANSWER_SHOWN = "com.irooky.geoquiz.answer_shown";
    private static final String KEY_CHEAT = "cheat";
    private static final String KEY_PRESS = "press";
    private static final String KEY_INDEX = "index";

    private boolean mAnswerIsTrue;
    private boolean mAnswerButtonPressed;
    private int mAnswerIndex;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        data.putExtra(EXTRA_ANSWER_INDEX, mAnswerIndex);
        setResult(RESULT_OK, data);
    }

    private void setAnswerTextView() {
        if(mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if(savedInstanceState != null) {
            mAnswerIsTrue = savedInstanceState.getBoolean(KEY_CHEAT, false);
            mAnswerButtonPressed = savedInstanceState.getBoolean(KEY_PRESS, false);
            mAnswerIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerIndex = getIntent().getIntExtra(EXTRA_ANSWER_INDEX, 0);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        if(mAnswerButtonPressed) {
            setAnswerTextView();
            setAnswerShownResult(true);
        }

        mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerButtonPressed = true;

                setAnswerTextView();
                setAnswerShownResult(true);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CHEAT, mAnswerIsTrue);
        outState.putBoolean(KEY_PRESS, mAnswerButtonPressed);
        outState.putInt(KEY_INDEX, mAnswerIndex);
    }
}
