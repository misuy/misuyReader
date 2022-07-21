package misuy.mreader.entities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.file.Files
import java.security.MessageDigest
import java.util.*
import kotlin.collections.HashMap
import kotlin.io.path.createDirectory

class BooksRepository {
    private var fileDir: File
    private var context: Context
    private var dbHelper: MySQLiteOpenHelper

    constructor(context_: Context) {
        context = context_
        val path = context.applicationInfo.dataDir + "/books"
        fileDir = File(path)
        if (!File(path).exists()) {
            fileDir.mkdir()
        }

        dbHelper = MySQLiteOpenHelper(context, "BOOKS", null, 1)
    }

    fun getAllBooks(): List<Book> {
        var books = mutableListOf<Book>()
        val cursor = dbHelper.readableDatabase.query("BOOKS", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            books.add(Book(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)))
        }
        return(books)
    }

    suspend fun addBook(uri: Uri) {
        val splittedUri = uri.path?.split("/")
        val bookName = splittedUri?.get(splittedUri.size-1)
        val bookPath = fileDir.path + "/" + bookName
        if (!isBookExist(bookPath)) {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(File(bookPath))
            var data = -1;
            if (inputStream != null) {
                while (true) {
                    data = inputStream.read()
                    if (data == -1) break
                    else outputStream.write(data)
                }
            }

            var bookLoader = BookLoader(bookPath)
            var contentValues = ContentValues()
            contentValues.put("PATH", bookPath)
            contentValues.put("NAME", bookLoader.getName())
            var authorsString = ""
            bookLoader.getAuthors().forEach(fun(author: String) {
                authorsString += "$author; "
            })
            contentValues.put("AUTHOR", authorsString)
            contentValues.put("IMAGE", bookLoader.getImage())
            contentValues.put("TOTAL_WORDS", 1)
            contentValues.put("CURRENT_WORD_NUMBER", 0)
            dbHelper.writableDatabase.insert("BOOKS", "PATH", contentValues)
        }
    }

    private fun isBookExist(bookPath: String): Boolean {
        val cursor = dbHelper.readableDatabase.query("BOOKS", null, "PATH = ?", arrayOf(bookPath), null, null, null)
        return(cursor.count > 0)
    }
}