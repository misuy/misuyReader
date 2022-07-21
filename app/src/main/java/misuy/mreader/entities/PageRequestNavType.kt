package misuy.mreader.entities

import android.os.Bundle
import android.util.JsonWriter
import androidx.navigation.NavType
import com.google.gson.Gson

class PageRequestNavType: NavType<PageRequest>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PageRequest? {
        return(bundle.getParcelable(key))
    }

    override fun parseValue(value: String): PageRequest {
        return(Gson().fromJson(value, PageRequest::class.java))
    }

    override fun put(bundle: Bundle, key: String, value: PageRequest): Unit {
        bundle.putParcelable(key, value)
    }
}