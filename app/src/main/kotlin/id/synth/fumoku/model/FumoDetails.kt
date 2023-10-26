package id.synth.fumoku.model

import android.annotation.SuppressLint
import android.util.Range
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.recyclerview.widget.DiffUtil
import java.math.BigDecimal
import java.net.MalformedURLException
import java.net.URL

data class FumoDetails(
    val id: UInt,
    @RawRes @DrawableRes
    val image: Int? = null,
    val releaseYears: Set<Int>? = null,
    val rarity: Rarity? = null,
    val secondhandCost: Cost? = null,
    val priceRangeUSD: Range<BigDecimal>? = null,
    val link: URL? = null,
) {
    val idPadded: String get() = "%03d".format(id)

    @Throws(
        NumberFormatException::class,
        MalformedURLException::class,
    )
    constructor(
        id: String,
        @RawRes @DrawableRes
        image: Int? = null,
        releaseYears: Set<Int>? = null,
        rarity: Rarity? = null,
        secondhandCost: Cost? = null,
        priceRangeUSD: Range<Double>? = null,
        link: String? = null,
    ) : this(
        id.toUInt(),
        image,
        releaseYears,
        rarity,
        secondhandCost,
        priceRangeUSD?.run { Range(lower.toBigDecimal(), upper.toBigDecimal()) },
        URL(link)
    )

    class DiffCallback : DiffUtil.ItemCallback<FumoDetails>() {
        override fun areItemsTheSame(oldItem: FumoDetails, newItem: FumoDetails) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FumoDetails, newItem: FumoDetails) =
            oldItem.image == newItem.image
                    && oldItem.releaseYears == newItem.releaseYears
                    && oldItem.rarity == newItem.rarity
                    && oldItem.secondhandCost == newItem.secondhandCost
                    && oldItem.priceRangeUSD == newItem.priceRangeUSD
                    && oldItem.link?.sameFile(newItem.link) ?: false

    }
}