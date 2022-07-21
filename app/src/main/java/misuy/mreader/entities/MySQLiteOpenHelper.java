package misuy.mreader.entities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "BOOKS";
    public static final String PATH_COLUMN = "PATH";
    public static final String NAME_COLUMN = "NAME";
    public static final String AUTHOR_COLUMN = "AUTHOR";
    public static final String IMAGE_COLUMN = "IMAGE";
    public static final String TOTAL_WORDS_COLUMN = "TOTAL_WORDS";
    public static final String CURRENT_WORD_NUMBER_COLUMN = "CURRENT_WORD_NUMBER";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +  " (" + PATH_COLUMN + " TEXT PRIMARY KEY, " + NAME_COLUMN + " TEXT, " + AUTHOR_COLUMN + " TEXT, " + IMAGE_COLUMN + " BLOB, " + TOTAL_WORDS_COLUMN + " INTEGER, " + CURRENT_WORD_NUMBER_COLUMN + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
