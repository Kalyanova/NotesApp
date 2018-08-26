package by.paranoidandroid.notesapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import by.paranoidandroid.notesapp.R;
import by.paranoidandroid.notesapp.adapters.NotesAdapter;
import by.paranoidandroid.notesapp.database.AppDatabase;
import by.paranoidandroid.notesapp.database.entities.Note;
import by.paranoidandroid.notesapp.utils.DividerItemDecoration;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etBody;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int DP_MARGIN = 4;
        RecyclerView recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DP_MARGIN));

        adapter = new NotesAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.updateList(getNoteList());

        etTitle =  findViewById(R.id.et_title);
        etBody =  findViewById(R.id.et_body);

        Button button = findViewById(R.id.bt_add_note);
        button.setOnClickListener(view -> onAddNoteButtonClick());
    }

    private void onAddNoteButtonClick() {
        AppDatabase.getAppDatabase(this)
                .noteDao()
                .insert(new Note(etTitle.getText().toString(),
                                   etBody.getText().toString()));
        List<Note> updatedList = getNoteList();
        adapter.updateList(updatedList);
    }

    private List<Note> getNoteList() {
        return AppDatabase.getAppDatabase(this).noteDao().selectAll();
    }
}