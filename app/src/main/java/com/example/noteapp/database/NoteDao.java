package com.example.noteapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.models.Notes;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void insertNotes(Notes... notes);

    @Query("delete from notes_table where id=:id")
    public void deleteNotes(int id);

    @Query("select * from notes_table")
    LiveData<List<Notes>> getAllNotes();
    @Query("select * from notes_table ORDER By notesPriority DESC")
    LiveData<List<Notes>> getHighToLow();
    @Query("select * from notes_table ORDER By notesPriority ASC")
    LiveData<List<Notes>> getLowToHigh();

    @Update
    public void updateNotes(Notes notes);
}
