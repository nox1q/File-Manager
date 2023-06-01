package kz.alabs.core.adapter

import androidx.annotation.CheckResult

interface OneToManyFlow<T> {

    /**
     * Sets some item view delegates to the item type.
     *
     * @param delegates the item view delegates
     * @return end flow operator
     */
    @CheckResult
    fun to(vararg delegates: ItemViewDelegate<T, *>): OneToManyEndpoint<T>

    @CheckResult
    fun to(vararg delegates: ItemViewBinder<T, *>): OneToManyEndpoint<T>
}