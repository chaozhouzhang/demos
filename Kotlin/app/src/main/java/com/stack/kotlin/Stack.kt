package com.stack.kotlin

class Stack<T> constructor(lan: Int) {

    private var lang: Int
    private lateinit var name: String

    constructor(nam: String, lan: Int) : this(lan) {
        name = nam
    }

    init {
        lang = lan
    }

}