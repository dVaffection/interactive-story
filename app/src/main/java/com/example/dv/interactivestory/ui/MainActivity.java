package com.example.dv.interactivestory.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dv.interactivestory.R;


public class MainActivity extends Activity {

    private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameEditText);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();

                Intent intent = new Intent(MainActivity.this, StoryActivity.class);
                intent.putExtra(getString(R.string.name), name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        nameField.setText("");
    }
}
