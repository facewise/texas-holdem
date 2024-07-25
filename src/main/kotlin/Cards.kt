enum class Cards(val suit: Suit, val rank: Rank) {

    SPADE_ACE(Suit.SPADE, Rank.ACE),
    DIAMOND_ACE(Suit.DIAMOND, Rank.ACE),
    HEART_ACE(Suit.HEART, Rank.ACE),
    CLUB_ACE(Suit.CLUB, Rank.ACE),
    SPADE_KING(Suit.SPADE, Rank.KING),
    DIAMOND_KING(Suit.DIAMOND, Rank.KING),
    HEART_KING(Suit.HEART, Rank.KING),
    CLUB_KING(Suit.CLUB, Rank.KING),
    SPADE_QUEEN(Suit.SPADE, Rank.QUEEN),
    DIAMOND_QUEEN(Suit.DIAMOND, Rank.QUEEN),
    HEART_QUEEN(Suit.HEART, Rank.QUEEN),
    CLUB_QUEEN(Suit.CLUB, Rank.QUEEN),
    SPADE_JACK(Suit.SPADE, Rank.JACK),
    DIAMOND_JACK(Suit.DIAMOND, Rank.JACK),
    HEART_JACK(Suit.HEART, Rank.JACK),
    CLUB_JACK(Suit.CLUB, Rank.JACK),
    SPADE_TEN(Suit.SPADE, Rank.TEN),
    DIAMOND_TEN(Suit.DIAMOND, Rank.TEN),
    HEART_TEN(Suit.HEART, Rank.TEN),
    CLUB_TEN(Suit.CLUB, Rank.TEN),
    SPADE_NINE(Suit.SPADE, Rank.NINE),
    DIAMOND_NINE(Suit.DIAMOND, Rank.NINE),
    HEART_NINE(Suit.HEART, Rank.NINE),
    CLUB_NINE(Suit.CLUB, Rank.NINE),
    SPADE_EIGHT(Suit.SPADE, Rank.EIGHT),
    DIAMOND_EIGHT(Suit.DIAMOND, Rank.EIGHT),
    HEART_EIGHT(Suit.HEART, Rank.EIGHT),
    CLUB_EIGHT(Suit.CLUB, Rank.EIGHT),
    SPADE_SEVEN(Suit.SPADE, Rank.SEVEN),
    DIAMOND_SEVEN(Suit.DIAMOND, Rank.SEVEN),
    HEART_SEVEN(Suit.HEART, Rank.SEVEN),
    CLUB_SEVEN(Suit.CLUB, Rank.SEVEN),
    SPADE_SIX(Suit.SPADE, Rank.SIX),
    DIAMOND_SIX(Suit.DIAMOND, Rank.SIX),
    HEART_SIX(Suit.HEART, Rank.SIX),
    CLUB_SIX(Suit.CLUB, Rank.SIX),
    SPADE_FIVE(Suit.SPADE, Rank.FIVE),
    DIAMOND_FIVE(Suit.DIAMOND, Rank.FIVE),
    HEART_FIVE(Suit.HEART, Rank.FIVE),
    CLUB_FIVE(Suit.CLUB, Rank.FIVE),
    SPADE_FOUR(Suit.SPADE, Rank.FOUR),
    DIAMOND_FOUR(Suit.DIAMOND, Rank.FOUR),
    HEART_FOUR(Suit.HEART, Rank.FOUR),
    CLUB_FOUR(Suit.CLUB, Rank.FOUR),
    SPADE_THREE(Suit.SPADE, Rank.THREE),
    DIAMOND_THREE(Suit.DIAMOND, Rank.THREE),
    HEART_THREE(Suit.HEART, Rank.THREE),
    CLUB_THREE(Suit.CLUB, Rank.THREE),
    SPADE_TWO(Suit.SPADE, Rank.TWO),
    DIAMOND_TWO(Suit.DIAMOND, Rank.TWO),
    HEART_TWO(Suit.HEART, Rank.TWO),
    CLUB_TWO(Suit.CLUB, Rank.TWO);

    val label = rank.label + suit.shortName

    fun toCard() = Card(this)

    enum class Suit(val shortName: String) : ExtendedComparable<Suit> {
        SPADE("s"),
        DIAMOND("d"),
        HEART("h"),
        CLUB("c")
    }

    enum class Rank(val label: String) : ExtendedComparable<Rank> {
        ACE("A"),
        KING("K"),
        QUEEN("Q"),
        JACK("J"),
        TEN("10"),
        NINE("9"),
        EIGHT("8"),
        SEVEN("7"),
        SIX("6"),
        FIVE("5"),
        FOUR("4"),
        THREE("3"),
        TWO("2")
    }

}
