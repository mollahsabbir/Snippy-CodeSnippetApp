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
import android.widget.ListView;

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



}





















/*
SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.tritekcorp.user.snippy",
                        Context.MODE_PRIVATE);


        ArrayList<Snippet> snippets = new ArrayList<Snippet>();

        Snippet snippet1 = new Snippet("Hello", "Hello", Language.CPlusPlus);
        Snippet snippet2 = new Snippet("Hello2", "Hello2", Language.CSharp);
        Snippet snippet3 = new Snippet("Hello3", "Hello3", Language.PseudoCode);

        snippets.add(snippet1);
        snippets.add(snippet2);
        snippets.add(snippet3);

        try {
            sharedPreferences.edit().putString("snippets",ObjectSerializer.serialize(snippets)).apply();
            //Log.i("friends",ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Snippet > newSnippets = new ArrayList<Snippet>();

        try {
            newSnippets = (ArrayList<Snippet>) ObjectSerializer.deserialize(sharedPreferences.getString("snippets",ObjectSerializer.serialize(new ArrayList<Snippet>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Snippet snippet: newSnippets)
        Log.i("New snippet",snippet.toString());

*/
