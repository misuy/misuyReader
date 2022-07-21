package misuy.mreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import misuy.mreader.entities.PageRequest
import misuy.mreader.entities.PageRequestNavType
import misuy.mreader.screens.BooksListScreen
import misuy.mreader.screens.PageScreen
import misuy.mreader.screens.SettingsScreen
import misuy.mreader.screens.test


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "BooksListScreen") {
        composable("BooksListScreen") { BooksListScreen() }
        composable("PageScreen/{PageRequest}", arguments = listOf(navArgument("PageRequest") { type = PageRequestNavType() })) {
            val pageRequest = it.arguments!!.getParcelable<PageRequest>("PageRequest")
            PageScreen(book = pageRequest!!.book, pageNumber = pageRequest.pageNumber)
        }
        composable("settings") { SettingsScreen() }
    }
}

