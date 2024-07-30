package org.hyperview.android

import arrow.core.None
import arrow.core.Some
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.primaryConstructor

fun getAllProperties(c: KClass<*>): List<String> {
    return c.members
        .filterIsInstance<KProperty<*>>()
        .map { it.name }
}

fun <T : Any> createInstance(c: KClass<T>, kwargs: Map<String, Any?>): T {
    val constructor = c.primaryConstructor!!
    val args = constructor.parameters.associateWith { param ->
        val type = param.type.toString()
        val value = kwargs[param.name]
        if (type.contains("arrow.core.Option")) {
            if (value == null) {
                None
            } else {
                Some(value)
            }
        } else if (type.contains("Boolean")) {
            value ?: false
        }
        else {
            value
        }
    }
    return constructor.callBy(args)
}
