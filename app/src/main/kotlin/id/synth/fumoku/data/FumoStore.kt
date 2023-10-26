package id.synth.fumoku.data

import android.util.Range
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.synth.fumoku.model.Cost
import id.synth.fumoku.model.Cost.*
import id.synth.fumoku.model.Fumo
import id.synth.fumoku.model.FumoDetails
import id.synth.fumoku.model.FumoPair
import id.synth.fumoku.model.FumoType
import id.synth.fumoku.model.FumoType.*
import id.synth.fumoku.model.Rarity
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                            img = null, // TODO
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
                        img = null, // TODO
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
                        img = null, // TODO
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
        @RawRes @DrawableRes img: Int? = null,
        releaseDates: Set<Int>? = null,
        rarity: Rarity? = null,
        secondhandCost: Cost? = null,
        priceRange: IntRange? = null,
        link: String? = null
    ): FumoPair {
        return Pair(
            Fumo(character, type, name, id),
            FumoDetails(
                id,
                image = img,
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