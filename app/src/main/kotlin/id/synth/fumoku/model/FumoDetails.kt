package id.synth.fumoku.model

import android.util.Range
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import java.time.Year

data class FumoDetails(
    val id: Int,
    @RawRes @DrawableRes
    val image: Int?,
    val releaseYears: Range<Year>?,
    val rarity: Rarity?,
    val price: Price?,
)
