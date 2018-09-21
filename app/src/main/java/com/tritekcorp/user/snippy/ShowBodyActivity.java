package com.tritekcorp.user.snippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ShowBodyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_body);

        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        EditText bodyTextView = (EditText)findViewById(R.id.bodyTextView);

        Intent intent = getIntent();

        int snippetId = intent.getIntExtra("snippetId", -1);

        if(snippetId != -1){
            titleTextView.setText(MainActivity.snippets.get(snippetId).toString());
            bodyTextView.setText(MainActivity.snippets.get(snippetId).getBody());
        }

    }
}
