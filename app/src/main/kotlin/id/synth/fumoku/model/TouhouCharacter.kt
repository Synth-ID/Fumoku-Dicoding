package id.synth.fumoku.model

import androidx.annotation.ColorInt
import androidx.recyclerview.widget.DiffUtil
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

    class DiffCallback : DiffUtil.ItemCallback<TouhouCharacter>() {
        override fun areItemsTheSame(oldItem: TouhouCharacter, newItem: TouhouCharacter) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: TouhouCharacter, newItem: TouhouCharacter) =
            oldItem.wiki.sameFile(newItem.wiki)
                    && oldItem.color == newItem.color

    }
}
