package com.corffen.study.delegate

class Animal {
    var name: String by Delegate()
}

fun main() {
    val animal = Animal()
    animal.name = "旺财"
    println(animal.name)

    val value = testWhen(2)
    println("value=$value")
}

private fun testWhen(type: Int): Int {
    return when (type) {
        else -> 8
    }
}