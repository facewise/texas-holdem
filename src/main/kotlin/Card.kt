class Card(val cards: Cards) {

    companion object {
        val RANK_COMPARATOR = Comparator<Card> { o1, o2 -> o1.rank.compareTo(o2.rank) }
    }

    val suit = cards.suit
    val rank = cards.rank
    var open: Boolean = false

    fun open() {
        open = true
    }
}
