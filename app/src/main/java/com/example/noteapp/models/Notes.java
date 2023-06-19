package com.example.noteapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes_table")
public class Notes implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String notesTitle;
    private String notesSubtitle;
    private String notes;
    private String notesDate;
    private String notesPriority;

    public Notes() {
    }

    public Notes(String notesTitle, String notesSubtitle, String notes, String notesDate, String notesPriority) {
        this.notesTitle = notesTitle;
        this.notesSubtitle = notesSubtitle;
        this.notes = notes;
        this.notesDate = notesDate;
        this.notesPriority = notesPriority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(String notesDate) {
        this.notesDate = notesDate;
    }

    public String getNotesSubtitle() {
        return notesSubtitle;
    }

    public void setNotesSubtitle(String notesSubtitle) {
        this.notesSubtitle = notesSubtitle;
    }

    public String getNotesPriority() {
        return notesPriority;
    }

    public void setNotesPriority(String notesPriority) {
        this.notesPriority = notesPriority;
    }
}
