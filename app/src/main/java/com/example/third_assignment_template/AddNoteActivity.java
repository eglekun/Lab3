package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void onAddNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);
         if (txtNote.getText().toString().isEmpty()) {
             Toast.makeText(getApplicationContext(), R.string.msgNoText, Toast.LENGTH_SHORT).show();
         }
         else {
             SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
             SharedPreferences.Editor spEd = sp.edit();
             Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
             Set<String> newStrSet = new HashSet<String>();

             boolean isNoteExists = oldSet.contains(txtNote.getText().toString());
             if (isNoteExists) {
                 Toast.makeText(getApplicationContext(), R.string.msgNoteExists, Toast.LENGTH_SHORT).show();
             } else {
                 newStrSet.add(txtNote.getText().toString());
                 newStrSet.addAll(oldSet);
                 spEd.putStringSet("notes",newStrSet);
                 spEd.apply();
             }
             finish();
         }
    }
}
