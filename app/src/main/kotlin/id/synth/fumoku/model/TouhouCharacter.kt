package id.synth.fumoku.model

import androidx.annotation.ColorInt
import java.net.MalformedURLException
import java.net.URL

data class TouhouCharacter(
    val name: String,
    val wiki: URL,
    @ColorInt val color: UInt
) {
    @Throws(
        MalformedURLException::class,
    )
    constructor(
        name: String,
        wiki: String,
        @ColorInt color: UInt
    ) : this(name, URL(wiki), color)
}
