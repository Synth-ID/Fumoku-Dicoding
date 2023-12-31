package id.synth.fumoku.model

enum class Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    SUPER_RARE,
    ;

    val rating: Int get() = ordinal + 1

    override fun toString(): String {
        return this.toPrettyString()
    }
}