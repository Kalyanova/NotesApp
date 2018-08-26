package by.paranoidandroid.notesapp.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import by.paranoidandroid.notesapp.database.entities.Note;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note ORDER BY title")
    List<Note> selectAll();

    @Query("SELECT * FROM note WHERE id=:id")
    Note findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Note... notes);

    @Update
    void update(Note... notes);

    @Delete
    void delete(Note notes);

    @Query("DELETE FROM note")
    void deleteAll();

}