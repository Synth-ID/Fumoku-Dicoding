package id.synth.fumoku.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.synth.fumoku.model.TouhouCharacter

object TouhouCharacterStore {
    @Suppress("SpellCheckingInspection")
    private val allTouhouCharacterData: Set<TouhouCharacter> = setOf(
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

    private val _touhouCharacterData by lazy {
        MutableLiveData<Set<TouhouCharacter>>().apply {
            value = allTouhouCharacterData
        }
    }
    val touhouCharacter: LiveData<Set<TouhouCharacter>> get() = _touhouCharacterData

    fun getAll() = allTouhouCharacterData

    fun get(name: String) = allTouhouCharacterData.firstOrNull { it.name == name }
}