package com.lucas.schiavini.client

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}