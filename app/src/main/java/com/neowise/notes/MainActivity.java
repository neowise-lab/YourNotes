package com.neowise.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.neowise.notes.data.Note;
import com.neowise.notes.data.NoteDao;
import com.neowise.notes.data.NoteDatabase;
import com.neowise.notes.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NoteAdapter noteAdapter = new NoteAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(noteAdapter);


        noteAdapter.setNoteAction(note -> {
            NoteDatabase db = NoteDatabase.getInstance(this);
            NoteDao dao = db.noteDao();
            dao.delete(note);
            noteAdapter.remove(note);
        });

        binding.addFab.setOnClickListener(e -> {
            startActivityForResult(new Intent(this, EditNoteActivity.class), 300);
        });

        loadNotes();
    }

    private void loadNotes() {
        NoteDatabase db = NoteDatabase.getInstance(this);
        NoteDao dao = db.noteDao();
        List<Note> notes = dao.findAll();

        noteAdapter.setNotes(notes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadNotes();
        }
    }
}