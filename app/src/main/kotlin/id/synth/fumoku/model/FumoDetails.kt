package id.synth.fumoku.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import java.net.MalformedURLException
import java.net.URL

data class FumoDetails(
    val id: Int,
    @RawRes @DrawableRes
    val image: Int? = null,
    val releaseYears: IntRange? = null,
    val rarity: Rarity? = null,
    val secondhandCost: Cost? = null,
    val priceRangeUSD: ULongRange? = null,
    val link: URL? = null,
) {
    @Throws(
        NumberFormatException::class,
        MalformedURLException::class,
    )
    constructor(
        id: String,
        @RawRes @DrawableRes
        image: Int? = null,
        releaseYears: IntRange? = null,
        rarity: Rarity? = null,
        secondhandCost: Cost? = null,
        priceRangeUSD: ULongRange? = null,
        link: String? = null,
    ) : this(id.toInt(), image, releaseYears, rarity, secondhandCost, priceRangeUSD, URL(link))
}