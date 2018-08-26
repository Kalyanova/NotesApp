package by.paranoidandroid.notesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import by.paranoidandroid.notesapp.R;
import by.paranoidandroid.notesapp.database.AppDatabase;
import by.paranoidandroid.notesapp.database.entities.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Note> notes;
    private LayoutInflater inflater;
    private Context context;

    public NotesAdapter(Context ctx) {
        context = ctx;
        notes = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(
                inflater.inflate(R.layout.list_item_note, parent, false));
        holder.itemView.setOnClickListener(view -> {
            int position = holder.getAdapterPosition();
            clickListener.onItemClick(view, context, position);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvBody.setText(note.getBody());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateList(List<Note> updatedList) {
        notes = updatedList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvBody = itemView.findViewById(R.id.tv_body);
        }
    }

    private ItemClickListener clickListener = (view, context, position) -> {
        Note note = notes.get(position);
        AppDatabase.getAppDatabase(context).noteDao().delete(note);
        notes.remove(note);
        notifyItemRemoved(position);
    };

    interface ItemClickListener {
        void onItemClick(View view, Context context, int position);
    }
}
