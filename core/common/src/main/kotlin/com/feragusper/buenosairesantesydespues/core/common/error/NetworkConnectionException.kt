package com.feragusper.buenosairesantesydespues.core.common.error

/** Thrown when a network request cannot complete (no connectivity or transport failure). */
class NetworkConnectionException(cause: Throwable? = null) :
    RuntimeException("No network connection", cause)
