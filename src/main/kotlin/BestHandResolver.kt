class BestHandResolver {
    companion object {
        fun resolve(vararg cards: Card): Hand {
            if (cards.size != 7) {
                throw IllegalStateException("Wrong number of cards")
            }

            val sortedCards = cards.sortedArrayWith(Card.RANK_COMPARATOR)
            return resolveStraightFlush(*sortedCards)
        }

        private fun resolveStraightFlush(vararg cards: Card): Hand.StraightFlush? {
            var hand = resolveStraight(*cards) ?: return null
            if (isFlush(*hand.cards))
                return Hand.StraightFlush(hand.cards, hand.rank)
        }

        private fun resolveQuads(vararg cards: Card): Hand.Quads? {
            val highMap = cards.groupBy(Card::rank).filter { it.value.size == 4 }
            for (entry in highMap) {
                return Hand.Quads(arrayOf(*cards), entry.key, Kickers())
            }
            return null
        }

        private fun resolveStraight(vararg cards: Card): Hand.Straight? {
            if (isStraight(cards[0], cards[1], cards[2], cards[3], cards[4])) {
                return Hand.Straight(arrayOf(cards[0], cards[1], cards[2], cards[3], cards[4]), cards[0].rank)
            }
            if (isStraight(cards[1], cards[2], cards[3], cards[4], cards[5])) {
                return Hand.Straight(arrayOf(cards[1], cards[2], cards[3], cards[4], cards[5]), cards[1].rank)
            }
            if (isStraight(cards[2], cards[3], cards[4], cards[5], cards[6])) {
                return Hand.Straight(arrayOf(cards[2], cards[3], cards[4], cards[5], cards[6]), cards[2].rank)
            }
            return null
        }

        private fun isFlush(vararg cards: Card): Boolean {
            if (cards.size != 5) {
                throw IllegalStateException("Wrong number of cards")
            }
            val suit = cards[0].suit
            return cards.all { it.suit == suit }
        }

        private fun isStraight(vararg cards: Card): Boolean {
            if (cards.size != 5) {
                throw IllegalStateException("Wrong number of cards")
            }
            for (i in 0..3) {
                if (cards[i].rank.ordinal - cards[i + 1].rank.ordinal != -1) {
                    return false
                }
            }
            return true
        }
    }
}