package misuy.mreader.screens

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var symbolWidth = 0.dp
    var symbolHeight = 0.dp
    Text(text="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя1234567890.,;:(){}[]", softWrap = false, onTextLayout = {
        symbolWidth = it.size.width.dp / 138
        symbolHeight = it.size.height.dp
        Log.e("pizdec", symbolWidth.toString())
    })
}