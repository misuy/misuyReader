package misuy.mreader.screens

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import misuy.mreader.entities.Book
import misuy.mreader.entities.BookInfo
import misuy.mreader.entities.BooksRepository
import java.io.ByteArrayInputStream
import java.io.File

@Composable
fun BooksListScreen() {
    val booksRepository = BooksRepository(LocalContext.current)
    var books by remember { mutableStateOf<List<Book>>(booksRepository.getAllBooks()) }
    val coroutineScope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            coroutineScope.launch {
                booksRepository.addBook(it)
            }
        }
        books = booksRepository.getAllBooks()
    }


    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .padding(screenWidth / 20), contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = {
                launcher.launch("application/*")
            },
            shape = CircleShape,
            modifier = Modifier.size(width = screenWidth / 7, height = screenWidth / 7)
        ) {
            Icon(Icons.Filled.Add, "add")
        }
    }


    Column() {
        for (i in 0..books.size - 1) {
            BookBox(book =  books[i])
        }
    }
}


@Composable
fun test() {
    val booksRepository = BooksRepository(LocalContext.current)
    var books by remember { mutableStateOf<List<Book>>(booksRepository.getAllBooks()) }
    PageScreen(book = books[0], pageNumber = 1)
}



@Composable
fun BookBox(book: Book) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = Modifier
            .size(width = screenWidth / 2, height = screenWidth / 2)
            .padding(screenWidth / 40), contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            elevation = 1.dp,
        ) {
            Column() {
                Text(text = book.name, fontWeight = FontWeight.W700)
                Text(text = book.author)
                val decodedImage = Base64.decode(book.image, 0)
                Image(BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size).asImageBitmap(), contentDescription = "book cover")
            }
        }
    }
}
