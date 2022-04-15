package com.lucas.schiavini.movieapp

import android.annotation.SuppressLint
import android.content.Context
import com.lucas.schiavini.client.repository.DriverFactory
import com.lucas.schiavini.client.repository.createRepository

class AndroidRepository(context: Context) {
    private val driver = DriverFactory(context)
    val repository = createRepository(driver)
}