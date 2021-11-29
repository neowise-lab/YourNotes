package com.neowise.notes.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Query("select * from notes")
    List<Note> findAll();
}
