package by.paranoidandroid.notesapp.database.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "note", indices = {@Index(value = "title", unique = true)})
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String body;

    @Ignore
    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Note(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
