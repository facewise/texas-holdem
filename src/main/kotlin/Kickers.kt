class Kickers(vararg cards: Card) : ExtendedComparable<Kickers> {

    val cards = cards.sortedArrayWith(Card.RANK_COMPARATOR)

    override fun compareTo(other: Kickers): Int {
        if (cards.size != other.cards.size) {
            throw IllegalStateException("Size of kickers are not equal")
        }

        if (this == other)
            return 0

        for (i in cards.indices) {
            if (cards[i].rank.isEquivalent(other.cards[i].rank)) {
                continue
            }
            return cards[i].rank.compareTo(other.cards[i].rank)
        }
        return 0
    }
}