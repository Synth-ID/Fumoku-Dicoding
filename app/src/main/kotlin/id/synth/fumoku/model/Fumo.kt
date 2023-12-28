package id.synth.fumoku.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize
import java.net.URL

@Parcelize
data class Fumo(
    val character: String,
    val type: FumoType,
    val name: String,
    val id: UInt,
    val image: URL?,
) : Parcelable {
    val idPadded: String get() = "%03d".format(id.toInt())

    @Throws(
        NumberFormatException::class,
    )
    constructor(
        character: String,
        type: FumoType,
        name: String,
        id: String,
        image: String? = null,
    ) : this(
        character,
        type,
        name,
        id.toUInt(),
        if (image == null) null else URL(image),
    )

    class DiffCallback(private val imageDiff: Boolean = false) : DiffUtil.ItemCallback<Fumo>() {
        override fun areItemsTheSame(oldItem: Fumo, newItem: Fumo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Fumo, newItem: Fumo) =
            oldItem.character == newItem.character
                    && oldItem.type == newItem.type
                    && oldItem.name == newItem.name
                    && (!imageDiff || oldItem.image == newItem.image)
    }
}