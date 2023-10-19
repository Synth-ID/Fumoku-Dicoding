package id.synth.fumoku.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fumo(
    val id: Int,
    val character: String,
    val type: FumoType,
    val name: String,
) : Parcelable