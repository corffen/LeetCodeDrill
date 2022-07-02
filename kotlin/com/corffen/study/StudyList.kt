package com.corffen.study

import java.util.*
import kotlin.collections.ArrayList

const val MAX_COUNT = 5
const val HOST = 4
val newlist = ArrayList<Int>()
fun main(args: Array<String>) {
//    val list = listOf(3, 2, 1, 5, 4, 6, 7, 9)
    newlist.clear()
    newlist.addAll(generatorList())
    println("init:$newlist")
    add(7)
    println("add 7:$newlist")
    add(9)
    println("add 9:$newlist")
    remove(3)
    println("remove 3:$newlist")
    remove(18)
    println("remove absent 18:$newlist")
    add(10)
    println("add 10:$newlist")
    remove(9)
    println("remove 9:$newlist")
    add(8)
    println("add 8:$newlist")
    remove(8)
    println("remove 8:$newlist")
    println(newlist)
    add(24)
    println("add 24:$newlist")
    add(22)
    println("add 22:$newlist")
    add(20)
    println("add 20:$newlist")
    add(21)
    println("add 21:$newlist")
}

private fun generatorList(): ArrayList<Int> {
    val list = (1..100000).toList()
    val newlist = ArrayList<Int>(MAX_COUNT + 5)
    val len = (MAX_COUNT + 1).coerceAtMost(list.size)
    Collections.sort(list) { o1, o2 -> o2 - o1 }
    (0 until len).forEach {
        newlist.add(list[it])
    }
    removeValue(HOST)
    if (newlist.size == MAX_COUNT + 1) {
        newlist.removeLast()
    }
    return newlist
}

private fun removeValue(value: Int) {
    val iterator = newlist.iterator()
    while (iterator.hasNext()) {
        val next = iterator.next()
        if (next == value) {
            iterator.remove()
            break
        }
    }
}

private fun add(value: Int) {
    if (value == HOST) {
        return
    }
    removeValue(value)
    newlist.add(value)
    sortList()
    while (newlist.size > MAX_COUNT) {
        newlist.removeLast()
    }
}

private fun sortList() {
    newlist.sortWith { o1, o2 -> o2 - o1 }
}

fun remove(value: Int) {
    if (value == HOST) {
        return
    }
    removeValue(value)
    sortList()
}