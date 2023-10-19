package id.synth.fumoku.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fumo(
    val id: UInt,
    val character: String,
    val type: FumoType,
    val name: String,
) : Parcelable {
    val idPadded: String get() = "%03d".format(id)

    @Throws(
        NumberFormatException::class,
    )
    constructor(
        id: String,
        character: String,
        type: FumoType,
        name: String,
    ) : this(id.toUInt(), character, type, name)
}