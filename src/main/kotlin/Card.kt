class Card(val sign: Cards) {

    companion object {
        val RANK_COMPARATOR = Comparator<Card> { o1, o2 -> o1.rank.compareTo(o2.rank) }
    }

    val suit = sign.suit
    val rank = sign.rank
    var open: Boolean = false

    fun open() {
        open = true
    }

    override fun toString(): String {
        return "Card(cards=$sign, suit=$suit, rank=$rank, open=$open)"
    }

}
