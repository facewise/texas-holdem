interface ExtendedComparable<T> : Comparable<T> {
    fun isEquivalent(other: T) = this.compareTo(other) == 0
}