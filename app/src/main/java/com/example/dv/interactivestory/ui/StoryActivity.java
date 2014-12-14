package com.example.dv.interactivestory.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dv.interactivestory.R;
import com.example.dv.interactivestory.model.Page;
import com.example.dv.interactivestory.model.Story;


public class StoryActivity extends Activity {

    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story story = new Story();
    private String name;
    private Page page;

    private ImageView image;
    private TextView text;
    private Button choice1;
    private Button choice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.name));
        if (name.length() < 1) {
            name = "Friend";
        }

        Log.d(TAG, name);

        image = (ImageView) findViewById(R.id.storyImageView);
        text = (TextView) findViewById(R.id.storyTextView);
        choice1 = (Button) findViewById(R.id.choiceButton1);
        choice2 = (Button) findViewById(R.id.choiceButton2);

        loadPage(0);
    }

    private void loadPage(int choice) {
        page = story.getPage(choice);

        Drawable drawable = getResources().getDrawable(page.getImageId());
        image.setImageDrawable(drawable);

        text.setText(String.format(page.getText(), name));

        if (page.isFinal()) {
            choice1.setVisibility(View.INVISIBLE);
            choice2.setText("Play again");
            choice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            choice1.setText(page.getChoice1().getText());
            choice2.setText(page.getChoice2().getText());

            choice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = page.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            choice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = page.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }

}
