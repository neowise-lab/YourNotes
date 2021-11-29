package com.neowise.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.neowise.notes.data.Note;
import com.neowise.notes.data.NoteDatabase;
import com.neowise.notes.databinding.ActivityEditNoteBinding;

public class EditNoteActivity extends AppCompatActivity {

    private ActivityEditNoteBinding binding;
    private int color = R.color.color1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View[] colorViews = new View[]{
                binding.color1, binding.color2, binding.color3,
                binding.color4, binding.color5
        };

        for (View view: colorViews) {
            view.setOnClickListener(e -> {
                switch (view.getId()) {
                    case R.id.color1: setColor(R.color.color1); break;
                    case R.id.color2: setColor(R.color.color2); break;
                    case R.id.color3: setColor(R.color.color3); break;
                    case R.id.color4: setColor(R.color.color4); break;
                    case R.id.color5: setColor(R.color.color5); break;
                }
            });
        }

        binding.saveFab.setOnClickListener(e -> {

            String title = binding.titleEdit.getText().toString().trim();
            String content = binding.contentEdit.getText().toString().trim();

            if (title.isEmpty() && content.isEmpty()) {
                Snackbar
                        .make(binding.getRoot(), "Fields can not be empty!", Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                Note note = new Note(title, content, color);
                NoteDatabase.getInstance(this)
                        .noteDao()
                        .insertAll(note);

                setResult(RESULT_OK);
                finish();
            }
        });

        setColor(R.color.color1);
    }

    private void setColor(int color) {
        this.color = color;
        binding.getRoot()
                .setBackgroundColor(getResources().getColor(color));
    }
}