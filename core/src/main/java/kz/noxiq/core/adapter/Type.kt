package kz.noxiq.core.adapter

data class Type<T>(
    val clazz: Class<out T>,
    val delegate: ItemViewDelegate<T, *>,
    val linker: Linker<T>
)