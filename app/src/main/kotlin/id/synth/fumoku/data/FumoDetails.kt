package id.synth.fumoku.data

import android.util.Range
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import java.time.Year

data class FumoDetails(
    @RawRes @DrawableRes
    val image: Int?,
    val releaseYears: Range<Year>?,
    val rarity: Rarity?,
    val price: Price?,
)
