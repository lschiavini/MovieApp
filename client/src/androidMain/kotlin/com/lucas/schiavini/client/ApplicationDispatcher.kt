package com.lucas.schiavini.client

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Main