package com.tritekcorp.user.snippy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView aboutInfoTextView = (TextView) findViewById(R.id.aboutInfoTextView);

        String help = "One Tap - See Snippet\nLong Tap - Delete Snippet\n\n";
        String info = "To see the LICENSE: ";

        aboutInfoTextView.setText(help + info );
    }

    public void sendToGithubRepo(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://github.com/SabbirMollah/Snippy-CodeSnippetApp.git"));
        startActivity(intent);
    }
}
