package kz.noxiq.core.adapter

internal class DefaultLinker<T> : Linker<T> {
    override fun index(position: Int, item: T): Int = 0
}