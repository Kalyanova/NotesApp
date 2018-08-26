Notes app with one Activity,
that contains two EditTexts (note title and body), one Button and RecyclerView.

Button click saves the notes to the database
and RecyclerView show old notes from database and new created one.
Click on note will remove it from database (and from RecyclerView accordingly).

For work with SQLite database this app uses Room persistence library,
that provides an abstraction layer over SQLite to allow fluent database access
while harnessing the full power of SQLite.

The queries to database is performed in background thread.
To achive that and correctly update UI, the app uses ViewModel and LiveData.