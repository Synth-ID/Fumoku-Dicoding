package id.synth.fumoku.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fumo(
    val id: Int,
    val character: String,
    val type: FumoType,
    val name: String,
) : Parcelable {
    @Throws(
        NumberFormatException::class,
        IllegalArgumentException::class,
    )
    constructor(
        id: String,
        character: String,
        type: String,
        name: String,
    ) : this(id.toInt(), character, FumoType.valueOf(type.uppercase()), name)
}