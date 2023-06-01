package kz.alabs.core.adapter

import kotlin.reflect.KClass

interface KotlinClassLinker<T> {

    /**
     * Returns the class of your registered delegates for your item.
     *
     * @param position The position in items
     * @param item The item
     * @return The index of your registered delegates
     * @see OneToManyEndpoint.withJavaClassLinker
     */
    fun index(position: Int, item: T): KClass<out ItemViewDelegate<T, *>>
}