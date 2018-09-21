package com.tritekcorp.user.snippy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

public class AddSnippetActivity extends AppCompatActivity {

    Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_snippet);

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        typeSpinner.setAdapter(new ArrayAdapter<Language>(this, R.layout.spinner_text_view, Language.values()));


    }

    public void saveSnippet(View view){

        String title;
        String body;
        Language type;

        EditText titleEditText = (EditText)findViewById(R.id.titleEditText);
        EditText bodyEditText = (EditText)findViewById(R.id.bodyEditText);
        Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);

        title = titleEditText.getText().toString();
        body = bodyEditText.getText().toString();
        type = (Language)typeSpinner.getSelectedItem();

        Snippet newSnippet = new Snippet(title, body, type);
        MainActivity.snippets.add(newSnippet);
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.tritekcorp.user.snippy",
                        Context.MODE_PRIVATE);


        try {
            sharedPreferences.edit().putString("snippets",ObjectSerializer.serialize(MainActivity.snippets)).apply();
            //Log.i("friends",ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }
}
