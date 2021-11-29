package com.neowise.notes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String content;
    public int color;

    public Note(String title, String content, int color) {
        this.title = title;
        this.content = content;
        this.color = color;
    }
}