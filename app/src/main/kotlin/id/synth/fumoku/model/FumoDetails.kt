package id.synth.fumoku.model

import android.util.Range
import java.math.BigDecimal
import java.net.MalformedURLException
import java.net.URL

data class FumoDetails(
    val id: UInt,
    val releaseYears: Set<Int>? = null,
    val rarity: Rarity? = null,
    val secondhandCost: Cost? = null,
    val priceRangeUSD: Range<BigDecimal>? = null,
    val link: URL? = null,
) {
    @Throws(
        NumberFormatException::class,
        MalformedURLException::class,
    )
    constructor(
        id: String,
        releaseYears: Set<Int>? = null,
        rarity: Rarity? = null,
        secondhandCost: Cost? = null,
        priceRangeUSD: Range<Double>? = null,
        link: String? = null,
    ) : this(
        id.toUInt(),
        releaseYears,
        rarity,
        secondhandCost,
        priceRangeUSD?.run { Range(lower.toBigDecimal(), upper.toBigDecimal()) },
        if (link == null) null else URL(link)
    )
}