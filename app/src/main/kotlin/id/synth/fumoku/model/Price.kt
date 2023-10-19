package id.synth.fumoku.model

enum class Price {
    LOW,
    NORMAL,
    HIGH,
    VERY_HIGH,
    ;

    val rating: Int get() = ordinal + 1

    override fun toString(): String {
        return this.toPrettyString()
    }
}