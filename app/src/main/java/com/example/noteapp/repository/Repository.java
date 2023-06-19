package com.example.noteapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.database.NoteDao;
import com.example.noteapp.database.NoteDatabase;
import com.example.noteapp.models.Notes;

import java.util.List;

import javax.inject.Inject;

public class Repository {
    private NoteDao noteDao;

   @Inject
    public Repository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void insertNotes(Notes... notes){
        noteDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        noteDao.deleteNotes(id);
    }

   public LiveData<List<Notes>> getAllNotes(){
        return noteDao.getAllNotes();
    }

   public LiveData<List<Notes>> getHighToLow(){
        return noteDao.getHighToLow();
   }
    public LiveData<List<Notes>> getLowToHigh(){
        return noteDao.getLowToHigh();
    }

    public void updateNotes(Notes notes){
        noteDao.updateNotes(notes);
    }


}
