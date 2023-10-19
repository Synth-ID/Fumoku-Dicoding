package id.synth.fumoku.model

enum class FumoType {
    REGULAR,
    STRAP,
    DEKA,
    PUPPET
    ;

    override fun toString(): String {
        return this.toPrettyString()
    }
}