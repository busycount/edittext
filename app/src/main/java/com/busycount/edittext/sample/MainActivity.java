package com.busycount.edittext.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.busycount.edittext.SecretEditText;
import com.busycount.keyboard.NumKeyboard;
import com.busycount.keyboard.OnNumKeyListener;

public class MainActivity extends AppCompatActivity {

    SecretEditText secretEditText;
    NumKeyboard numKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secretEditText = findViewById(R.id.secretEditText);
        numKeyboard = findViewById(R.id.numKeyboard);
        numKeyboard.setOnNumKeyListener(new OnNumKeyListener() {
            @Override
            public void onKeyNum(char c) {
                secretEditText.add(c);
                if (secretEditText.isDone()) {
                    onKeyDone();
                }
            }

            @Override
            public void onKeyDelete() {
                secretEditText.delete();
            }

            @Override
            public void onKeyDone() {
                toast();
            }
        });
    }

    private void toast() {
        Toast.makeText(this, secretEditText.getString(), Toast.LENGTH_SHORT).show();
    }
}

