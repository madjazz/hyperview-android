package org.hyperview.android

interface Factory<T> {
    fun create(vararg args: Any): T
}
