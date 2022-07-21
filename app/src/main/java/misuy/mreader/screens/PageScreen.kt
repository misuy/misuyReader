package misuy.mreader.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import misuy.mreader.entities.Book
import misuy.mreader.entities.Line

@Composable
fun PageScreen(book: Book, pageNumber: Int) {
    var symbolWidth = 0
    var symbolHeight = 0
    Text(text="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя1234567890.,;:(){}[]", softWrap = false, onTextLayout = {
        symbolWidth = it.size.width / 138
        symbolHeight = it.size.height
        Log.e("pizdec", it.size.height.toString())
    })

    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx().toInt() }
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx().toInt() }

    val lines: List<Line>? = book.getPage(pageNumber, 10.sp, symbolWidth, screenWidth, symbolHeight, screenHeight)
    Box(modifier = Modifier
        .fillMaxHeight(1f)
        .fillMaxWidth(1f)) {
        if (lines == null) {
            Text("NOTHING")
        }
        else {
            Column() {
                for (i in lines.indices) {
                    Text(lines[i].data)
                }
            }
        }
    }
}