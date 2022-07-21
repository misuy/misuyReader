package misuy.mreader.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PageRequest(val pageNumber: Int, val book: @RawValue Book): Parcelable