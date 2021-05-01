package com.example.third_assignment_template;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner ddSelection;
    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        this.ddSelection = (Spinner) findViewById(R.id.ddSelection);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        this.ddSelection.setAdapter(this.listAdapter);
    }

    public void onDeleteNoteClick(View view) {
        String selection = this.ddSelection.getSelectedItem().toString();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        if (oldSet.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.msgNoText, Toast.LENGTH_SHORT).show();
        }
        else {
            Set<String> newStrSet = new HashSet<String>();
            for (String str : oldSet) {
                if (!str.equals(selection)) {
                    newStrSet.add(str);
                }
            }
            spEd.putStringSet("notes",newStrSet);
            spEd.apply();
            Toast.makeText(getApplicationContext(), R.string.msgDeleted, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
