object BestHandResolver : HandResolver {

    override fun resolve(cards: Array<Card>): Hand {
        if (cards.size != 7) {
            throw IllegalStateException("Wrong number of cards")
        }

        val sortedCards = cards.copyOf()
        sortedCards.sortWith(Card.RANK_COMPARATOR)
        return resolveStraightFlush(sortedCards)
            ?: resolveQuads(sortedCards)
            ?: resolveFullHouse(sortedCards)
            ?: resolveFlush(sortedCards)
            ?: resolveStraight(sortedCards)
            ?: resolveTrips(sortedCards)
            ?: resolveTwoPair(sortedCards)
            ?: resolveOnePair(sortedCards)
            ?: resolveHighCard(sortedCards)
    }

    private fun resolveStraightFlush(cards: Array<Card>): Hand.StraightFlush? {
        val hand = resolveStraight(cards) ?: return null
        if (isFlush(hand.cards))
            return Hand.StraightFlush(hand.cards, hand.rank)
        return null
    }

    private fun resolveQuads(cards: Array<Card>): Hand.Quads? {
        val group = cards.groupBy(Card::rank)
        val highMap = group.filter { it.value.size == 4 }
        for (entry in highMap) {
            val kicker = group.minBy { it.key }.value[0]
            val bestHands = entry.value.plus(kicker).toTypedArray()
            return Hand.Quads(bestHands, entry.key, Kickers(kicker))
        }
        return null
    }

    private fun resolveFullHouse(cards: Array<Card>): Hand.FullHouse? {
        val list = mutableListOf<Card>()
        val group = cards.groupBy(Card::rank)
        val trips = group.filter { it.value.size == 3 }
        val pairs = group.filter { it.value.size == 2 }
        if (trips.isEmpty() || pairs.isEmpty())
            return null
        var firstRank: Cards.Rank? = null
        var secondRank: Cards.Rank? = null
        for (entry in trips) {
            if (firstRank == null) {
                firstRank = entry.key
                list.addAll(entry.value)
            } else {
                secondRank = entry.key
                list.add(entry.value[0])
                list.add(entry.value[1])
            }
        }
        if (secondRank == null) {
            for (entry in pairs) {
                if (secondRank == null) {
                    secondRank = entry.key
                    list.addAll(entry.value)
                }
            }
        }
        if (firstRank == null || secondRank == null) return null
        return Hand.FullHouse(list.toTypedArray(), firstRank, secondRank)
    }

    private fun resolveFlush(cards: Array<Card>): Hand.Flush? {
        val group = cards.groupBy(Card::suit)
        val filter = group.filter { it.value.size == 5 }
        for (entry in filter) {
            val kickers = entry.value.toTypedArray()
            return Hand.Flush(kickers, Kickers(*kickers))
        }
        return null
    }

    private fun resolveStraight(cards: Array<Card>): Hand.Straight? {
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

    private fun resolveTrips(cards: Array<Card>): Hand.Trips? {
        val list = cards.toMutableList()
        val group = cards.groupBy(Card::rank)
        val trips = group.filter { it.value.size == 3 }
        for (entry in trips) {
            list.removeAll(entry.value)
            val kickers = list.take(2).toTypedArray()
            return Hand.Trips(entry.value.plus(kickers).toTypedArray(), entry.key, Kickers(*kickers))
        }
        return null
    }

    private fun resolveTwoPair(cards: Array<Card>): Hand.TwoPair? {
        val originalCards = cards.toMutableList()
        val list = mutableListOf<Card>()
        val group = cards.groupBy(Card::rank)
        val pairs = group.filter { it.value.size == 2 }
        if (pairs.size < 2) return null

        var firstRank: Cards.Rank? = null
        var secondRank: Cards.Rank? = null
        for (entry in pairs) {
            if (firstRank == null) {
                firstRank = entry.key
                originalCards.removeAll(entry.value)
                list.addAll(entry.value)
            } else if (secondRank == null) {
                secondRank = entry.key
                originalCards.removeAll(entry.value)
                list.addAll(entry.value)
            }
        }
        if (firstRank == null || secondRank == null) return null
        list.add(originalCards[0])

        return Hand.TwoPair(list.toTypedArray(), firstRank, secondRank, Kickers(originalCards[0]))
    }

    private fun resolveOnePair(cards: Array<Card>): Hand.OnePair? {
        val originalCards = cards.toMutableList()
        val group = cards.groupBy(Card::rank)
        val pairs = group.filter { it.value.size == 2 }
        if (pairs.isEmpty()) return null
        for (entry in pairs) {
            originalCards.removeAll(entry.value)
            val kickers = originalCards.take(3).toTypedArray()
            return Hand.OnePair(entry.value.plus(kickers).toTypedArray(), entry.key, Kickers(*kickers))
        }

        return null
    }

    private fun resolveHighCard(cards: Array<Card>): Hand.HighCard {
        val kickers = cards.sliceArray(0..5)
        return Hand.HighCard(kickers, Kickers(*kickers))
    }

    private fun isFlush(cards: Array<Card>): Boolean {
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