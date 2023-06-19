package com.example.noteapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noteapp.R;
import com.example.noteapp.databinding.ActivityAddNewNodeBinding;
import com.example.noteapp.models.Notes;
import com.example.noteapp.viewModel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddNewNodeActivity extends AppCompatActivity {
    ActivityAddNewNodeBinding binding;
    private String title,subTitle,date,notes,priority="1";
    private NoteViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_node);
        viewModel=new ViewModelProvider(this).get(NoteViewModel.class);
        binding.PriorityGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.PriorityGreen.setImageResource(R.drawable.shape_done);
                binding.PriorityRed.setImageResource(0);
                binding.PriorityYellow.setImageResource(0);
                priority="2";

            }
        });
        binding.PriorityRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.PriorityRed.setImageResource(R.drawable.shape_done);
                binding.PriorityGreen.setImageResource(0);
                binding.PriorityYellow.setImageResource(0);
                priority="3";

            }
        });
        binding.PriorityYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.PriorityYellow.setImageResource(R.drawable.shape_done);
                binding.PriorityRed.setImageResource(0);
                binding.PriorityGreen.setImageResource(0);
                priority="1";


            }
        });

        binding.fabAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=binding.editTextTitle.getText().toString();
                subTitle=binding.editTextSubTitle.getText().toString();
                notes=binding.editTextNote.getText().toString();
                date=getCurrentDate();
                if(!title.isEmpty()&&!notes.isEmpty()){
                    CreateNewNode(title,subTitle,notes,date,priority);
                }
                else
                    Toast.makeText(AddNewNodeActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void CreateNewNode(String title, String subTitle, String notes, String date,String priority) {
        Notes newNote=new Notes(title,subTitle,notes,date,priority);
        viewModel.insertNotes(newNote);
        Toast.makeText(this, "added successful", Toast.LENGTH_SHORT).show();
        finish();
    }
    private String getCurrentDate(){
        Calendar c=Calendar.getInstance();
        SimpleDateFormat m = new SimpleDateFormat("EEEE hh:mm a ");
        String s = m.format(c.getTime());
        return s ;
    }

}