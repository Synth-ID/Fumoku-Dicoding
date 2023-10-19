package id.synth.fumoku.data

enum class Price {
    LOW,
    NORMAL,
    HIGH,
    VERY_HIGH,
    ;

    val rating: Int get() = ordinal + 1
}