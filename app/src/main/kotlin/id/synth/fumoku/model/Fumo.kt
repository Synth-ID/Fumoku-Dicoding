package id.synth.fumoku.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fumo(
    val character: String,
    val type: FumoType,
    val name: String,
    val id: UInt,
) : Parcelable {
    val idPadded: String get() = "%03d".format(id)

    @Throws(
        NumberFormatException::class,
    )
    constructor(
        character: String,
        type: FumoType,
        name: String,
        id: String,
    ) : this(character, type, name, id.toUInt())

    class DiffCallback : DiffUtil.ItemCallback<Fumo>() {
        override fun areItemsTheSame(oldItem: Fumo, newItem: Fumo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Fumo, newItem: Fumo) =
            oldItem.character == newItem.character
                    && oldItem.type == newItem.type
                    && oldItem.name == newItem.name

    }
}