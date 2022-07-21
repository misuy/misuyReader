package misuy.mreader.entities

import android.util.Log
import com.kursx.parser.fb2.FictionBook
import com.kursx.parser.fb2.Person
import java.io.File

class BookLoader {
    private var book: FictionBook

    constructor(bookPath: String)
    {
        book = FictionBook(File(bookPath))
    }

    fun getName(): String {
        return(book.title)
    }

    fun getAuthors(): List<String> {
        var authors = mutableListOf<String>()
        book.authors.forEach(fun (person: Person) {
            authors.add(person.firstName)
        })
        return(authors)
    }

    fun getImage(): String? {
        Log.e("pizdec", book.binaries.size.toString())
        return(book.binaries["cover.jpg"]?.binary)
    }
}