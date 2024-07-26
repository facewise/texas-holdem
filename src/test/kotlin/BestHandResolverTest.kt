import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BestHandResolverTest {

    private fun get(vararg cards: Cards) = cards.map(Cards::toCard).toTypedArray()

    @Test
    fun testStraightFlush() {
        val arr = get(
            Cards.HEART_JACK,
            Cards.HEART_KING,
            Cards.HEART_QUEEN,
            Cards.HEART_TEN,
            Cards.DIAMOND_SIX,
            Cards.SPADE_NINE,
            Cards.HEART_ACE,
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.StraightFlush::class.java)
        assertThat((resolve as Hand.StraightFlush).rank).isEqualTo(Cards.Rank.ACE)
    }

    @Test
    fun testQuads() {
        val arr = get(
            Cards.HEART_JACK,
            Cards.HEART_KING,
            Cards.CLUB_NINE,
            Cards.DIAMOND_NINE,
            Cards.SPADE_NINE,
            Cards.DIAMOND_QUEEN,
            Cards.SPADE_NINE,
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.Quads::class.java)
        assertThat((resolve as Hand.Quads).rank).isEqualTo(Cards.Rank.NINE)
        assertThat(resolve.kickers.cards)
            .hasSize(1)
            .allMatch { it.sign == Cards.HEART_KING }
    }

    @Test
    fun testFullHouse() {
        val arr = get(
            Cards.HEART_JACK,
            Cards.HEART_QUEEN,
            Cards.CLUB_NINE,
            Cards.DIAMOND_NINE,
            Cards.SPADE_NINE,
            Cards.DIAMOND_QUEEN,
            Cards.SPADE_THREE,
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.FullHouse::class.java)
        assertThat((resolve as Hand.FullHouse).firstRank).isEqualTo(Cards.Rank.NINE)
        assertThat(resolve.secondRank).isEqualTo(Cards.Rank.QUEEN)
    }

    @Test
    fun testFlush() {
        val arr = get(
            Cards.HEART_TWO,
            Cards.HEART_EIGHT,
            Cards.HEART_QUEEN,
            Cards.HEART_TEN,
            Cards.DIAMOND_SIX,
            Cards.SPADE_NINE,
            Cards.HEART_SEVEN,
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.Flush::class.java)
        assertThat((resolve as Hand.Flush).kickers.cards)
            .hasSize(5)
            .allMatch { it.suit == Cards.Suit.HEART }
    }

    @Test
    fun testStraight() {
        val arr = get(
            Cards.DIAMOND_JACK,
            Cards.HEART_KING,
            Cards.HEART_QUEEN,
            Cards.HEART_TEN,
            Cards.DIAMOND_SIX,
            Cards.SPADE_NINE,
            Cards.CLUB_SEVEN,
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.Straight::class.java)
        assertThat((resolve as Hand.Straight).rank).isEqualTo(Cards.Rank.KING)
    }

    @Test
    fun testTrips() {
        val arr = get(
            Cards.DIAMOND_JACK,
            Cards.HEART_KING,
            Cards.SPADE_JACK,
            Cards.SPADE_NINE,
            Cards.CLUB_SIX,
            Cards.CLUB_JACK,
            Cards.DIAMOND_FIVE
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.Trips::class.java)
        assertThat((resolve as Hand.Trips).rank).isEqualTo(Cards.Rank.JACK)
        assertThat(resolve.kickers.cards.map(Card::sign))
            .hasSize(2)
            .element(0).isEqualTo(Cards.HEART_KING)
    }

    @Test
    fun testTwoPair() {
        val arr = get(
            Cards.DIAMOND_JACK,
            Cards.HEART_KING,
            Cards.SPADE_JACK,
            Cards.SPADE_NINE,
            Cards.CLUB_SIX,
            Cards.CLUB_NINE,
            Cards.DIAMOND_FIVE
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.TwoPair::class.java)
        assertThat((resolve as Hand.TwoPair).firstRank).isEqualTo(Cards.Rank.JACK)
        assertThat(resolve.secondRank).isEqualTo(Cards.Rank.NINE)
        assertThat(resolve.kickers.cards[0].sign).isEqualTo(Cards.HEART_KING)
    }

    @Test
    fun testOnePair() {
        val arr = get(
            Cards.DIAMOND_TEN,
            Cards.HEART_KING,
            Cards.SPADE_JACK,
            Cards.SPADE_NINE,
            Cards.CLUB_SIX,
            Cards.CLUB_NINE,
            Cards.DIAMOND_FIVE
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.OnePair::class.java)
        assertThat((resolve as Hand.OnePair).rank).isEqualTo(Cards.Rank.NINE)
        assertThat(resolve.kickers.cards[0].sign).isEqualTo(Cards.HEART_KING)
        assertThat(resolve.kickers.cards[1].sign).isEqualTo(Cards.SPADE_JACK)
        assertThat(resolve.kickers.cards[2].sign).isEqualTo(Cards.DIAMOND_TEN)
    }

    @Test
    fun testHighCard() {
        val arr = get(
            Cards.DIAMOND_TEN,
            Cards.HEART_QUEEN,
            Cards.SPADE_JACK,
            Cards.SPADE_TWO,
            Cards.CLUB_SIX,
            Cards.CLUB_NINE,
            Cards.DIAMOND_FIVE
        )

        val resolve = BestHandResolver.resolve(arr)
        assertThat(resolve).isInstanceOf(Hand.HighCard::class.java)
        assertThat((resolve as Hand.HighCard).kickers.cards[0].sign).isEqualTo(Cards.HEART_QUEEN)
        assertThat(resolve.kickers.cards[1].sign).isEqualTo(Cards.SPADE_JACK)
        assertThat(resolve.kickers.cards[2].sign).isEqualTo(Cards.DIAMOND_TEN)
        assertThat(resolve.kickers.cards[3].sign).isEqualTo(Cards.CLUB_NINE)
        assertThat(resolve.kickers.cards[4].sign).isEqualTo(Cards.CLUB_SIX)
    }
}