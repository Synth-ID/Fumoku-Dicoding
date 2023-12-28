package id.synth.fumoku.data

import android.util.Range
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.synth.fumoku.model.*
import id.synth.fumoku.model.Cost.*
import id.synth.fumoku.model.FumoType.*
import id.synth.fumoku.model.Rarity.*

// Data from https://github.com/Ununoctium117/fumosite/blob/master/fumo_data.json

object FumoStore {

    @Suppress("ComplexRedundantLet", "SpellCheckingInspection")
    val allFumoData: Set<FumoPair> = listOf(
        "Hakurei Reimu".let { character ->
            listOf(
                REGULAR.let { type ->
                    listOf(
                        fumo(
                            character,
                            type,
                            "Version 1",
                            "001",
                            img = "https://fumo.website/img/001.jpg",
                            releaseDates = setOf(
                                2008,
                                2009,
                                2010,
                                2011,
                                2012,
                            ),
                            rarity = UNCOMMON,
                            secondhandCost = HIGH,
                            priceRange = 70..120,
                        ),
                        fumo(
                            character,
                            type,
                            "Nendroid Plus",
                            "029",
                            img = "https://fumo.website/img/029.jpg",
                            releaseDates = setOf(
                                2010,
                            ),
                            rarity = RARE,
                            secondhandCost = HIGH,
                            priceRange = 80..120,
                        ),
                        fumo(
                            character,
                            type,
                            "Kourindou Version",
                            "345",
                            img = "https://fumo.website/img/345.jpg",
                            releaseDates = setOf(
                                2015,
                                2016,
                                2017,
                                2018,
                                2020,
                            ),
                            rarity = COMMON,
                            secondhandCost = NORMAL,
                            priceRange = 40..60,
                        ),
                        fumo(
                            character,
                            type,
                            "Version 1.5",
                            "897",
                            img = "https://fumo.website/img/897.jpg",
                            releaseDates = setOf(
                                2022,
                            ),
                            rarity = COMMON,
                            secondhandCost = NORMAL,
                        ),
                    )
                },
                STRAP.let { type ->
                    listOf(
                        fumo(
                            character,
                            type,
                            name = "Nendroid Plus",
                            id = "122",
                            img = "https://fumo.website/img/122.jpg",
                            releaseDates = setOf(
                                2011,
                            ),
                            rarity = RARE,
                            secondhandCost = NORMAL,
                        ),
                        fumo(
                            character,
                            type,
                            name = "Kourindou Version",
                            id = "452",
                            img = "https://fumo.website/img/452.jpg",
                            releaseDates = setOf(
                                2016,
                            ),
                            rarity = RARE,
                            secondhandCost = NORMAL,
                            priceRange = 100..200,
                        ),
                        fumo(
                            character,
                            type,
                            name = "Plush Strap",
                            id = "745",
                            img = "https://fumo.website/img/745.jpg",
                            releaseDates = setOf(
                                2020,
                            ),
                            rarity = COMMON,
                            secondhandCost = LOW,
                            priceRange = 20..20,
                        ),
                    )
                },
                DEKA.let { type ->
                    listOf(
                        fumo(
                            character,
                            type,
                            name = "Version 1",
                            id = "009",
                            img = "https://fumo.website/img/009.jpg",
                            releaseDates = setOf(
                                2009,
                                2010,
                                2011,
                                2012,
                            ),
                            rarity = SUPER_RARE,
                            secondhandCost = HIGH,
                            priceRange = 500..750,
                        ),
                        fumo(
                            character,
                            type,
                            name = "Kourindou Version",
                            id = "739",
                            img = "https://fumo.website/img/739.jpg",
                            releaseDates = setOf(
                                2020,
                            ),
                            rarity = SUPER_RARE,
                            secondhandCost = NORMAL,
                            priceRange = 500..500,
                        ),
                    )
                },
                PUPPET.let { type ->
                    listOf(
                        fumo(
                            character,
                            type,
                            name = "Puppet",
                            id = "021",
                            img = "https://fumo.website/img/021.jpg",
                            releaseDates = setOf(
                                2009,
                            ),
                            rarity = RARE,
                            secondhandCost = HIGH,
                            priceRange = 120..120,
                        ),
                    )
                },
            )
        }.flatten(),
        "Kirisame Marisa".let { character ->
            listOf(REGULAR.let { type ->
                listOf(
                    fumo(
                        character,
                        type,
                        name = "Version 1",
                        id = "002",
                        img = "https://fumo.website/img/002.jpg",
                        releaseDates = setOf(
                            2008, 2009, 2010, 2011, 2012
                        ),
                        rarity = UNCOMMON,
                        secondhandCost = NORMAL,
                    )
                )
            })
        }.flatten(),
        "Cirno".let { character ->
            listOf(REGULAR.let { type ->
                listOf(
                    fumo(
                        character,
                        type,
                        name = "Version 1",
                        id = "014",
                        img = "https://fumo.website/img/014.jpg",
                        releaseDates = setOf(
                            2009, 2010, 2011, 2013
                        ),
                        rarity = COMMON,
                        secondhandCost = VERY_HIGH,
                    )
                )
            })
        }.flatten(),
    ).flatten().toSet()

    private fun productLink(id: String): String {
        return "https://www.gift-gift.jp/nui/nui$id.html"
    }

    private fun fumo(
        character: String,
        type: FumoType,
        name: String,
        id: String,
        img: String? = null,
        releaseDates: Set<Int>? = null,
        rarity: Rarity? = null,
        secondhandCost: Cost? = null,
        priceRange: IntRange? = null,
        link: String? = null
    ): FumoPair {
        return Pair(
            Fumo(
                character,
                type,
                name,
                id,
                img,
            ),
            FumoDetails(
                id,
                releaseYears = releaseDates,
                rarity,
                secondhandCost,
                priceRangeUSD = priceRange?.run { Range(first.toDouble(), last.toDouble()) },
                link = link ?: productLink(id)
            ),
        )
    }

    private val _fumoData by lazy {
        MutableLiveData<Set<FumoPair>>().apply {
            value = allFumoData
        }
    }
    val fumoData: LiveData<Set<FumoPair>> get() = _fumoData

    fun getAll() = allFumoData

    fun get(id: UInt) = allFumoData.firstOrNull { it.first.id == id }

    fun get(id: String) = allFumoData.firstOrNull { it.first.idPadded == id }
}