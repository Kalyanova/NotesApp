package by.paranoidandroid.notesapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import by.paranoidandroid.notesapp.database.AppDatabase;
import by.paranoidandroid.notesapp.database.entities.Note;

public class NoteViewModel extends AndroidViewModel {
    private MutableLiveData<List<Note>> notes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Note>> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
            loadNotes();
        }
        return notes;
    }

    private void loadNotes() {
        // Doing an asynchronous operation (query to database) in background to fetch notes.
        new DBAsyncTask(AppDatabase.getAppDatabase(getApplication()), notes, Action.Loading).execute();
    }

    public void insertNote(String title, String body) {
        Note note = new Note(title, body);
        new DBAsyncTask(AppDatabase.getAppDatabase(getApplication()), notes, Action.Inserting)
                .execute(note);
    }

    public void deleteNote(Note note) {
        new DBAsyncTask(AppDatabase.getAppDatabase(getApplication()), notes, Action.Deleting)
                .execute(note);
    }

    /**
     * AsyncTask that executes asynchronous operations (queries to database) in background.
     */
    private static class DBAsyncTask extends AsyncTask<Note, Void, List<Note>> {
        private AppDatabase db;
        private MutableLiveData<List<Note>> notes;
        private Action action;

        DBAsyncTask(AppDatabase appDatabase, MutableLiveData<List<Note>> liveData, Action act) {
            db = appDatabase;
            notes = liveData;
            action = act;
        }

        @Override
        protected List<Note> doInBackground(Note... notes) {
            switch (action) {
                case Loading:
                    break;
                case Inserting:
                    db.noteDao().insert(notes[0]);
                    break;
                case Deleting:
                    db.noteDao().delete(notes[0]);
                    break;
            }
            return db.noteDao().selectAll();
        }

        @Override
        protected void onPostExecute(List<Note> newData) {
            super.onPostExecute(newData);
            notes.setValue(newData);
        }
    }

    enum Action {
        Loading,
        Inserting,
        Deleting
    }
}