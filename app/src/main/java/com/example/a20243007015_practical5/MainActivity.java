package com.example.a20243007015_practical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /** Intent extra key shared between MainActivity and Municipality. */
    public static final String EXTRA_MUNICIPALITY = "com.example.a20243007015_practical5.EXTRA_MUNICIPALITY";

    private EditText municipalityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        municipalityInput = findViewById(R.id.editTextMunicipality);
        municipalityInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean isGo = actionId == EditorInfo.IME_ACTION_GO
                        || actionId == EditorInfo.IME_ACTION_DONE;
                boolean isEnter = event != null
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN;

                if (isGo || isEnter) {
                    processInput();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Reads, validates and normalises the user input, then either shows an error
     * or starts the Municipality activity via an explicit Intent.
     */
    private void processInput() {
        String rawInput = municipalityInput.getText().toString();
        String municipality = TextProcessor.processMunicipality(rawInput);

        if (municipality == null) {
            if (rawInput == null || rawInput.trim().isEmpty()) {
                Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }
}
