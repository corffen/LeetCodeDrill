package com.corffen.study.delegate

import kotlin.reflect.KProperty

class Delegate {
    private var _value: String? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("getValue() thisRef=$thisRef,property=${property.name}")
        return _value ?: ""
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("setValue() thisRef=$thisRef,property=${property.name},value = $value")
        _value = value
    }
}