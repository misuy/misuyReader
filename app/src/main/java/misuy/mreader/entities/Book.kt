package misuy.mreader.entities

import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.kursx.parser.fb2.*
import kotlinx.android.parcel.Parcelize
import java.io.File
import kotlin.math.floor

class Book {
    var path: String
    var name: String
    var author: String
    var image: String
    var totalWords: Int
    var currentWord: Int
    var data: FictionBook? = null
    private var linesList: LinesList? = null

    constructor(path: String, name: String, author: String, image: String, totalWords: Int, currentWord: Int)
    {
        this.path = path
        this.name = name
        this.author = author
        this.image = image
        this.totalWords = totalWords
        this.currentWord = currentWord
    }


    fun load() {
        if (this.data == null) {
            this.data = FictionBook(File(this.path))
        }
    }


    fun getPage(pageNumber: Int, fontSize: TextUnit, symbolWidth: Int, containerWidth: Int, symbolHeight: Int, containerHeight: Int): List<Line>? {
        Log.e("sdf", symbolHeight.toString())
        this.load()

        if (this.linesList == null) {
            this.linesList = LinesList(this.data!!, fontSize, symbolWidth, containerWidth)
        }
        else {
            if (this.linesList!!.fontSize != fontSize) {
                this.linesList = LinesList(this.data!!, fontSize, symbolWidth, containerWidth)
            }
        }

        val linesCount = containerHeight / symbolHeight
        val startLineNumber = linesCount * pageNumber
        val endLineNumber = linesCount * (pageNumber + 1)
        if (startLineNumber < this.linesList!!.lines.size) {
            if (endLineNumber < this.linesList!!.lines.size) {
                return(this.linesList!!.lines.subList(startLineNumber, endLineNumber))
            }
            else return(this.linesList!!.lines.subList(startLineNumber, this.linesList!!.lines.size))
        }
        else return(null)
    }
}