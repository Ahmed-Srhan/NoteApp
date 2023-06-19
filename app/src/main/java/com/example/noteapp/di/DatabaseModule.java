package com.example.noteapp.di;

import android.app.Application;

import androidx.room.Room;

import com.example.noteapp.database.NoteDao;
import com.example.noteapp.database.NoteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {
    public static NoteDatabase INSTANCE;

    @Provides
    @Singleton
    public static synchronized NoteDatabase provideDB(Application application){

            return Room.databaseBuilder(application,NoteDatabase.class,"Note_Database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

    }
    @Provides
    @Singleton
    public static NoteDao provideDao(NoteDatabase noteDatabase){
        return noteDatabase.noteDao();
    }

}
