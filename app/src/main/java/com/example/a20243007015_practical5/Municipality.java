package com.example.a20243007015_practical5;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {

    private String municipalityName;
    private String websiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        String passedName = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        municipalityName = TextProcessor.processMunicipality(passedName);

        // Handle missing or invalid data safely.
        if (municipalityName == null) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int colourId;
        int imageId;
        int summaryIndex;

        switch (municipalityName) {
            case "Beijing":
                colourId = R.color.municipality_beijing;
                imageId = R.drawable.beijing;
                summaryIndex = 0;
                websiteUrl = getString(R.string.url_beijing);
                break;
            case "Shanghai":
                colourId = R.color.municipality_shanghai;
                imageId = R.drawable.shanghai;
                summaryIndex = 1;
                websiteUrl = getString(R.string.url_shanghai);
                break;
            case "Chongqing":
                colourId = R.color.municipality_chongqing;
                imageId = R.drawable.chongqing;
                summaryIndex = 2;
                websiteUrl = getString(R.string.url_chongqing);
                break;
            case "Tianjin":
                colourId = R.color.municipality_tianjin;
                imageId = R.drawable.tianjin;
                summaryIndex = 3;
                websiteUrl = getString(R.string.url_tianjin);
                break;
            default:
                colourId = R.color.municipality_beijing;
                imageId = R.drawable.beijing;
                summaryIndex = 0;
                websiteUrl = getString(R.string.url_beijing);
                break;
        }

        int colour = getColor(colourId);
        int textLight = getColor(R.color.color_text_light);
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);

        TextView nameView = findViewById(R.id.textViewName);
        TextView infoView = findViewById(R.id.textViewInfo);
        ImageView imageView = findViewById(R.id.imageViewMunicipality);
        Button visitButton = findViewById(R.id.buttonVisitSite);

        nameView.setText(municipalityName);
        nameView.setBackgroundColor(colour);
        nameView.setTextColor(textLight);

        infoView.setText(summaries[summaryIndex]);
        infoView.setTextColor(colour);

        imageView.setImageResource(imageId);

        visitButton.setBackgroundTintList(ColorStateList.valueOf(colour));
        visitButton.setTextColor(textLight);
        visitButton.setOnClickListener(v -> openWebsite());
    }

    /**
     * Opens the official website of the currently displayed municipality using
     * an implicit ACTION_VIEW intent.
     */
    private void openWebsite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        try {
            startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_LONG).show();
        }
    }
}
