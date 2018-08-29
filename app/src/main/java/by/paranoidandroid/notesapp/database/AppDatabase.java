package by.paranoidandroid.notesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import by.paranoidandroid.notesapp.database.dao.NoteDao;
import by.paranoidandroid.notesapp.database.entities.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;
    private static final String DB_NAME = "note.db";

    public abstract NoteDao noteDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME)
                            // Allow queries on the main thread.
                            // Don't do this on a real app!
                            // Only for testing purposes.
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}