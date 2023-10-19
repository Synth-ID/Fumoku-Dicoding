package id.synth.fumoku.data

enum class Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    SUPER_RARE,
    ;

    val rating: Int get() = ordinal + 1
}