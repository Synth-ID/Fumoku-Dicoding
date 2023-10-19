package id.synth.fumoku.model

import android.os.Parcelable
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
}