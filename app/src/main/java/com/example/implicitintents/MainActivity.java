package com.example.implicitintents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareText = findViewById(R.id.share_edittext);

    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("Intent Error", "Cant Found any Applications");
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUrl = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUrl);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("Intent Error", "Cant Found any Applications");
        }
    }

    public void shareText(View view) {
        String txt = mShareText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_this_text)
                .setText(txt)
                .startChooser();
    }
}