package com.lucas.schiavini.client.repository

import android.content.Context
import com.lucas.schiavini.client.db.MovieDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MovieDatabase.Schema, context, "MovieDatabase.db")
    }
}