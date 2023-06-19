package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.noteapp.adapters.NoteAdapter;
import com.example.noteapp.adapters.OnNoteViewListener;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.models.Notes;
import com.example.noteapp.ui.AddNewNodeActivity;
import com.example.noteapp.ui.UpdateNoteActivity;
import com.example.noteapp.viewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity{
    public static final String UPDATE_NOTE = "update_note";
    ActivityMainBinding binding;
    private NoteViewModel viewModel;
    private NoteAdapter adapter;
    private List<Notes> filterNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
       viewModel=new ViewModelProvider(this).get(NoteViewModel.class);
       adapter=new NoteAdapter(new OnNoteViewListener() {
           @Override
           public void onViewClickListener(Notes notes) {
               Intent intent=new Intent(MainActivity.this, UpdateNoteActivity.class);
               intent.putExtra(UPDATE_NOTE,notes);
               startActivity(intent);

           }
       });
       binding.NotesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
       binding.NotesRecyclerView.setAdapter(adapter);
       viewModel.getAllNotes();
       viewModel.listOfNotes.observe(this, new Observer<List<Notes>>() {
           @Override
           public void onChanged(List<Notes> notes) {
               adapter.setNotes((ArrayList<Notes>) notes);
               filterNotes=notes;

           }
       });
       binding.fabNewNote.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, AddNewNodeActivity.class));
           }
       });

        binding.noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
        binding.noFilter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               loadData(0);
               binding.noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
               binding.lowToHigh.setBackgroundResource(R.drawable.filter_shape);
               binding.highToLow.setBackgroundResource(R.drawable.filter_shape);


           }
       });
        binding.highToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(1);
                binding.highToLow.setBackgroundResource(R.drawable.filter_selected_shape);
                binding.noFilter.setBackgroundResource(R.drawable.filter_shape);
                binding.lowToHigh.setBackgroundResource(R.drawable.filter_shape);
            }
        });
        binding.lowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(2);
                binding.lowToHigh.setBackgroundResource(R.drawable.filter_selected_shape);
                binding.noFilter.setBackgroundResource(R.drawable.filter_shape);
                binding.highToLow.setBackgroundResource(R.drawable.filter_shape);

            }
        });

    }

    private void loadData(int i) {
        if(i==0){
            viewModel.getAllNotes();
            viewModel.listOfNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    adapter.setNotes((ArrayList<Notes>) notes);
                }
            });
        } else if (i==1) {
            viewModel.getHighToLow();
            viewModel.highToLow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    adapter.setNotes((ArrayList<Notes>) notes);
                }
            });
        } else if (i==2) {
            viewModel.getLowToHigh();
            viewModel.lowToHigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    adapter.setNotes((ArrayList<Notes>) notes);
                }
            });

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_note,menu);
        MenuItem menuItem=menu.findItem(R.id.ic_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here ...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                noteFilter(s);

                return false;
            }
        });
        return true;
    }


    private void noteFilter(String newText) {
        ArrayList<Notes> filterName=new ArrayList<>();
        for (Notes notes:filterNotes
             ) {
            if ((notes.getNotesSubtitle().contains(newText))||notes.getNotesTitle().contains(newText)){
                filterName.add(notes);
            }
            
        }
        adapter.searchNotes(filterName);
    }

}