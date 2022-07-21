package misuy.mreader.entities

import android.util.Log

class BookInfo {
    var name: String
    var path: String
    var authors: String
    var image: String
    var currentWord: Int
    var totalWords: Int

    constructor(name: String, path: String, authors: String, image: String, totalWords: Int, currentWord: Int) {
        Log.e("pizdec", image.length.toString())
        this.name = name
        this.path = path
        this.authors = authors
        this.image = image
        this.totalWords = totalWords
        this.currentWord = currentWord
    }
}