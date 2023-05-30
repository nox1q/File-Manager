package kz.noxiq.core.adapter

import androidx.annotation.IntRange

interface Linker<T> {

    /**
     * Returns the index of your registered delegates for your item. The result should be in range of
     * `[0, one-to-multiple-delegates.length)`.
     *
     * Note: The argument of [OneToManyFlow.to] is the
     * one-to-multiple-delegates.
     *
     * @param position The position in items
     * @param item The data item
     * @return The index of your registered delegates
     * @see OneToManyFlow.to
     * @see OneToManyEndpoint.withLinker
     */
    @IntRange(from = 0)
    fun index(position: Int, item: T): Int
}