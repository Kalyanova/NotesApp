package by.paranoidandroid.notesapp.utils;

import by.paranoidandroid.notesapp.database.entities.Note;

@FunctionalInterface
public interface RemoveItemListener {
    void removeItem(Note note);
}