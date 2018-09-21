package com.tritekcorp.user.snippy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Snippet> snippets = new ArrayList<Snippet>();

    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.tritekcorp.user.snippy",
                        Context.MODE_PRIVATE);

        //Getting snippets from user prefs
        try {
            snippets = (ArrayList<Snippet>) ObjectSerializer.deserialize(sharedPreferences
                    .getString("snippets",ObjectSerializer.serialize(new ArrayList<String>())));
            //Returns a null arraylist, if the sharedpref doesn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Displaying Snippets List
        ListView snippetsListView = (ListView)  findViewById(R.id.snippetsListView);
        arrayAdapter = new ArrayAdapter(this, R.layout.snippet_list_view ,snippets);
        snippetsListView.setAdapter(arrayAdapter);

        snippetsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ShowBodyActivity.class);
                intent.putExtra("snippetId",i);
                startActivity(intent);
            }
        });

        snippetsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are You Sure?")
                        .setMessage("rm -rf snippet?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                snippets.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();
                                storeSnippets();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();


                return true;
            }
        });

        //Selection Spinner
        Spinner selectTypeSpinner = (Spinner)findViewById(R.id.selectTypeSpinner);
        selectTypeSpinner.setAdapter(new ArrayAdapter<Language>(this, R.layout.spinner_text_view, Language.values()));

        selectTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<Snippet> sortedSnippets = new ArrayList<Snippet>();
                //sortedSnippets will update depending on the spinner selection

                for(Snippet snippet: snippets){
                    if(  snippet.getType().getValue() == i ){
                        sortedSnippets.add(snippet);
                    }
                }

                ListView snippetsListView = (ListView)  findViewById(R.id.snippetsListView);
                arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.snippet_list_view ,sortedSnippets);
                snippetsListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void addSnippet(View view){
        Intent intent = new Intent(getApplicationContext(), AddSnippetActivity.class);

        startActivity(intent);
    }

    public void account(View view){
        Intent intent = new Intent(getApplicationContext(), AccountActivity.class);

        startActivity(intent);
    }

    public void storeSnippets(){
        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.tritekcorp.user.snippy",
                        Context.MODE_PRIVATE);


        try {
            sharedPreferences.edit().putString("snippets",ObjectSerializer.serialize(snippets)).apply();
            //Log.i("friends",ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aboutPage(View view){
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);

        startActivity(intent);
    }

    public void showAllSnippets(View view){
        ArrayList<Snippet> sortedSnippets = snippets;
        ListView snippetsListView = (ListView)  findViewById(R.id.snippetsListView);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.snippet_list_view ,sortedSnippets);
        snippetsListView.setAdapter(arrayAdapter);
    }

}
