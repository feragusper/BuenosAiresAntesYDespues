package com.feragusper.buenosairesantesydespues.core.common.dispatchers

import javax.inject.Qualifier

/** Qualifier marking an injected [kotlinx.coroutines.CoroutineDispatcher]. */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: BaadDispatcher)

enum class BaadDispatcher {
    IO,
    Default,
}
