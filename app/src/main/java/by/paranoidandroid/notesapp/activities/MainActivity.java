package by.paranoidandroid.notesapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import by.paranoidandroid.notesapp.R;
import by.paranoidandroid.notesapp.adapters.NotesAdapter;
import by.paranoidandroid.notesapp.database.entities.Note;
import by.paranoidandroid.notesapp.utils.DividerItemDecoration;
import by.paranoidandroid.notesapp.utils.RemoveItemListener;
import by.paranoidandroid.notesapp.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity implements RemoveItemListener {
    private EditText etTitle, etBody;
    private NotesAdapter adapter;
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int DP_MARGIN = 4;
        RecyclerView recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DP_MARGIN));

        adapter = new NotesAdapter(this, this);
        recyclerView.setAdapter(adapter);

        etTitle =  findViewById(R.id.et_title);
        etBody =  findViewById(R.id.et_body);

        Button button = findViewById(R.id.bt_add_note);
        button.setOnClickListener(view -> onAddNoteButtonClick());

        viewModel = new NoteViewModel(getApplication());
        viewModel.getNotes().observe(this, notes -> adapter.updateList(notes));
    }

    private void onAddNoteButtonClick() {
        viewModel.insertNote(etTitle.getText().toString(), etBody.getText().toString());
    }

    @Override
    public void removeItem(Note note) {
        viewModel.deleteNote(note);
    }
}