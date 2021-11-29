package com.neowise.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.neowise.notes.data.Note;
import com.neowise.notes.databinding.ItemNoteBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    private NoteAction noteAction;

    public void setNoteAction(NoteAction noteAction) {
        this.noteAction = noteAction;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        this.notifyDataSetChanged();
    }

    public void remove(Note note) {
        int idx = notes.indexOf(note);
        notes.remove(idx);
        notifyItemRemoved(idx);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_note, parent, false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.bind(notes.get(position), noteAction);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteHolder extends RecyclerView.ViewHolder {

        private final ItemNoteBinding binding;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNoteBinding.bind(itemView);
        }

        public void bind(Note note, NoteAction noteAction) {
            binding.title.setText(note.title);
            binding.content.setText(note.content);

            binding.getRoot().setBackgroundTintList(
                    ContextCompat.getColorStateList(itemView.getContext(), note.color)
            );

            binding.deleteBtn.setOnClickListener(e -> {
                noteAction.delete(note);
            });
        }
    }

    @FunctionalInterface
    public interface NoteAction {
        void delete(Note note);
    }
}
