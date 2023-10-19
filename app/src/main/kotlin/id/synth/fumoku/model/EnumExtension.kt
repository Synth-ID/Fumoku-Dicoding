package id.synth.fumoku.model

internal fun <E : Enum<E>> E.toPrettyString(): String {
    return name
        .split('_')
        .joinToString(" ") {
            it.lowercase().replaceFirstChar(Char::titlecase)
        }
}