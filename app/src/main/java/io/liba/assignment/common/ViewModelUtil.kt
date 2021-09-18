package io.liba.assignment.common

import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

val defaultCoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    Timber.e(throwable)
}
