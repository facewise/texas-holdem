open class Hand(val hands: Hands, val cards: Array<out Card>) {

    enum class Hands {
        STRAIGHT_FLUSH,
        QUADS,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        TRIPS,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD
    }

    class StraightFlush(cards: Array<out Card>, val rank: Cards.Rank) : Hand(Hands.STRAIGHT_FLUSH, cards),
        ExtendedComparable<StraightFlush> {
        override fun compareTo(other: StraightFlush) = rank.compareTo(other.rank)
    }

    class Quads(cards: Array<out Card>, val rank: Cards.Rank, val kickers: Kickers) : Hand(Hands.QUADS, cards),
        ExtendedComparable<Quads> {
        override fun compareTo(other: Quads) =
            if (rank == other.rank) kickers.compareTo(other.kickers)
            else rank.compareTo(other.rank)
    }

    class FullHouse(cards: Array<out Card>, val firstRank: Cards.Rank, val secondRank: Cards.Rank) :
        Hand(Hands.FULL_HOUSE, cards),
        ExtendedComparable<FullHouse> {
        override fun compareTo(other: FullHouse) =
            if (firstRank == other.firstRank) secondRank.compareTo(other.secondRank)
            else firstRank.compareTo(other.firstRank)
    }

    class Flush(cards: Array<out Card>, val kickers: Kickers) : Hand(Hands.FLUSH, cards), ExtendedComparable<Flush> {
        override fun compareTo(other: Flush) = kickers.compareTo(other.kickers)
    }

    class Straight(cards: Array<out Card>, val rank: Cards.Rank) : Hand(Hands.STRAIGHT, cards),
        ExtendedComparable<Straight> {
        override fun compareTo(other: Straight) = rank.compareTo(other.rank)
    }

    class Trips(cards: Array<out Card>, val rank: Cards.Rank, val kickers: Kickers) : Hand(Hands.TRIPS, cards),
        ExtendedComparable<Trips> {
        override fun compareTo(other: Trips) =
            if (rank == other.rank) kickers.compareTo(other.kickers)
            else rank.compareTo(other.rank)
    }

    class TwoPair(cards: Array<out Card>, val firstRank: Cards.Rank, val secondRank: Cards.Rank, val kickers: Kickers) :
        Hand(Hands.TWO_PAIR, cards),
        ExtendedComparable<TwoPair> {
        override fun compareTo(other: TwoPair): Int {
            if (firstRank == other.firstRank) {
                if (secondRank == other.secondRank) {
                    return kickers.compareTo(other.kickers)
                }
                return secondRank.compareTo(other.secondRank)
            }
            return firstRank.compareTo(other.firstRank)
        }
    }

    class OnePair(cards: Array<out Card>, val rank: Cards.Rank, val kickers: Kickers) : Hand(Hands.ONE_PAIR, cards),
        ExtendedComparable<OnePair> {
        override fun compareTo(other: OnePair) =
            if (rank == other.rank) kickers.compareTo(other.kickers)
            else rank.compareTo(other.rank)
    }

    class HighCard(cards: Array<out Card>, val kickers: Kickers) : Hand(Hands.HIGH_CARD, cards),
        ExtendedComparable<HighCard> {
        override fun compareTo(other: HighCard) = kickers.compareTo(other.kickers)
    }

    override fun toString(): String {
        return "Hand(hands=$hands, cards=${cards.contentToString()})"
    }

}