package ch.smoca.hyperview

interface Factory<T> {
    fun create(vararg args: Any): T
}
