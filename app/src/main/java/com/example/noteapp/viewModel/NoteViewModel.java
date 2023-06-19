package com.example.noteapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteapp.models.Notes;
import com.example.noteapp.repository.Repository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    public LiveData<List<Notes>> listOfNotes=null;
    public LiveData<List<Notes>> highToLow=null;
    public LiveData<List<Notes>> lowToHigh=null;
    private Repository repository;

    @ViewModelInject
    public NoteViewModel(@NonNull Application application, Repository repository) {
        super(application);
        this.repository = repository;
    }
    public void insertNotes(Notes... notes){
        repository.insertNotes(notes);
    }

    public void deleteNotes(int id){
        repository.deleteNotes(id);
    }

    public void getAllNotes(){
        listOfNotes= repository.getAllNotes();
    }
    public void getHighToLow(){
        highToLow= repository.getHighToLow();
    }
    public void getLowToHigh(){
        lowToHigh= repository.getLowToHigh();
    }


    public void updateNotes(Notes notes){
        repository.updateNotes(notes);
    }



}
