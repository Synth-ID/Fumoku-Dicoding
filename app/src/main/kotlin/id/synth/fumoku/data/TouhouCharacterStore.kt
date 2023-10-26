package id.synth.fumoku.data

import id.synth.fumoku.model.TouhouCharacter

@Suppress("SpellCheckingInspection")
private val touhouCharacterData: Set<TouhouCharacter> = setOf(
    TouhouCharacter(
        "Hakurei Reimu",
        "https://en.touhouwiki.net/wiki/Reimu_Hakurei",
        0xFFff0000U,
    ),
    TouhouCharacter(
        "Kirisame Marisa",
        "https://en.touhouwiki.net/wiki/Marisa_Kirisame",
        0xFFffd700U,
    ),
    TouhouCharacter(
        "Cirno",
        "https://en.touhouwiki.net/wiki/Cirno",
        0xFF0065ccU,
    ),
)

val touhouCharacterDataMapped: Map<String, TouhouCharacter> =
    touhouCharacterData.associateBy { it.name }