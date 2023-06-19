package com.example.noteapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.example.noteapp.databinding.ActivityUpdateNoteBinding;
import com.example.noteapp.models.Notes;
import com.example.noteapp.viewModel.NoteViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateNoteActivity extends AppCompatActivity  {
    ActivityUpdateNoteBinding binding;
    private String updateTitle,updateSubTitle,updateDate,updateNotes,updatePriority ="1";
    private NoteViewModel viewModel;
    private Notes oldNote;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_note);
        viewModel=new ViewModelProvider(this).get(NoteViewModel.class);

        oldNote=(Notes) getIntent().getSerializableExtra(MainActivity.UPDATE_NOTE);
        binding.editTextUpdateTitle.setText(oldNote.getNotesTitle());
        binding.editTextUpdateSubTitle.setText(oldNote.getNotesSubtitle());
        binding.editTextUpdateNote.setText(oldNote.getNotes());
        updatePriority =oldNote.getNotesPriority();
        id=oldNote.getId();
        binding.UpPriorityGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.UpPriorityGreen.setImageResource(R.drawable.shape_done);
                binding.UpPriorityRed.setImageResource(0);
                binding.UpPriorityYellow.setImageResource(0);
                updatePriority ="2";

            }
        });
        binding.UpPriorityRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.UpPriorityRed.setImageResource(R.drawable.shape_done);
                binding.UpPriorityGreen.setImageResource(0);
                binding.UpPriorityYellow.setImageResource(0);
                updatePriority ="3";

            }
        });
        binding.UpPriorityYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.UpPriorityYellow.setImageResource(R.drawable.shape_done);
                binding.UpPriorityRed.setImageResource(0);
                binding.UpPriorityGreen.setImageResource(0);
                updatePriority ="1";


            }
        });
        
        binding.fabUpdateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTitle =binding.editTextUpdateTitle.getText().toString();
                updateSubTitle =binding.editTextUpdateSubTitle.getText().toString();
                updateNotes =binding.editTextUpdateNote.getText().toString();
                updateDate =getCurrentDate();
                if(oldNote.getNotesTitle().equals(updateTitle)&&oldNote.getNotesSubtitle().equals(updateSubTitle)
                &&oldNote.getNotes().equals(updateNotes)&&oldNote.getNotesPriority().equals(updatePriority)){
                    Toast.makeText(UpdateNoteActivity.this, "Data not modified", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    updateNode(updateTitle, updateSubTitle, updateNotes, updateDate, updatePriority);

                }

            }
        });

    }

    private void updateNode(String title, String subTitle, String notes, String date, String priority) {
        Notes UpdateNote=new Notes(title,subTitle,notes,date,priority);
        UpdateNote.setId(id);
        viewModel.updateNotes(UpdateNote);
        Toast.makeText(this, "update successful", Toast.LENGTH_SHORT).show();
        finish();
    }
    private String getCurrentDate(){
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mDateFormat = new SimpleDateFormat("EEEE hh:mm a ");
        String date = mDateFormat.format(calendar.getTime());
        return date ;
    }


    private void deleteNote(int deleted_id){
        viewModel.deleteNotes(deleted_id);
        Toast.makeText(this, "delete successful", Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.delete_menu)
        {
           showSheetDialog();
        }
        return true;
    }

    private void showSheetDialog() {
        BottomSheetDialog sheetDialog=new BottomSheetDialog(UpdateNoteActivity.this,R.style.CustomBottomSheetDialog);
        View view= LayoutInflater.from(UpdateNoteActivity.this).inflate(R.layout.delete_buttom_sheet,null,false);
        sheetDialog.setContentView(view);
        TextView no,yes;
        no=view.findViewById(R.id.delete_no);
        yes=view.findViewById(R.id.delete_yes);

        sheetDialog.show();
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote(id);
            }
        });
    }
}