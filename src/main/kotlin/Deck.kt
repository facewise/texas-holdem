import java.security.SecureRandom
import kotlin.random.Random

class Deck {

    private val cards = shuffle()
    private var pos = 0

    private fun shuffle(): Array<Card> {
        val random = SecureRandom()
        val cards = Cards.entries.map { Card(it) }.toTypedArray()
        cards.shuffle(Random(random.nextLong()))
        return cards
    }

    fun deal() = cards[pos++]

}
