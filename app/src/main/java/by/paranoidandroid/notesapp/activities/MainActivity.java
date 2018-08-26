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
    private EditText edTitle, edBody;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 4));

        adapter = new NotesAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.updateList(getNoteList());

        edTitle =  findViewById(R.id.et_title);
        edBody =  findViewById(R.id.et_body);

        Button button = findViewById(R.id.bt_add_note);
        button.setOnClickListener(view -> onAddNoteButtonClick());
    }

    private void onAddNoteButtonClick() {
        AppDatabase.getAppDatabase(this)
                .noteDao()
                .insert(new Note(edTitle.getText().toString(),
                                   edBody.getText().toString()));
        List<Note> updatedList = getNoteList();
        adapter.updateList(updatedList);
    }

    private List<Note> getNoteList() {
        return AppDatabase.getAppDatabase(this).noteDao().selectAll();
    }
}