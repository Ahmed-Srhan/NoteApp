package com.example.noteapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.databinding.CustomNoteBinding;
import com.example.noteapp.models.Notes;

import java.util.ArrayList;
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private ArrayList<Notes> notes = new ArrayList<>();
    OnNoteViewListener listener;

    public void setNotes(ArrayList<Notes> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
    public void searchNotes(ArrayList<Notes> filterNotes){
        this.notes=filterNotes;
        notifyDataSetChanged();

    }

    public NoteAdapter(OnNoteViewListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.fillDate(note);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        CustomNoteBinding binding;
        Notes clickedNote;

        public NoteViewHolder(@NonNull CustomNoteBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onViewClickListener(clickedNote);
                }
            });
        }

        public void fillDate(Notes note){
            clickedNote=note;
            binding.textViewDate.setText(note.getNotesDate());
            binding.textViewTitle.setText(note.getNotesTitle());
            binding.textViewSubTitle.setText(note.getNotesSubtitle());
            switch (note.getNotesPriority()) {
                case "1":
                    binding.priority.setBackgroundResource(R.drawable.yellow_shape);
                    break;
                case "2":
                   binding.priority.setBackgroundResource(R.drawable.green_shape);
                    break;
                case "3":
                   binding.priority.setBackgroundResource(R.drawable.red_shape);
                    break;
                default:
                    break;
            }

        }
    }
}
